package edu.northeastern.cs5500.delivery.controller;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import edu.northeastern.cs5500.delivery.model.User;
import edu.northeastern.cs5500.delivery.repository.InMemoryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserControllerTest {
    User user1 = new User();
    User user2 = new User();
    User user3 = new User();

    @BeforeEach
    public void init() {
        user1.setFirstName("Jen");
        user1.setLastName("Chang");
        user1.setAddress("Seattle St");
        user1.setPassword("abcde");
        user1.setState("WA");
        user1.setCity("Seattle");
        user1.setZip("12345");
        user1.setEmail("abc@gmail.com");
        user1.setPhoneNumber(1234567890L);
        user1.setUsername("jenChang");

        user2.setFirstName("Nachi");
        user2.setLastName("Nachiket");
        user2.setAddress("Seattle Rd");
        user2.setPassword("batman");
        user2.setCity("Seattle");
        user2.setState("WA");
        user2.setZip("14234");
        user2.setEmail("bde@gmail.com");
        user2.setPhoneNumber(8372831022L);
        user2.setUsername("nachiNachiket");

        user3.setFirstName("James");
        user3.setLastName("Lee");
        user3.setAddress("Bellevue St");
        user3.setPassword("12345fsa");
        user3.setCity("Bellevue");
        user3.setState("WA");
        user3.setZip("23142");
        user3.setEmail("def@gmail.com");
        user3.setPhoneNumber(1827364578L);
        user3.setUsername("jamesLee");
    }

    @Test
    void testRegisterCreatesUsers() {
        UserController userController = new UserController(new InMemoryRepository<User>());
        assertThat(userController.getUsers()).isNotEmpty();
    }

    @Test
    void testRegisterCreatesValidUsers() {
        UserController userController = new UserController(new InMemoryRepository<User>());
        assertThat(userController.getUsers()).isNotEmpty();

        for (User user : userController.getUsers()) {
            assertWithMessage(user.getUsername()).that(user.isValid()).isTrue();
        }
    }

    @Test
    void testCanAddUsers() throws ExceptionClass {
        UserController userController = new UserController(new InMemoryRepository<User>());
        User addedUser1 = userController.addUser(user1);
        ObjectId addedUser1ID = addedUser1.getId();
        User addedUserInCollection = userController.getUser(addedUser1ID);
        assertEquals(user1, addedUserInCollection);
        assertEquals(user1.getUsername(), addedUserInCollection.getUsername());
    }

    @Test
    void testCanUpdateUser() throws ExceptionClass {
        UserController userController = new UserController(new InMemoryRepository<User>());
        System.out.println(userController.getUsers());
        User addedUser2 = userController.addUser(user2);
        ObjectId addedUser2ID = addedUser2.getId();
        System.out.println(
                "Original Address: " + userController.getUser(addedUser2ID).getAddress());
        System.out.println("Added in User2: " + userController.getUsers());
        addedUser2.setAddress("Bellevue Road");
        userController.updateUser(addedUser2);
        System.out.println(
                "Changed user2 Address: " + userController.getUser(addedUser2ID).getAddress());
        assertEquals(addedUser2, userController.getUser(addedUser2ID));
        System.out.println(userController.getUsers());
    }

    @Test
    void testCanDeleteUser() throws ExceptionClass {
        UserController userController = new UserController(new InMemoryRepository<User>());
        User addedUser3 = userController.addUser(user3);
        ObjectId addedUser3Id = addedUser3.getId();
        userController.deleteUser(addedUser3Id);
        assertNotEquals(addedUser3, userController.getUser(addedUser3Id));
        System.out.println(userController.getUser(addedUser3Id));
    }

    @Test
    void testAddInvalidPhoneNumber() {
        User user = new User();
        user.setFirstName("Jen");
        user.setLastName("Chang");
        user.setAddress("Seattle St");
        user.setPassword("abcde");
        user.setState("WA");
        user.setCity("Seattle");
        user.setZip("00000");
        user.setEmail("abc@gmail.com");
        user.setPhoneNumber(1234567891021L);
        user.setUsername("jenChang");

        UserController userController = new UserController(new InMemoryRepository<User>());
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    userController.addUser(user);
                });
    }

    @Test
    void testAddDuplicateUsername() {
        User dupUser = new User();
        dupUser.setFirstName("Jennie");
        dupUser.setLastName("Zhang");
        dupUser.setAddress("Seattle St");
        dupUser.setPassword("abcde");
        dupUser.setState("WA");
        dupUser.setCity("Seattle");
        dupUser.setZip("12345");
        dupUser.setEmail("aa@gmail.com");
        dupUser.setPhoneNumber(1234567891021L);
        dupUser.setUsername("jenChang");

        UserController userController = new UserController(new InMemoryRepository<User>());

        Assertions.assertThrows(
                Exception.class,
                () -> {
                    userController.addUser(dupUser);
                });
    }

    @Test
    void testAddInvalidZipcode() {
        User user = new User();
        user.setFirstName("Jen");
        user.setLastName("Chang");
        user.setAddress("Seattle St");
        user.setPassword("abcde");
        user.setState("WA");
        user.setCity("Seattle");
        user.setZip("0000");
        user.setEmail("abc@gmail.com");
        user.setPhoneNumber(1234567891021L);
        user.setUsername("jenChang");

        UserController userController = new UserController(new InMemoryRepository<User>());
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    userController.addUser(user);
                });
    }

    @Test
    void testAddInvalidUser() {
        User user = new User();
        user.setFirstName("Jen");
        user.setPassword("abcde");
        user.setState("WA");
        user.setCity("Seattle");
        user.setPhoneNumber(1234567891021L);
        user.setUsername("jenChang");

        UserController userController = new UserController(new InMemoryRepository<User>());
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    userController.addUser(user);
                });
    }
}
