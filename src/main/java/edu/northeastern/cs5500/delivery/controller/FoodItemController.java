package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.FoodItem;
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
public class FoodItemController {
    private final GenericRepository<FoodItem> foodItems;

    @Inject
    FoodItemController(GenericRepository<FoodItem> foodItemRepository) {
        foodItems = foodItemRepository;

        log.info("FoodItemController > construct");

        if (foodItems.count() > 0) {
            return;
        }

        log.info("FoodItemController > construct > adding default foodItems");

        // Create 2 default restaurants to reside in the Restaurant Controller
        FoodItem defaultFood1 = new FoodItem();
        defaultFood1.setId(new ObjectId());
        defaultFood1.setFoodItem("Veggie Pizza");
        defaultFood1.setFoodPrice(1199);
        FoodItem defaultFood2 = new FoodItem();
        defaultFood2.setId(new ObjectId());
        defaultFood2.setFoodItem("Meat Lovers Pizza");
        defaultFood2.setFoodPrice(1399);

        try {
            addFoodItem(defaultFood1);
            addFoodItem(defaultFood2);
        } catch (Exception e) {
            log.error("FoodItemController > construct > adding default fooditems > failure?");
            e.printStackTrace();
        }
    }

    @Nullable
    public FoodItem getFoodItem(@Nonnull ObjectId uuid) {
        log.debug("FoodItemController > getFoodItems({})", uuid);
        return foodItems.get(uuid);
    }

    @Nonnull
    public Collection<FoodItem> getFoodItems() {
        log.debug("FoodItemController > getFoodItems()");
        return foodItems.getAll();
    }

    @Nonnull
    public FoodItem addFoodItem(@Nonnull FoodItem foodItem) throws ExceptionClass {
        log.debug("FoodItemController > addFoodItem(...)");
        if (!foodItem.isValid()) {
            // Replace with a real invalid object exception
            // probably not one exception per object type though
            throw new ExceptionClass("InvalidFoodItemException");
        }

        ObjectId id = foodItem.getId();

        if (id != null && foodItems.get(id) != null) {
            // Replace with a real duplicate key exception
            throw new ExceptionClass("DuplicateKeyException");
        }

        return foodItems.add(foodItem);
    }

    public void updateFoodItem(@Nonnull FoodItem foodItem) throws ExceptionClass {
        log.debug("FoodItemController > updateFoodItem(...)");
        foodItems.update(foodItem);
    }

    public void deleteFoodItem(@Nonnull ObjectId id) throws ExceptionClass {
        log.debug("FoodItemController > deleteFoodItem(...)");
        foodItems.delete(id);
    }
}
