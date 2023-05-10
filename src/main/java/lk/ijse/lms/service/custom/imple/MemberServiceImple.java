package lk.ijse.lms.service.custom.imple;

import lk.ijse.lms.dto.MemberDTO;
import lk.ijse.lms.entity.Member;
import lk.ijse.lms.repository.RepoFactory;
import lk.ijse.lms.repository.custom.MemberRepo;
import lk.ijse.lms.service.custom.MemberService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

public class MemberServiceImple implements MemberService {
    MemberRepo repo = (MemberRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.MEMBER);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(MemberDTO memberDTO) {
        Session session = openSession();
        repo.update(modelMapper.map(memberDTO, Member.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) {
        Session session = openSession();
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(MemberDTO memberDTO) {
        Session session = openSession();
        repo.add(modelMapper.map(memberDTO, Member.class), session);
        closeSession(session);
    }

    @Override
    public MemberDTO search(String id) {
        Session session = openSession();
        MemberDTO map = modelMapper.map(repo.search(id, session), MemberDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<MemberDTO> getAll() {
        Session session = openSession();
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<MemberDTO>>() {
        }.getType());
        closeSession(session);
        return (List<MemberDTO>) map;
    }

    @Override
    public boolean isExist(String id) {
        Session session = openSession();
        Member member = session.get(Member.class, id);
        closeSession(session);
        return member != null;
    }
}
