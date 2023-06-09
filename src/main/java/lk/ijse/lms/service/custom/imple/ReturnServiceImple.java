package lk.ijse.lms.service.custom.imple;

import lk.ijse.lms.dto.ReturnDTO;
import lk.ijse.lms.entity.Return;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.repository.custom.ReturnRepo;
import lk.ijse.lms.service.custom.ReturnService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class ReturnServiceImple implements ReturnService {
    ReturnRepo repo = (ReturnRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.RETURN);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(ReturnDTO returnDTO) {
        Session session = openSession();
        repo.update(modelMapper.map(returnDTO, Return.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) {
        Session session = openSession();
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(ReturnDTO returnDTO) {
        Session session = openSession();
        repo.add(modelMapper.map(returnDTO, Return.class), session);
        closeSession(session);
    }

    @Override
    public ReturnDTO search(String id) {
        Session session = openSession();
        ReturnDTO map = modelMapper.map(repo.search(id, session), ReturnDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<ReturnDTO> getAll() {
        Session session = openSession();
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<ReturnDTO>>() {
        }.getType());
        closeSession(session);
        return (List<ReturnDTO>) map;
    }

}
