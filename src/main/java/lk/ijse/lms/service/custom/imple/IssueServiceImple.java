package lk.ijse.lms.service.custom.imple;

import lk.ijse.lms.dto.IssueDTO;
import lk.ijse.lms.entity.Issue;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.repository.custom.IssueRepo;
import lk.ijse.lms.service.custom.IssueService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class IssueServiceImple implements IssueService {
    IssueRepo repo = (IssueRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.ISSUE);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void save(IssueDTO issueDTO) {
        Session session = openSession();
        repo.add(modelMapper.map(issueDTO, Issue.class), session);
        closeSession(session);
    }

    @Override
    public IssueDTO search(String id) {
        Session session = openSession();
        IssueDTO map = modelMapper.map(repo.search(id, session), IssueDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<IssueDTO> getAll() {
        Session session = openSession();
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<IssueDTO>>() {
        }.getType());
        closeSession(session);
        return (List<IssueDTO>) map;
    }
}
