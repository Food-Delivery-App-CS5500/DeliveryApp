package edu.northeastern.cs5500.delivery.controller;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import edu.northeastern.cs5500.delivery.model.Review;
import edu.northeastern.cs5500.delivery.repository.InMemoryRepository;
import java.time.LocalDate;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReviewControllerTest {
    @Test
    void testRegisterCreatesReviews() {
        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());
        assertThat(reviewController.getReviews()).isNotEmpty();
    }

    @Test
    void testRegisterCreatesValidReviews() {
        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());
        for (Review review : reviewController.getReviews()) {
            String comment = review.getComment();
            assertWithMessage(comment).that(review.isValid()).isTrue();
        }
    }

    @Test
    void testCount() {
        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());
        assertThat(reviewController.count()).isEqualTo(2);
    }

    @Test
    void testCanAddReview() throws Exception {
        Review newReview = new Review();
        newReview.setComment("There are better ones out there...");
        newReview.setUserName("DJ");
        newReview.setRestaurantName("QFC");
        newReview.setRating(3.0);
        LocalDate date = LocalDate.of(2018, 6, 3);
        newReview.setCreatedTime(date);

        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());
        Review addedReview = reviewController.addReview(newReview);
        assertThat(reviewController.count()).isEqualTo(3);
        assertThat(addedReview.getId()).isEqualTo(newReview.getId());
    }

    @Test
    void testAddInvalidReviewNoComment() {
        Review newReview = new Review();
        newReview.setUserName("Tororo");
        newReview.setRestaurantName("Korean Food");
        newReview.setRating(4.5);
        LocalDate date = LocalDate.of(2019, 6, 5);
        newReview.setCreatedTime(date);

        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());

        Assertions.assertThrows(
                Exception.class,
                () -> {
                    reviewController.addReview(newReview);
                });
    }

    @Test
    void testAddInvalidReviewNoRating() {
        Review newReview = new Review();
        newReview.setComment("I love their dessert. Not too sweet!");
        newReview.setUserName("Ku");
        newReview.setRestaurantName("Grab a Bite");
        LocalDate date = LocalDate.of(2019, 5, 5);
        newReview.setCreatedTime(date);

        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());

        Assertions.assertThrows(
                Exception.class,
                () -> {
                    reviewController.addReview(newReview);
                });
    }

    @Test
    void testAddInvalidReviewRestaurantName() {
        Review newReview = new Review();
        newReview.setComment("I love their chocolate. Not too sweet!");
        newReview.setUserName("Jim");
        LocalDate date = LocalDate.of(2019, 5, 5);
        newReview.setCreatedTime(date);

        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());

        Assertions.assertThrows(
                Exception.class,
                () -> {
                    reviewController.addReview(newReview);
                });
    }

    @Test
    void testAddDuplicateReview() throws Exception {
        Review newReview = new Review();
        newReview.setComment("Too Yuumy...");
        newReview.setUserName("Shaun");
        newReview.setRestaurantName("BestBite");
        newReview.setRating(4.0);
        LocalDate date = LocalDate.of(2020, 10, 7);
        newReview.setCreatedTime(date);

        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());
        Review addedReview = reviewController.addReview(newReview);
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    reviewController.addReview(addedReview);
                });
    }

    @Test
    void testCanReplaceReview() throws Exception {
        Review newReview = new Review();
        newReview.setComment("Just ok, nothing special.");
        newReview.setUserName("Henry");
        newReview.setRestaurantName("Ymmy");
        newReview.setRating(3.3);
        LocalDate date = LocalDate.of(2020, 11, 1);
        newReview.setCreatedTime(date);

        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());
        Review addedReview = reviewController.addReview(newReview);
        newReview.setRating(3.0);
        reviewController.updateReview(newReview);

        assertThat(addedReview.getRating()).isEqualTo(3.0);
        assertThat(reviewController.count()).isEqualTo(3);
    }

    @Test
    void testCanDeleteReview() throws Exception {
        Review newReview = new Review();
        newReview.setComment("Too cold for ice cream!");
        newReview.setUserName("Sunny");
        newReview.setRestaurantName("Yee");
        newReview.setRating(4.5);
        LocalDate date = LocalDate.of(2020, 7, 21);
        newReview.setCreatedTime(date);

        ReviewController reviewController = new ReviewController(new InMemoryRepository<Review>());
        Review addedReview = reviewController.addReview(newReview);
        ObjectId cardId = addedReview.getId();

        reviewController.deleteReview(cardId);
        assertThat(reviewController.getReview(cardId)).isNull();
    }
}
