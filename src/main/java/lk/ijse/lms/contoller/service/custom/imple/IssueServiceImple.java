package lk.ijse.lms.contoller.service.custom.imple;

import lk.ijse.lms.contoller.dto.IssueDTO;
import lk.ijse.lms.contoller.entity.Issue;
import lk.ijse.lms.contoller.repository.RepoFactory;
import lk.ijse.lms.contoller.repository.custom.IssueRepo;
import lk.ijse.lms.contoller.service.custom.IssueService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class IssueServiceImple implements IssueService {
    IssueRepo repo = (IssueRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.ISSUE);
    Session session = FactoryConfiguration.getInstance().operSession();
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void save(IssueDTO issueDTO)throws Exception {
        openSession(session);
        repo.add(modelMapper.map(issueDTO, Issue.class), session);
        closeSession(session);
    }

    @Override
    public IssueDTO search(String id) throws Exception{
        openSession(session);
        IssueDTO map = modelMapper.map(repo.search(id, session), IssueDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<IssueDTO> getAll() throws Exception{
        openSession(session);
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<IssueDTO>>() {
        }.getType());
        closeSession(session);
        return (List<IssueDTO>) map;
    }

}
