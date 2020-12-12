package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import java.time.LocalDate;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

/** This is a credit card controller class that add, delete, update and edit creditcard objects. */
@Singleton
@Slf4j
public class CreditCardController {
    private final GenericRepository<CreditCard> creditCards;
    /**
     * Contructor for creditcard controller using dagger.
     *
     * @param creditCardRepository - a collection of creditcards.
     */
    @Inject
    CreditCardController(GenericRepository<CreditCard> creditCardRepository) {
        creditCards = creditCardRepository;

        log.info("CreditCardController > construct");

        if (creditCards.count() > 0) {
            return;
        }

        log.info("creditCardController > construct > adding default credit cards");

        final CreditCard defaultCreditCard1 = new CreditCard();
        defaultCreditCard1.setCardNumber(1234123412341234L);
        defaultCreditCard1.setUserName("Jay");
        defaultCreditCard1.setExpirationYear(2022);
        defaultCreditCard1.setExpirationMonth(12);
        LocalDate today = LocalDate.now();
        Integer year = today.getYear();
        Integer month = today.getMonthValue();
        System.out.println("Year" + year);
        System.out.println("expY" + defaultCreditCard1.getExpirationYear());
        System.out.println("Month" + month);
        System.out.println("expM" + defaultCreditCard1.getExpirationMonth());

        final CreditCard defaultCreditCard2 = new CreditCard();
        defaultCreditCard2.setCardNumber(5432543254325432L);
        defaultCreditCard2.setUserName("Yam");
        defaultCreditCard2.setExpirationYear(2022);
        defaultCreditCard2.setExpirationMonth(12);

        try {
            addCreditCard(defaultCreditCard1);
            addCreditCard(defaultCreditCard2);
        } catch (Exception e) {
            log.error("DeliveryController > construct > adding default deliverys > failure?");
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a creditcard object from database.
     *
     * @param id - object id of a creditcard object
     * @return CreditCard - return a creditcard object
     * @throws Exception - throws exception when object id does not exist in database
     */
    @Nullable
    public CreditCard getCreditCard(@Nonnull ObjectId id) throws Exception {
        log.debug("CreditCardController > getCard({})", id);
        return creditCards.get(id);
    }

    /**
     * Retrieve all creditcard objects from databse.
     *
     * @return Collection<CreditCard> - return a collection of creditcards
     */
    @Nonnull
    public Collection<CreditCard> getCreditCards() {
        log.debug("CreditCardController > getAllCards({})");
        return creditCards.getAll();
    }
    /**
     * Add a new creditcard object to database.
     *
     * @param creditCard - a creditCard object
     * @return CreditCard - a added creditCard object
     * @throws Exception - throws exception when credit card info not valid or duplicate key in
     *     database.
     */
    @Nonnull
    public CreditCard addCreditCard(@Nonnull CreditCard creditCard) throws Exception {
        log.debug("CreditCardController > addCreditCard(...)");
        if (!creditCard.isValid()) {
            throw new ExceptionClass("Can NOT add a credit card");
        }
        ObjectId id = creditCard.getId();
        if (id != null && creditCards.get(id) != null) {
            throw new ExceptionClass("CreditCardDuplicateKeyException");
        }

        if (checkCardNumLength(creditCard) != 16) {
            throw new ExceptionClass("Invliad card number or expiration date!");
        }

        return creditCards.add(creditCard);
    }

    /**
     * Update information in a existing creditcard object.
     *
     * @param creditCard - a creditCard object to update
     * @throws Exception - throws exception when credit card info not valid.
     */
    public void updateCreditCard(@Nonnull CreditCard creditCard) throws Exception {
        log.debug("CreditCardController > updateCreditCard(...)");
        creditCards.updateByUserName(creditCard, creditCard.getUserName());
    }

    /**
     * Delete a creditcard object by id.
     *
     * @param id - the object id of the creditcard being deleted.
     */
    public void deleteCreditCard(@Nonnull ObjectId id) {
        log.debug("DeliveryController > deleteDelivery(...)");
        creditCards.delete(id);
    }

    /**
     * Delete a creditcard object by cardNumber.
     *
     * @param id - the object id of the creditcard being deleted.
     */
    public void deleteCreditCard(@Nonnull String uniqueId, Object cardnumber) {
        log.debug("DeliveryController > deleteDelivery(...)");
        creditCards.deleteByUnique(uniqueId, cardnumber);
    }
    /** Count the number of creditcard objects in the collection. */
    public Long count() {
        return creditCards.count();
    }

    /**
     * Check if the length of a creditcard number if 16 digits.
     *
     * @param creditCard - a creditCard object being checked.
     * @return the length of the card number.
     */
    private Integer checkCardNumLength(CreditCard creditCard) {
        Long cardNum = creditCard.getCardNumber();
        String cardNumStr = Long.toString(cardNum);
        Integer cardNumLength = cardNumStr.length();
        return cardNumLength;
    }
}
