package lk.ijse.lms.contoller.service;

import lk.ijse.lms.contoller.repository.RepoFactory;
import lk.ijse.lms.contoller.service.custom.imple.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    private ServiceFactory(){}
    public enum RepoTypes {
        LOGIN, BOOK, MEMBER, ISSUE, RETURN, CATOGERY
    }

    public static ServiceFactory getInstance() {
        return serviceFactory == null ? serviceFactory = new ServiceFactory() : serviceFactory;
    }

    public SuperService getRepo(RepoFactory.RepoTypes types) {
        switch (types) {
            case LOGIN:
                return new LoginServiceImple();
            case BOOK:
                return new BookServiceImple();
            case ISSUE:
                return new IssueServiceImple();
            case MEMBER:
                return new MemberServiceImple();
            case RETURN:
                return new ReturnServiceImple();
            case CATOGERY:
                return new CategoryServiceImple();
            default:
                return null;
        }
    }
}
