package lk.ijse.lms.service.custom.imple;

import lk.ijse.lms.dto.LoginDTO;
import lk.ijse.lms.entity.Login;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.repository.custom.LoginRepo;
import lk.ijse.lms.service.custom.LoginService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class LoginServiceImple implements LoginService {
    LoginRepo repo = (LoginRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.LOGIN);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(LoginDTO loginDTO) {
        Session session = openSession();
        repo.update(modelMapper.map(loginDTO, Login.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) {
        Session session = openSession();
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(LoginDTO loginDTO) {
        Session session = openSession();
        repo.add(modelMapper.map(loginDTO, Login.class), session);
        closeSession(session);
    }

    @Override
    public LoginDTO search(String id) {
        Session session = openSession();
        LoginDTO map = modelMapper.map(repo.search(id, session), LoginDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<LoginDTO> getAll() {
        Session session = openSession();
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<LoginDTO>>() {
        }.getType());
        closeSession(session);
        return (List<LoginDTO>) map;
    }
}
