package web.dao;

import web.models.Users;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void save(Users user);
    List<Users> findAll();
    Optional<Users> findById(Long id);
    void updateUser(Users user);
    void deleteById(Long id);
}
