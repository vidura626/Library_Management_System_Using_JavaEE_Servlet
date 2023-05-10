package lk.ijse.lms.contoller.service.custom.imple;

import lk.ijse.lms.contoller.dto.ReturnDTO;
import lk.ijse.lms.contoller.entity.Return;
import lk.ijse.lms.contoller.repository.RepoFactory;
import lk.ijse.lms.contoller.repository.custom.ReturnRepo;
import lk.ijse.lms.contoller.service.custom.ReturnService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class ReturnServiceImple implements ReturnService {
    ReturnRepo repo = (ReturnRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.RETURN);
    Session session = FactoryConfiguration.getInstance().operSession();
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(ReturnDTO returnDTO) {
        openSession(session);
        repo.update(modelMapper.map(returnDTO, Return.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) {
        openSession(session);
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(ReturnDTO returnDTO) {
        openSession(session);
        repo.add(modelMapper.map(returnDTO, Return.class), session);
        closeSession(session);
    }

    @Override
    public ReturnDTO search(String id) {
        openSession(session);
        ReturnDTO map = modelMapper.map(repo.search(id, session), ReturnDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<ReturnDTO> getAll() {
        openSession(session);
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<ReturnDTO>>() {
        }.getType());
        closeSession(session);
        return (List<ReturnDTO>) map;
    }

}
