package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    public String encrypt(String password);
    List<UserModel> getUserList();
    UserModel getUserByUsername(String username);
    void deleteUserByUsername(String Username);
    UserModel updatePassword(UserModel user, String newPassword);
    boolean matchPassword(String newPassword, String oldPassword);

}
