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
import java.time.LocalDate;

@Singleton
@Slf4j
public class CreditCardController {
    private final GenericRepository<CreditCard> creditCards;

    @Inject //constructor for creditcard controller
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
    public CreditCard getCreditCard(@Nonnull Integer cardNum){
        //call creditcard
        //check cardNum has the right length and call number then return
        log.debug("CreditCardController > getCard({})", cardNum);
        return creditCards.get(cardNum);
    }

    @Nonnull
    public Collection<CreditCard> getCreditCards(){
        //call creditcard
        //check cardNum has the right length and call number then return
        log.debug("CreditCardController > getAllCards({})");
        return creditCards.getAll();
    }

    @Nonnull
    public CreditCard addCreditCard(@Nonnull CreditCard creditCard) throws Exception {
        log.debug("CreditCardController > addCreditCard(...)");
        if (!creditCard.isValid()) {
            throw new Exception("Can NOT add a credit card");
        }
        Integer cardNumber = creditCard.getCardNumber();
        if (cardNumber != null && creditCards.get(cardNumber) != null) {
            throw new Exception("CreditCardDuplicateKeyException");
        }
        return creditCards.add(creditCard);
    }

    public CreditCard updateCreditCard(@Nonnull CreditCard creditCard) throws Exception{
        log.debug("CreditCardController > updateCreditCard(...)");
        creditCards.update(creditCard);

    }

    public void delete(@Nonnull CreditCard creditCard) {
        log.debug("DeliveryController > deleteDelivery(...)");
        creditCards.delete(creditCard);
    }

    //public long count();

}
