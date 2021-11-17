package apap.tutorial.emsidi.service;

import apap.tutorial.emsidi.model.UserModel;
import apap.tutorial.emsidi.repository.UserDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDB userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return  hashedPassword;
    }

    @Override
    public List<UserModel> getUserList(){ return userDb.findAll();}

    @Override
    public UserModel getUserByUsername(String username){
        return userDb.findByUsername(username);
    }

    @Override
    public void deleteUserByUsername(String username){ userDb.deleteByUsername(username); }

    @Override
    public UserModel updatePassword(UserModel user, String newPassword){
        String pass = encrypt(newPassword);
        user.setPassword(pass);
        return userDb.save(user);
    };

    @Override
    public boolean matchPassword(String newPassword, String oldPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(oldPassword, newPassword);
    }
}