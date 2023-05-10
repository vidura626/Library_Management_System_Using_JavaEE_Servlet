package lk.ijse.lms.contoller.service.custom.imple;

import lk.ijse.lms.contoller.dto.MemberDTO;
import lk.ijse.lms.contoller.entity.Member;
import lk.ijse.lms.contoller.repository.RepoFactory;
import lk.ijse.lms.contoller.repository.custom.CatogeryRepo;
import lk.ijse.lms.contoller.repository.custom.MemberRepo;
import lk.ijse.lms.contoller.service.custom.MemberService;
import lk.ijse.lms.util.FactoryConfiguration;
import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MemberServiceImple implements MemberService {
    MemberRepo repo = (MemberRepo) RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.MEMBER);
    Session session = FactoryConfiguration.getInstance().operSession();
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public void update(MemberDTO memberDTO) throws Exception {
        openSession(session);
        repo.update(modelMapper.map(memberDTO, Member.class), session);
        closeSession(session);
    }

    @Override
    public void delete(String id) throws Exception {
        openSession(session);
        repo.delete(id, session);
        closeSession(session);
    }

    @Override
    public void save(MemberDTO memberDTO) throws Exception {
        openSession(session);
        repo.add(modelMapper.map(memberDTO,Member.class),session);
        closeSession(session);
    }

    @Override
    public MemberDTO search(String id) throws Exception {
        openSession(session);
        MemberDTO map = modelMapper.map(repo.search(id, session), MemberDTO.class);
        closeSession(session);
        return map;
    }

    @Override
    public List<MemberDTO> getAll() throws Exception {
        openSession(session);
        Object map = modelMapper.map(repo.getAll(session), new TypeToken<List<MemberDTO>>() {
        }.getType());
        closeSession(session);
        return (List<MemberDTO>) map;
    }

}
