package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import edu.northeastern.cs5500.delivery.model.FoodItem;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.OrderStatus;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class OrderController {
    private final GenericRepository<Order> orders;

    @Inject
    OrderController(GenericRepository<Order> orderRepository) {
        orders = orderRepository;

        log.info("OrderController > construct");

        if (orders.count() > 0) {
            return;
        }

        log.info("OrderController > construct > adding default orders");

        final Restaurant defaultOrderRestaurant1 = new Restaurant();
        defaultOrderRestaurant1.setId(new ObjectId());
        ObjectId defaultOrderRestaurant1Id = defaultOrderRestaurant1.getId();
        defaultOrderRestaurant1.setRestaurantName("Best Pasta");
        defaultOrderRestaurant1.setRestaurantDescription("Best taste from Sicily!");
        FoodItem defaultFood1 = new FoodItem();
        defaultFood1.setId(new ObjectId());
        defaultFood1.setFoodItem("Fettucini Alfredo");
        defaultFood1.setFoodPrice(1699);
        FoodItem defaultFood2 = new FoodItem();
        defaultFood2.setId(new ObjectId());
        defaultFood2.setFoodItem("Carbonara Sausage Classic");
        defaultFood2.setFoodPrice(1799);
        HashMap<ObjectId, FoodItem> defaultOrderRestaurant1Menu = new HashMap<>();
        defaultOrderRestaurant1Menu.put(defaultFood1.getId(), defaultFood1);
        defaultOrderRestaurant1Menu.put(defaultFood2.getId(), defaultFood2);
        defaultOrderRestaurant1.setRestaurantMenu(defaultOrderRestaurant1Menu);
        final Order defaultOrder1 = new Order();
        defaultOrder1.setId(new ObjectId());
        defaultOrder1.setUserName("John Doe");
        defaultOrder1.setRestaurantId(defaultOrderRestaurant1Id);
        HashMap<ObjectId, Integer> order1Items = new HashMap<>();
        order1Items.put(defaultFood2.getId(), 2);
        defaultOrder1.setComment("Extra bread please!");
        defaultOrder1.setOrderTime(LocalDateTime.now());
        defaultOrder1.setStatus(OrderStatus.INCART);
        defaultOrder1.setScheduleDelivery(LocalDateTime.of(2020, 11, 19, 18, 30, 20));
        CreditCard defaultOrderPaymentCard1 = new CreditCard();
        defaultOrderPaymentCard1.setCardNumber(4141262631315566L);
        defaultOrderPaymentCard1.setExpirationDate(LocalDate.of(2024, 4, 2));
        defaultOrderPaymentCard1.setUserName("Tisha Doe");
        defaultOrder1.setPaymentCard(defaultOrderPaymentCard1);
        defaultOrder1.setOrderFoodItems(order1Items);

        final Restaurant defaultOrderRestaurant2 = new Restaurant();
        defaultOrderRestaurant2.setId(new ObjectId());
        ObjectId defaultOrderRestaurant2Id = defaultOrderRestaurant2.getId();
        defaultOrderRestaurant2.setRestaurantName("Big Fried Awesomeness");
        defaultOrderRestaurant2.setRestaurantDescription("Best Southern Fried Chicken!");
        FoodItem defaultFood3 = new FoodItem();
        defaultFood3.setId(new ObjectId());
        defaultFood3.setFoodItem("Spicy Meal");
        defaultFood3.setFoodPrice(1499);
        FoodItem defaultFood4 = new FoodItem();
        defaultFood4.setId(new ObjectId());
        defaultFood4.setFoodItem("Regular Meal");
        defaultFood4.setFoodPrice(1449);
        HashMap<ObjectId, FoodItem> defaultOrderRestaurant2Menu = new HashMap<>();
        defaultOrderRestaurant2Menu.put(defaultFood3.getId(), defaultFood3);
        defaultOrderRestaurant2Menu.put(defaultFood4.getId(), defaultFood4);
        defaultOrderRestaurant2.setRestaurantMenu(defaultOrderRestaurant2Menu);
        final Order defaultOrder2 = new Order();
        defaultOrder2.setId(new ObjectId());
        defaultOrder2.setUserName("Jane Doe");
        defaultOrder2.setRestaurantId(defaultOrderRestaurant2Id);
        HashMap<ObjectId, Integer> order2Items = new HashMap<>();
        order2Items.put(defaultFood3.getId(), 2);
        order2Items.put(defaultFood4.getId(), 1);
        defaultOrder2.setComment("Skip celery, Extra barbeque sauce on the side");
        defaultOrder2.setOrderTime(LocalDateTime.now());
        defaultOrder2.setStatus(OrderStatus.ORDERPLACED);
        defaultOrder2.setScheduleDelivery(LocalDateTime.of(2020, 11, 20, 16, 30, 20));
        CreditCard defaultOrderPaymentCard2 = new CreditCard();
        defaultOrderPaymentCard2.setCardNumber(4141585833335555L);
        defaultOrderPaymentCard2.setExpirationDate(LocalDate.of(2026, 6, 11));
        defaultOrderPaymentCard2.setUserName("Jane Doe");
        defaultOrder2.setPaymentCard(defaultOrderPaymentCard2);
        defaultOrder2.setOrderFoodItems(order2Items);
        try {
            addOrder(defaultOrder1);
            addOrder(defaultOrder2);
        } catch (Exception e) {
            log.error("OrderController > construct > adding default orders > failure?");
            e.printStackTrace();
        }
    }

    @Nullable
    public Order getOrder(@Nonnull ObjectId uuid) {
        log.debug("OrderController > getOrders({})", uuid);
        return orders.get(uuid);
    }

    @Nonnull
    public Collection<Order> getOrders() {
        log.debug("OrderController > getOrders()");
        return orders.getAll();
    }

    @Nonnull
    public Order addOrder(@Nonnull Order order) throws ExceptionClass {
        log.debug("OrderController > addOrder(...)");
        if (!order.isValid()) {
            // Replace with a real invalid object exception
            // probably not one exception per object type though
            throw new ExceptionClass("InvalidOrderException");
        }

        ObjectId id = order.getId();

        if (id != null && orders.get(id) != null) {
            // Replace with a real duplicate key exception
            throw new ExceptionClass("DuplicateKeyException");
        }

        return orders.add(order);
    }

    public void updateOrder(@Nonnull Order order) throws ExceptionClass {
        log.debug("OrderController > updateOrder(...)");
        orders.update(order);
    }

    public void deleteOrder(@Nonnull ObjectId id) throws ExceptionClass {
        log.debug("OrderController > deleteOrder(...)");
        orders.delete(id);
    }
}
