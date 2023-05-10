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
                return new LoginARepoImple();
            case BOOK:
                return new BookRepoImple();
            case ISSUE:
                return new IssueRepoImple();
            case MEMBER:
                return new MemberRepoImple();
            case RETURN:
                return new ReturnRepoImple();
            case CATOGERY:
                return new CatogeryRepoImple();
            default:
                return null;
        }
    }

}
