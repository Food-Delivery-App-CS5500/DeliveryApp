package edu.northeastern.cs5500.delivery.controller;


import edu.northeastern.cs5500.delivery.model.User;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;



@Singleton
@Slf4j
public class UserController {
    private final GenericRepository<User> users;

    @Inject
    UserController(GenericRepository<User> userRepository) {
        users = userRepository;
        log.info("UserController > construct");

        if (users.count() > 0) {
            return;
        }

        log.info("UserController > construct > adding default users");

        final User defaultUser1 = new User("A", "A", "A", "A", "A", 1234567891, "WA", "12345", "A", "A");
        final User defaultUser2 = new User("B", "B", "B", "B", "B", 1224567891, "SZ", "12342", "B", "B");

        try {
            addUser(defaultUser1);
            addUser(defaultUser2);
        } catch (Exception e) {
            log.error("UserController > construct > adding default User > failure?");
            e.printStackTrace();
        }
    }

    @Nullable
    public User getUser(@Nonnull ObjectId uuid) {
        log.debug("UserController > getUser({})", uuid);
        return users.get(uuid);
    }

    @Nonnull
    public Collection<User> getUsers() {
        log.debug("UserController > getUsers()");
        return users.getAll();
    }

    @Nonnull
    public User addUser(@Nonnull User user) throws Exception {
        log.debug("UserController > addUser(...)");
        if (!user.isValid()) {
            throw new ExceptionClass("InvalidUserException");
        }
        Collection<User> allUsers = users.getAll();
        ObjectId id = user.getId();
        if (id != null && users.get(id) != null) {
            throw new ExceptionClass("DuplicateKeyExecption");
        }

        if (!checkDuplicateUsername(allUsers, user)) {
            throw new ExceptionClass("DuplicateUsernameException");
        }

        return users.add(user);
    }

    private boolean checkDuplicateUsername(Collection<User> users, User userToCheck) {
        for (User user : users) {
            if (userToCheck.getUsername() == user.getUsername()) {
                return false;
            }
        }
        return true;
    }

    public void updateUser(@Nonnull User user) throws Exception {
        log.debug("UserController > updateUser(...)");
        users.update(user);
    }

    public void deleteUser(@Nonnull ObjectId id) throws Exception {
        log.debug("UserController > deleteUser(...)");
        users.delete(id);
    }

}


