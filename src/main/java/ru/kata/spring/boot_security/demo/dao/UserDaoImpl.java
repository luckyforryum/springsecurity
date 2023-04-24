package ru.kata.spring.boot_security.demo.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserDaoImpl(EntityManager entityManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.entityManager = entityManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return entityManager.createQuery("from UserEntity", UserEntity.class).getResultList();
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        String queryStr = "SELECT u FROM UserEntity u LEFT JOIN FETCH u.roles WHERE u.username = :username";
        TypedQuery<UserEntity> query = entityManager.createQuery(queryStr, UserEntity.class);
        query.setParameter("username", username);
        UserEntity UserEntity;
        try {
            UserEntity = query.getSingleResult();
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return Optional.ofNullable(UserEntity);
    }

    @Override
    public UserEntity getInfoByUsername(String username) {
        TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class);
        query.setParameter("username", username);
        UserEntity users = query.getSingleResult();
        return users;
    }

    @Override
    public void save(UserEntity user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        entityManager.merge(user);

    }

    @Override
    public void update(UserEntity user) {
        entityManager.merge(user);

    }

    @Override
    public UserEntity getUser(Long id) {
        return entityManager.find(UserEntity.class,id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getUser(id));
    }
}
