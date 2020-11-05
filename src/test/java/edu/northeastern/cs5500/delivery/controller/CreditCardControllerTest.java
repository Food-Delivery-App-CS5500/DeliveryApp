package edu.northeastern.cs5500.delivery.controller;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import edu.northeastern.cs5500.delivery.model.CreditCard;
import edu.northeastern.cs5500.delivery.repository.InMemoryRepository;
import java.time.LocalDate;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditCardControllerTest {
    @Test
    void testRegisterCreatesCreditCards() {
        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        assertThat(cardController.getCreditCards()).isNotEmpty();
    }

    @Test
    void testRegisterCreatesValidDeliverys() {
        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        for (CreditCard card : cardController.getCreditCards()) {
            String cardNum = Long.toString(card.getCardNumber());
            assertWithMessage(cardNum).that(card.isValid()).isTrue();
        }
    }

    @Test
    void testCount() {
        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        assertThat(cardController.count()).isEqualTo(2L);
    }

    @Test
    void testCanAddCreditCard() throws Exception {
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber(9876543219876543L);
        newCard.setUserName("Emily");
        LocalDate date = LocalDate.of(2022, 11, 1);
        newCard.setExpirationDate(date);

        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        CreditCard addedCard = cardController.addCreditCard(newCard);
        assertThat(cardController.count()).isEqualTo(3);
        assertThat(addedCard.getCardNumber()).isEqualTo(newCard.getCardNumber());
    }

    @Test
    void testAddInvalidCreditCard() {
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber(2323454567678989L);
        LocalDate date = LocalDate.of(2024, 4, 2);
        newCard.setExpirationDate(date);

        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());

        Assertions.assertThrows(
                Exception.class,
                () -> {
                    cardController.addCreditCard(newCard);
                });
    }

    @Test
    void testAddDuplicateCreditCard() throws Exception {
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber(6543876543219876L);
        newCard.setUserName("Eric");
        LocalDate date = LocalDate.of(2024, 4, 4);
        newCard.setExpirationDate(date);

        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        CreditCard addedCard = cardController.addCreditCard(newCard);
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    cardController.addCreditCard(addedCard);
                });
    }

    @Test
    void testAddWrongNumberCreditCard() throws Exception {
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber(1654387654321L);
        newCard.setUserName("Elly");
        LocalDate date = LocalDate.of(2024, 3, 3);
        newCard.setExpirationDate(date);

        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    cardController.addCreditCard(newCard);
                });
    }

    @Test
    void testAddWrongDateCreditCard() throws Exception {
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber(9012345876543214L);
        newCard.setUserName("Ella");
        LocalDate date = LocalDate.of(2018, 3, 3);
        newCard.setExpirationDate(date);

        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    cardController.addCreditCard(newCard);
                });
    }

    @Test
    void testCanReplaceCreditCard() throws Exception {
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber(9876543219876543L);
        newCard.setUserName("Piglet");
        LocalDate date = LocalDate.of(2022, 11, 1);
        newCard.setExpirationDate(date);

        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        CreditCard addedCard = cardController.addCreditCard(newCard);
        newCard.setUserName("Pooh");
        cardController.updateCreditCard(newCard);

        assertThat(addedCard.getUserName()).isEqualTo("Pooh");
        assertThat(cardController.count()).isEqualTo(3);
    }

    @Test
    void testCanDeleteCreditCard() throws Exception {
        CreditCard newCard = new CreditCard();
        newCard.setCardNumber(5432123454321234L);
        newCard.setUserName("William");
        LocalDate date = LocalDate.of(2023, 2, 3);
        newCard.setExpirationDate(date);

        CreditCardController cardController =
                new CreditCardController(new InMemoryRepository<CreditCard>());
        CreditCard addedCard = cardController.addCreditCard(newCard);
        ObjectId cardId = addedCard.getId();

        cardController.deleteCreditCard(cardId);
        assertThat(cardController.getCreditCard(cardId)).isNull();
    }
}
