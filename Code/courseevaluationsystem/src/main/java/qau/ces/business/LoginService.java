package qau.ces.business;

import qau.ces.data.LoginDao;

public class LoginService {
    private LoginDao loginDao = new LoginDao();

    public boolean validateUser(String username, String password) {
        return loginDao.validateUser(username, password);
    }
}