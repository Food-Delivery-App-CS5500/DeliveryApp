package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.Review;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import java.time.LocalDate;
import java.util.Collection;
import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

/** This is a review controller class that add, delete, update and edit Review objects. */
@Singleton
@Slf4j
public class ReviewController {
    private final GenericRepository<Review> reviews;
    /**
     * Contructor for review controller using dagger.
     *
     * @param reviewRepository - a collection of reviews.
     */
    @Inject
    ReviewController(GenericRepository<Review> reviewRepository) {
        reviews = reviewRepository;

        log.info("ReviewController > construct");

        if (reviews.count() > 0) {
            return;
        }

        log.info("ReviewController > construct > adding default Reviews");

        final Review defaultReview1 = new Review();
        defaultReview1.setComment("This place has the best fried chicken ever!");
        defaultReview1.setUserName("Jay");
        defaultReview1.setRating(4.8);
        LocalDate date = LocalDate.of(2019, 10, 10);
        defaultReview1.setCreatedTime(date);

        final Review defaultReview2 = new Review();
        defaultReview2.setComment("There was a hair in my soup...");
        defaultReview2.setUserName("Ken");
        defaultReview2.setRating(1.0);
        LocalDate date2 = LocalDate.of(2018, 12, 26);
        defaultReview2.setCreatedTime(date2);

        try {
            addReview(defaultReview1);
            addReview(defaultReview2);
        } catch (Exception e) {
            log.error("ReviewController > construct > adding default reviews > failure?");
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a review object from database.
     *
     * @param id - object id of a review object
     * @return Review - return a review object
     * @throws Exception - throws exception when object id does not exist in database
     */
    @Nonnull
    public Review getReview(@Nonnull ObjectId id) throws Exception {
        log.debug("ReviewController > getReview({})", id);
        if (reviews.get(id) == null) {
            throw new Exception("No such review exist.");
        }
        return reviews.get(id);
    }
    /**
     * Retrieve all review objects from databse.
     *
     * @return Collection<Review> - return a collection of reviews
     */
    @Nonnull
    public Collection<Review> getReviews() {
        log.debug("ReviewController > getReviews({})");
        return reviews.getAll();
    }
    /**
     * Add a new review object to database.
     *
     * @param review - a review object
     * @return Review - an added Review object
     * @throws Exception - throws exception when Review info not valid or duplicate key in database.
     */
    @Nonnull
    public Review addReview(@Nonnull Review review) throws Exception {
        log.debug("ReviewControllerr > addReview(...)");
        if (!review.isValid()) {
            throw new ExceptionClass("Can NOT add a review");
        }
        ObjectId id = review.getId();
        if (id != null && reviews.get(id) != null) {
            throw new ExceptionClass("ReviewDuplicateKeyException");
        }

        return reviews.add(review);
    }

    /**
     * Update information in a existing review object.
     *
     * @param review - a review object to update
     * @throws Exception - throws exception when review info not valid.
     */
    public void updateCreditCard(@Nonnull Review review) throws Exception {
        log.debug("ReviewController > updateReview(...)");
        reviews.update(review);
    }
    /**
     * Delete a review object.
     *
     * @param id - the object id of the review being deleted.
     */
    public void deleteReview(@Nonnull ObjectId id) {
        log.debug("ReviewController > deleteReview(...)");
        reviews.delete(id);
    }
}
