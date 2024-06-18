package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Users;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Users user) {
        entityManager.persist(user);
    }

    @Override
    public List<Users> findAll() {
        return entityManager.createQuery("select u from Users u", Users.class)
                .getResultList();
    }

    @Override
    public Optional<Users> findById(Long id) {
        Users user = entityManager.find(Users.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public void updateUser(Users user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Users> userById = findById(id);
        userById.ifPresent(user -> entityManager.remove(user));
    }
}
