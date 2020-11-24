package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.DeliveryDriver;
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
public class DeliveryDriverController {
    private final GenericRepository<DeliveryDriver> deliveryDrivers;

    @Inject
    DeliveryDriverController(GenericRepository<DeliveryDriver> deliveryDriverRepository) {
        deliveryDrivers = deliveryDriverRepository;
        log.info("DeliveryDriverController > construct");

        if (deliveryDrivers.count() > 0) {
            return;
        }

        log.info("DeliveryDriverController > construct > adding default users");

        final ObjectId order1 = new ObjectId();
        final DeliveryDriver defaultDeliveryDriver1 = new DeliveryDriver();
        defaultDeliveryDriver1.setFirstName("Shaun");
        defaultDeliveryDriver1.setLastName("Ho");
        defaultDeliveryDriver1.setEmail("Shaun@hotmail.com");
        defaultDeliveryDriver1.setOrderId(order1);
        defaultDeliveryDriver1.setPassword("aaa");
        defaultDeliveryDriver1.setPhoneNumber(1234567890L);
        defaultDeliveryDriver1.setUsername("shaunho");

        final ObjectId order2 = new ObjectId();
        final DeliveryDriver defaultDeliveryDriver2 = new DeliveryDriver();
        defaultDeliveryDriver2.setFirstName("Emily");
        defaultDeliveryDriver2.setLastName("Chiang");
        defaultDeliveryDriver2.setEmail("Emily@hotmail.com");
        defaultDeliveryDriver2.setOrderId(order2);
        defaultDeliveryDriver2.setPassword("aaa");
        defaultDeliveryDriver2.setPhoneNumber(1234567891L);
        defaultDeliveryDriver2.setUsername("emilychiang");

        try {
            addDeliveryDriver(defaultDeliveryDriver1);
            addDeliveryDriver(defaultDeliveryDriver2);
        } catch (Exception e) {
            log.error("DeliveryDriverController > construct > adding default User > failure?");
            e.printStackTrace();
        }
    }

    @Nullable
    public DeliveryDriver getDeliveryDriver(@Nonnull ObjectId uuid) {
        log.debug("DeliveryDriverController > getDeliveryDriver({})", uuid);
        return deliveryDrivers.get(uuid);
    }

    @Nonnull
    public Collection<DeliveryDriver> getDeliveryDrivers() {
        log.debug("DeliveryDriverController > getDeliveryDrivers()");
        return deliveryDrivers.getAll();
    }

    @Nonnull
    public DeliveryDriver addDeliveryDriver(@Nonnull DeliveryDriver deliveryDriver)
            throws ExceptionClass {
        log.debug("DeliveryDriverController > addDeliveryDriver(...)");
        if (!deliveryDriver.isValid()) {
            throw new ExceptionClass("InvalidUserException");
        }
        Collection<DeliveryDriver> allDeliveryDrivers = deliveryDrivers.getAll();
        ObjectId id = deliveryDriver.getId();
        if (id != null && deliveryDrivers.get(id) != null) {
            throw new ExceptionClass("DuplicateKeyExecption");
        }

        if (!checkPhoneLength(deliveryDriver)) {
            throw new ExceptionClass("PhoneNumberException");
        }

        if (!checkDuplicateUsername(allDeliveryDrivers, deliveryDriver)) {
            throw new ExceptionClass("DuplicateUsernameException");
        }

        return deliveryDrivers.add(deliveryDriver);
    }

    private boolean checkDuplicateUsername(
            Collection<DeliveryDriver> deliveryDrivers, DeliveryDriver driverToCheck) {
        for (DeliveryDriver driver : deliveryDrivers) {
            if (driverToCheck.getUsername().equals(driver.getUsername())) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPhoneLength(DeliveryDriver deliveryDriver) {
        if (Long.toString(deliveryDriver.getPhoneNumber()).length() != 10) {
            return false;
        }
        return true;
    }

    public void updateDeliveryDriver(@Nonnull DeliveryDriver driver) throws ExceptionClass {
        log.debug("DeliveryDriverController > updateDeliveryDriver(...)");

        if (!checkPhoneLength(driver)) {
            throw new ExceptionClass("PhoneNumberException");
        }

        deliveryDrivers.update(driver);
    }

    public void deleteDeliveryDriver(@Nonnull ObjectId id) throws ExceptionClass {
        log.debug("DeliveryDriverController > deleteDeliveryDriver(...)");
        deliveryDrivers.delete(id);
    }
}
