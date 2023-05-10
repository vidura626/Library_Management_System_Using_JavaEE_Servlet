package lk.ijse.lms.contoller.service.custom.imple;

import lk.ijse.lms.contoller.dto.CatogeryDTO;
import lk.ijse.lms.contoller.entity.Catogery;
import lk.ijse.lms.contoller.repository.RepoFactory;
import lk.ijse.lms.contoller.repository.custom.CatogeryRepo;
import lk.ijse.lms.contoller.service.custom.CatogeryService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class CategoryServiceImple implements CatogeryService {

    CatogeryRepo repo = (CatogeryRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.CATOGERY);
    Session session = FactoryConfiguration.getInstance().operSession();
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(CatogeryDTO catogeryDTO) throws Exception {
        openSession(session);
        repo.update(modelMapper.map(catogeryDTO, Catogery.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) throws Exception {
        openSession(session);
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(CatogeryDTO catogeryDTO) throws Exception {
        openSession(session);
        repo.add(modelMapper.map(catogeryDTO, Catogery.class), session);
        closeSession(session);
    }

    @Override
    public CatogeryDTO search(String id) throws Exception {
        openSession(session);
        CatogeryDTO map = modelMapper.map(repo.search(id, session), CatogeryDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<CatogeryDTO> getAll() throws Exception {
        openSession(session);
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<CatogeryDTO>>() {
        }.getType());
        closeSession(session);
        return (List<CatogeryDTO>) map;
    }
}
