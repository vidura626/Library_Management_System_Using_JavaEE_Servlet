package lk.ijse.lms.contoller.service.custom.imple;

import lk.ijse.lms.contoller.dto.LoginDTO;
import lk.ijse.lms.contoller.entity.Login;
import lk.ijse.lms.contoller.repository.RepoFactory;
import lk.ijse.lms.contoller.repository.custom.CatogeryRepo;
import lk.ijse.lms.contoller.repository.custom.LoginRepo;
import lk.ijse.lms.contoller.service.custom.LoginService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class LoginServiceImple implements LoginService {
    LoginRepo repo = (LoginRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.LOGIN);
    Session session = FactoryConfiguration.getInstance().operSession();
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(LoginDTO loginDTO) throws Exception {
        openSession(session);
        repo.update(modelMapper.map(loginDTO, Login.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) throws Exception {
        openSession(session);
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(LoginDTO loginDTO) throws Exception {
        openSession(session);
        repo.add(modelMapper.map(loginDTO, Login.class), session);
        closeSession(session);
    }

    @Override
    public LoginDTO search(String id) throws Exception {
        openSession(session);
        LoginDTO map = modelMapper.map(repo.search(id, session), LoginDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<LoginDTO> getAll() throws Exception {
        openSession(session);
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<LoginDTO>>() {
        }.getType());
        closeSession(session);
        return (List<LoginDTO>) map;
    }

}
