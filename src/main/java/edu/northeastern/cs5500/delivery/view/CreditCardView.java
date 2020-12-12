package edu.northeastern.cs5500.delivery.view;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.Spark.put;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.CreditCardController;
import edu.northeastern.cs5500.delivery.model.CreditCard;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class CreditCardView implements View {

    @Inject
    CreditCardView() {}

    @Inject JsonTransformer jsonTransformer;

    @Inject CreditCardController creditCardController;

    @Override
    public void register() {
        log.info("CreditCardView > register");

        get(
                "/creditcard",
                (request, response) -> {
                    System.out.println("Get");
                    log.debug("/creditcard");
                    response.type("application/json");
                    return creditCardController.getCreditCards();
                },
                jsonTransformer);

        get(
                "/creditcard/:id",
                (request, response) -> {
                    final String paramId = request.params(":id");
                    log.debug("/creditcard/:id<{}>", paramId);
                    final ObjectId id = new ObjectId(paramId);
                    CreditCard card = creditCardController.getCreditCard(id);
                    if (card == null) {
                        halt(404);
                    }
                    response.type("application/json");
                    return card;
                },
                jsonTransformer);

        post(
                "/creditcardcreate",
                (request, response) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    CreditCard card = mapper.readValue(request.body(), CreditCard.class);
                    if (!card.isValid()) {
                        response.status(400);
                        return "";
                    }

                    // Ignore the user-provided ID if there is one
                    card.setId(null);
                    card = creditCardController.addCreditCard(card);

                    return "Created Credit Card";
                });

        put(
                "/creditcardupdate",
                (request, response) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    CreditCard card = mapper.readValue(request.body(), CreditCard.class);
                    if (card.getUserName() == null) {
                        System.out.println("not valid");
                        response.status(400);
                        return "";
                    }
                    creditCardController.updateCreditCard(card);
                    return "Credit Card Updated";
                });

        delete(
                "/creditcarddelete",
                (request, response) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    CreditCard card = mapper.readValue(request.body(), CreditCard.class);
                    Long cardNumber = card.getCardNumber();
                    creditCardController.deleteCreditCard("cardNumber", cardNumber);
                    return "Deleted Credit Card";
                });
    }
}
