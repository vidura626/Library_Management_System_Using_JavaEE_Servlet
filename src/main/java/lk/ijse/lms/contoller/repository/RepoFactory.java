package lk.ijse.lms.contoller.repository;

import lk.ijse.lms.contoller.repository.custom.imple.*;

public class RepoFactory {
    private static RepoFactory repoFactory;

    private RepoFactory() {

    }

    public enum RepoTypes {
        LOGIN, BOOK, MEMBER, ISSUE, RETURN, CATOGERY
    }

    public static RepoFactory getInstance() {
        return repoFactory == null ? repoFactory = new RepoFactory() : repoFactory;
    }

    public SuperRepo getRepo(RepoTypes types) {
        switch (types) {
            case LOGIN:
                return (SuperRepo) new LoginARepoImple();
            case BOOK:
                return (SuperRepo) new BookRepoImple();
            case ISSUE:
                return (SuperRepo) new IssueRepoImple();
            case MEMBER:
                return (SuperRepo) new MemberRepoImple();
            case RETURN:
                return (SuperRepo) new ReturnRepoImple();
            case CATOGERY:
                return new CatogeryRepoImple();
            default:
                return null;
        }
    }

}
