package lk.ijse.lms.service.custom.imple;

import lk.ijse.lms.dto.CatogeryDTO;
import lk.ijse.lms.entity.Catogery;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.repository.custom.CatogeryRepo;
import lk.ijse.lms.service.custom.CatogeryService;
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
    public void update(CatogeryDTO catogeryDTO) {
        openSession(session);
        repo.update(modelMapper.map(catogeryDTO, Catogery.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) {
        openSession(session);
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(CatogeryDTO catogeryDTO) {
        openSession(session);
        repo.add(modelMapper.map(catogeryDTO, Catogery.class), session);
        closeSession(session);
    }

    @Override
    public CatogeryDTO search(String id) {
        openSession(session);
        CatogeryDTO map = modelMapper.map(repo.search(id, session), CatogeryDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<CatogeryDTO> getAll() {
        openSession(session);
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<CatogeryDTO>>() {
        }.getType());
        closeSession(session);
        return (List<CatogeryDTO>) map;
    }
}
