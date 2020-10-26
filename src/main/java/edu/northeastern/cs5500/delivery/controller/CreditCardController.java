package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;


public class CreditCardController {
    private final GenericRepository<CreditCard> creditCards;

    @Inject //constructor for creditcard controller
    CreditCardController(GenericRepository<CreditCard> creditCardRepository) {
        creditCards = creditCardRepository;
        log.info("CreditCardController > construct");

        if (deliverys.count() > 0) {
            return;
        }
'''
        log.info("DeliveryController > construct > adding default deliverys");

        final Delivery defaultDelivery1 = new Delivery();
        defaultDelivery1.setTitle("Hot dog");

        final Delivery defaultDelivery2 = new Delivery();
        defaultDelivery2.setTitle("A steak");
        defaultDelivery2.setDescription("Not a hot dog");
'''
        try {
            addCreditCard()
        } catch (Exception e) {
            log.error("DeliveryController > construct > adding default deliverys > failure?");
            e.printStackTrace();
        }
    }

    public CreditCard getCreditCard(@Nonnull Integer cardNum){
        //call creditcard
        //check cardNum has the right length and call number then return
        return creditCards.get(cardNum)
    }

    public CreditCard addCreditCard(@Nonnull CreditCard creditCard) throws Exception {
        // isValid() from CreditCard class
        //else throw exception
        return creditCards.add(creditCard)
    }

    public CreditCard updateCreditCard(@Nonnull CreditCard creditCard) throws Exception{


    }

    public void delete(@Nonnull ObjectId id);

    public Collection<T> getAll();

    public long count();

}
