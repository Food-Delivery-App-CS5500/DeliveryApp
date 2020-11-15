package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.FoodItem;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;

import java.util.ArrayList;
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

        log.info("FoodItemController > construct > adding default food items");

        final FoodItem defaultFoodItem1 = new FoodItem();
        defaultFoodItem1.setFoodItem("Cheese Pizza");
        defaultFoodItem1.setFoodPrice(1199);

        final FoodItem defaultFoodItem2 = new FoodItem();
        defaultFoodItem1.setFoodItem("Pepperoni Pizza");
        defaultFoodItem1.setFoodPrice(1299);

        try {
            addFoodItem(defaultFoodItem1);
            addFoodItem(defaultFoodItem2);
        } catch (Exception e) {
            log.error("FoodItemController > construct > adding default food items > failure?");
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
            // TODO: replace with a real invalid object exception
            // probably not one exception per object type though
            throw new ExceptionClass("InvalidFoodItemException");
        }

        ObjectId id = foodItem.getId();

        if (id != null && foodItems.get(id) != null) {
            // TODO: replace with a real duplicate key exception
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

}
