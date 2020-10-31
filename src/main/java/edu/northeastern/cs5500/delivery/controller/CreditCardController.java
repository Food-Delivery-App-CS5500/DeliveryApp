package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import java.time.LocalDate;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@Singleton
@Slf4j
public class CreditCardController {
    private final GenericRepository<CreditCard> creditCards;

    @Inject // constructor for creditcard controller
    CreditCardController(GenericRepository<CreditCard> creditCardRepository) {
        creditCards = creditCardRepository;

        log.info("CreditCardController > construct");

        if (creditCards.count() > 0) {
            return;
        }

        log.info("creditCardController > construct > adding default credit cards");

        final CreditCard defaultCreditCard1 = new CreditCard();
        defaultCreditCard1.setCardNumber(123);
        defaultCreditCard1.setUserName("Jay");
        LocalDate date = LocalDate.of(2020, 12, 1);
        defaultCreditCard1.setExpirationDate(date);

        final CreditCard defaultCreditCard2 = new CreditCard();
        defaultCreditCard2.setCardNumber(456);
        defaultCreditCard2.setUserName("Yam");
        LocalDate date2 = LocalDate.of(2022, 1, 1);
        defaultCreditCard2.setExpirationDate(date);

        try {
            addCreditCard(defaultCreditCard1);
            addCreditCard(defaultCreditCard2);
        } catch (Exception e) {
            log.error("DeliveryController > construct > adding default deliverys > failure?");
            e.printStackTrace();
        }
    }

    @Nonnull
    public CreditCard getCreditCard(@Nonnull ObjectId id) throws Exception {
        log.debug("CreditCardController > getCard({})", id);
        if (creditCards.get(id) == null) {
            throw new Exception("No such card exist.");
        }
        return creditCards.get(id);
    }

    @Nonnull
    public Collection<CreditCard> getCreditCards() {
        // call creditcard
        // check cardNum has the right length and call number then return
        log.debug("CreditCardController > getAllCards({})");
        return creditCards.getAll();
    }

    @Nonnull
    public CreditCard addCreditCard(@Nonnull CreditCard creditCard) throws Exception {
        log.debug("CreditCardController > addCreditCard(...)");
        if (!creditCard.isValid()) {
            throw new Exception("Can NOT add a credit card");
        }
        ObjectId id = creditCard.getId();
        if (id != null && creditCards.get(id) != null) {
            throw new Exception("CreditCardDuplicateKeyException");
        }
        Integer cardnumber = creditCard.getCardNumber();
        LocalDate expDate = creditCard.getExpirationDate();
        // iterate through all objects in the collection to check if there are duplicate creditcard
        // number
        // Set<CreditCard> allCreditCards = creditCards.getAll();
        // for (CreditCard card: allCreditCards) {
        //     if (card.getCardNumber() == cardnumber && card.getExpirationDate() != expDate) {
        //         throw new Exception("CreditCard");
        //     }
        // }

        if (checkExpirationDate(creditCard) < 0) {
            throw new Exception("Invliad expiration date!");
        }

        return creditCards.add(creditCard);
    }

    public void updateCreditCard(@Nonnull CreditCard creditCard) throws Exception {
        log.debug("CreditCardController > updateCreditCard(...)");
        
        if (checkExpirationDate(creditCard) < 0) {
            throw new Exception("Invliad expiration date!");
        }

        creditCards.update(creditCard);
    }

    public void deleteCreditCard(@Nonnull ObjectId id) {
        log.debug("DeliveryController > deleteDelivery(...)");
        creditCards.delete(id);
    }

    private Integer checkExpirationDate(CreditCard creditCard) {
        LocalDate expDate = creditCard.getExpirationDate();
        LocalDate now = LocalDate.now();
        return expDate.compareTo(now);
    }

    // public long count();

}
