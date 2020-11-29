package edu.northeastern.cs5500.delivery.view;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.FoodItemController;
import edu.northeastern.cs5500.delivery.model.FoodItem;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class FoodItemView implements View {

    @Inject
    FoodItemView() {}

    @Inject JsonTransformer jsonTransformer;

    @Inject FoodItemController foodItemController;

    @Override
    public void register() {
        log.info("FoodItemView > register");

        get(
                "/foodItem",
                (request, response) -> {
                    log.debug("/foodItem");
                    response.type("application/json");
                    return foodItemController.getFoodItems();
                },
                jsonTransformer);

        get(
                "/foodItem/:id",
                (request, response) -> {
                    final String paramId = request.params(":id");
                    log.debug("/foodItem/:id<{}>", paramId);
                    final ObjectId id = new ObjectId(paramId);
                    FoodItem foodItem = foodItemController.getFoodItem(id);
                    if (foodItem == null) {
                        halt(404);
                    }
                    response.type("application/json");
                    return foodItem;
                },
                jsonTransformer);

        post(
                "/foodItem",
                (request, response) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    FoodItem foodItem = mapper.readValue(request.body(), FoodItem.class);
                    if (!foodItem.isValid()) {
                        response.status(400);
                        return "";
                    }

                    // Ignore the user-provided ID if there is one
                    foodItem.setId(null);
                    foodItem = foodItemController.addFoodItem(foodItem);

                    // response.redirect(
                    //         String.format("/foodItem/{}", foodItem.getId().toHexString()),
                    // 301);
                    return foodItem;
                });

        put(
                "/foodItem",
                (request, response) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    FoodItem foodItem = mapper.readValue(request.body(), FoodItem.class);
                    if (!foodItem.isValid()) {
                        response.status(400);
                        return "";
                    }

                    foodItemController.updateFoodItem(foodItem);
                    return foodItem;
                });

        delete(
                "/foodItem",
                (request, response) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    FoodItem foodItem = mapper.readValue(request.body(), FoodItem.class);

                    foodItemController.deleteFoodItem(foodItem.getId());
                    return foodItem;
                });
    }
}
