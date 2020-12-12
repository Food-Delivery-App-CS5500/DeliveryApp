package edu.northeastern.cs5500.delivery.controller;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import edu.northeastern.cs5500.delivery.model.FoodItem;
import edu.northeastern.cs5500.delivery.model.Order;
import edu.northeastern.cs5500.delivery.model.OrderStatus;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.repository.InMemoryRepository;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderControllerTest {
    public Order testOrder1 = new Order();
    public Order testOrder2 = new Order();
    public Order testOrder3 = new Order();
    public Restaurant testRestaurant1 = new Restaurant();
    public Restaurant testRestaurant2 = new Restaurant();
    public Restaurant testRestaurant3 = new Restaurant();

    @BeforeEach
    public void init() {
        testRestaurant1.setId(new ObjectId());
        ObjectId testRestaurant1Id = testRestaurant1.getId();
        testRestaurant1.setRestaurantName("Best Dosa");
        testRestaurant1.setRestaurantDescription("Best South Indian food!");
        FoodItem testFood1 = new FoodItem();
        testFood1.setId(new ObjectId());
        testFood1.setFoodItem("Plain Dosa");
        testFood1.setFoodPrice(899);
        FoodItem testFood2 = new FoodItem();
        testFood2.setId(new ObjectId());
        testFood2.setFoodItem("Masala Dosa");
        testFood2.setFoodPrice(1099);
        HashMap<String, FoodItem> testRestaurant1Menu = new HashMap<>();
        testRestaurant1Menu.put(testFood1.getId().toString(), testFood1);
        testRestaurant1Menu.put(testFood2.getId().toString(), testFood2);
        testRestaurant1.setRestaurantMenu(testRestaurant1Menu);

        testOrder1.setUserName("James Tenzing");
        testOrder1.setRestaurantId(testRestaurant1Id);
        HashMap<String, Integer> testOrder1Items = new HashMap<>();
        testOrder1Items.put(testFood2.getId().toString(), 2);
        testOrder1.setComment("Extra chutney please!");
        testOrder1.setOrderTime(LocalDateTime.now());
        testOrder1.setStatus(OrderStatus.ORDERPLACED);
        testOrder1.setScheduleDelivery(LocalDateTime.of(2020, 11, 19, 18, 30, 20));
        CreditCard defaultOrderPaymentCard1 = new CreditCard();
        defaultOrderPaymentCard1.setCardNumber(4141262631310101L);
        defaultOrderPaymentCard1.setExpirationYear(2024);
        defaultOrderPaymentCard1.setExpirationMonth(12);
        defaultOrderPaymentCard1.setUserName("Tisha Tenzing");
        testOrder1.setPaymentCard(defaultOrderPaymentCard1);
        testOrder1.setOrderFoodItems(testOrder1Items);

        testOrder2.setUserName("Giana Hill");
        testOrder2.setRestaurantId(testRestaurant1Id);
        HashMap<String, Integer> testOrder2Items = new HashMap<>();
        testOrder2Items.put(testFood1.getId().toString(), 1);
        testOrder2Items.put(testFood2.getId().toString(), 2);
        testOrder2.setComment("Extra curry and napkins please!");
        testOrder2.setOrderTime(LocalDateTime.now());
        testOrder2.setStatus(OrderStatus.ORDERPLACED);
        testOrder2.setScheduleDelivery(LocalDateTime.of(2020, 12, 19, 17, 32, 25));
        CreditCard defaultOrderPaymentCard2 = new CreditCard();
        defaultOrderPaymentCard2.setCardNumber(8888262600110101L);
        defaultOrderPaymentCard2.setExpirationYear(2024);
        defaultOrderPaymentCard2.setExpirationMonth(12);
        defaultOrderPaymentCard2.setUserName("Giana Hill");
        testOrder2.setPaymentCard(defaultOrderPaymentCard2);
        testOrder2.setOrderFoodItems(testOrder2Items);
    }

    @Test
    void testRegisterCreatesOrders() throws ExceptionClass {
        OrderController orderController = new OrderController(new InMemoryRepository<Order>());
        assertThat(orderController.getOrders()).isNotEmpty();
    }

    @Test
    void testRegisterCreatesValidOrders() {
        OrderController orderController = new OrderController(new InMemoryRepository<Order>());
        for (Order order : orderController.getOrders()) {
            assertWithMessage(order.getUserName()).that(order.isValid()).isTrue();
        }
    }

    @Test
    void testCanAddOrder() throws ExceptionClass {
        // This test should NOT call register
        OrderController orderController = new OrderController(new InMemoryRepository<Order>());
        testOrder1.setId(new ObjectId());
        ObjectId testOrder1Id = testOrder1.getId();
        orderController.addOrder(testOrder1);
        assertEquals(testOrder1, orderController.getOrder(testOrder1Id));
    }

    @Test
    void testCanReplaceOrder() throws ExceptionClass {
        // This test should NOT call register
        OrderController orderController = new OrderController(new InMemoryRepository<Order>());
        ObjectId testOrder1Id = testOrder1.getId();
        FoodItem testFood1 = new FoodItem();
        testFood1.setId(new ObjectId());
        testFood1.setFoodItem("Plain Dosa");
        testFood1.setFoodPrice(899);
        testOrder1.getOrderFoodItems().put(testFood1.getId().toString(), 2);
        orderController.updateOrder(testOrder1);
        assertEquals(testOrder1, orderController.getOrder(testOrder1Id));
        assertEquals(2, orderController.getOrder(testOrder1Id).getOrderFoodItems().size());
    }

    @Test
    void testCanDeleteOrder() throws ExceptionClass {
        // This test should NOT call register
        OrderController orderController = new OrderController(new InMemoryRepository<Order>());
        testOrder2.setId(new ObjectId());
        ObjectId testOrder2Id = testOrder2.getId();
        orderController.addOrder(testOrder2);
        orderController.deleteOrder(testOrder2Id);
        assertNull(orderController.getOrder(testOrder2Id));
        assertNotEquals(testOrder2Id, orderController.getOrder(testOrder2Id));
    }
}
