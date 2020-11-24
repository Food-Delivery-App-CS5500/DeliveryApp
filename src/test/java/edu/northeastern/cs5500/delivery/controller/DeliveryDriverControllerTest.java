package edu.northeastern.cs5500.delivery.controller;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import edu.northeastern.cs5500.delivery.model.DeliveryDriver;
import edu.northeastern.cs5500.delivery.repository.InMemoryRepository;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryDriverControllerTest {
    ObjectId order1 = new ObjectId();
    ObjectId order2 = new ObjectId();
    ObjectId order3 = new ObjectId();
    DeliveryDriver DeliveryDriver1 = new DeliveryDriver();
    DeliveryDriver DeliveryDriver2 = new DeliveryDriver();
    DeliveryDriver DeliveryDriver3 = new DeliveryDriver();

    @BeforeEach
    public void init() {
        DeliveryDriver1.setFirstName("Jen");
        DeliveryDriver1.setLastName("Chang");
        DeliveryDriver1.setPassword("abcde");
        DeliveryDriver1.setEmail("abc@gmail.com");
        DeliveryDriver1.setOrderId(order1);
        DeliveryDriver1.setPhoneNumber(1234567890L);
        DeliveryDriver1.setUsername("jenChang");

        DeliveryDriver2.setFirstName("Nachi");
        DeliveryDriver2.setLastName("Nachiket");
        DeliveryDriver2.setPassword("batman");
        DeliveryDriver2.setEmail("bde@gmail.com");
        DeliveryDriver2.setOrderId(order2);
        DeliveryDriver2.setPhoneNumber(8372831022L);
        DeliveryDriver2.setUsername("nachiNachiket");

        DeliveryDriver3.setFirstName("James");
        DeliveryDriver3.setLastName("Lee");
        DeliveryDriver3.setPassword("12345fsa");
        DeliveryDriver3.setEmail("def@gmail.com");
        DeliveryDriver3.setOrderId(order3);
        DeliveryDriver3.setPhoneNumber(1827364578L);
        DeliveryDriver3.setUsername("jamesLee");
    }

    @Test
    void testRegisterCreatesDeliveryDrivers() {
        DeliveryDriverController deliveryDriverController =
                new DeliveryDriverController(new InMemoryRepository<DeliveryDriver>());
        assertThat(deliveryDriverController.getDeliveryDrivers()).isNotEmpty();
    }

    @Test
    void testRegisterCreatesValidDeliveryDriver() {
        DeliveryDriverController deliveryDriverController =
                new DeliveryDriverController(new InMemoryRepository<DeliveryDriver>());
        assertThat(deliveryDriverController.getDeliveryDrivers()).isNotEmpty();

        for (DeliveryDriver deliveryDriver : deliveryDriverController.getDeliveryDrivers()) {
            assertWithMessage(deliveryDriver.getUsername()).that(deliveryDriver.isValid()).isTrue();
        }
    }

    @Test
    void testCanAddDeliveryDrivers() throws ExceptionClass {
        DeliveryDriverController deliveryDriverController =
                new DeliveryDriverController(new InMemoryRepository<DeliveryDriver>());
        DeliveryDriver addedDeliveryDrviver =
                deliveryDriverController.addDeliveryDriver(DeliveryDriver1);
        ObjectId addedDeliveryDrviver1ID = addedDeliveryDrviver.getId();
        DeliveryDriver addedDeliveryDriverInCollection =
                deliveryDriverController.getDeliveryDriver(addedDeliveryDrviver1ID);
        assertEquals(DeliveryDriver1, addedDeliveryDriverInCollection);
        assertEquals(DeliveryDriver1.getUsername(), addedDeliveryDriverInCollection.getUsername());
    }

    @Test
    void testCanUpdateDeliveryDriver() throws ExceptionClass {
        DeliveryDriverController deliveryDriverController =
                new DeliveryDriverController(new InMemoryRepository<DeliveryDriver>());
        DeliveryDriver addedDeliveryDriver2 =
                deliveryDriverController.addDeliveryDriver(DeliveryDriver2);
        ObjectId addedDeliveryDriver2ID = addedDeliveryDriver2.getId();
        addedDeliveryDriver2.setUsername("HelloHello");
        deliveryDriverController.updateDeliveryDriver(addedDeliveryDriver2);
        assertEquals(
                addedDeliveryDriver2,
                deliveryDriverController.getDeliveryDriver(addedDeliveryDriver2ID));
    }

    @Test
    void testCanDeleteDeliveryDriver() throws ExceptionClass {
        DeliveryDriverController deliveryDriverController =
                new DeliveryDriverController(new InMemoryRepository<DeliveryDriver>());
        DeliveryDriver addedDeliveryDriver3 =
                deliveryDriverController.addDeliveryDriver(DeliveryDriver3);
        ObjectId addedDeliveryDriver3ID = addedDeliveryDriver3.getId();
        deliveryDriverController.deleteDeliveryDriver(addedDeliveryDriver3ID);
        assertNotEquals(
                addedDeliveryDriver3,
                deliveryDriverController.getDeliveryDriver(addedDeliveryDriver3ID));
    }

    @Test
    void testAddInvalidPhoneNumber() {
        DeliveryDriver deliveryDriver = new DeliveryDriver();
        deliveryDriver.setFirstName("Jen");
        deliveryDriver.setLastName("Chang");
        deliveryDriver.setPassword("abcde");
        deliveryDriver.setEmail("abc@gmail.com");
        deliveryDriver.setPhoneNumber(1234567891021L);
        deliveryDriver.setUsername("jenChang");

        DeliveryDriverController deliveryDriverController =
                new DeliveryDriverController(new InMemoryRepository<DeliveryDriver>());
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    deliveryDriverController.addDeliveryDriver(deliveryDriver);
                });
    }

    @Test
    void testAddDuplicateUsername() throws ExceptionClass {
        DeliveryDriver dupUser = new DeliveryDriver();
        dupUser.setFirstName("Jennie");
        dupUser.setLastName("Zhang");
        dupUser.setPassword("abcde");
        dupUser.setEmail("aa@gmail.com");
        dupUser.setPhoneNumber(1234567891021L);
        dupUser.setUsername("jenChang");

        DeliveryDriverController deliveryDriverController =
                new DeliveryDriverController(new InMemoryRepository<DeliveryDriver>());
        deliveryDriverController.addDeliveryDriver(DeliveryDriver1);
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    deliveryDriverController.addDeliveryDriver(dupUser);
                });
    }

    @Test
    void testAddInvalidDeliveryDriver() {
        DeliveryDriver invalidDriver = new DeliveryDriver();
        invalidDriver.setFirstName("Jen");
        invalidDriver.setLastName("zz");
        invalidDriver.setPassword("abcde");
        invalidDriver.setPhoneNumber(12345678911123L);
        invalidDriver.setUsername("jenChang");
        invalidDriver.setEmail("jen2@gmail.com");

        DeliveryDriverController deliveryDriverController =
                new DeliveryDriverController(new InMemoryRepository<DeliveryDriver>());

        Assertions.assertThrows(
                Exception.class,
                () -> {
                    deliveryDriverController.addDeliveryDriver(invalidDriver);
                });
    }
}
