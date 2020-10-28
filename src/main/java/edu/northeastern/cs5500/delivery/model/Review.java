package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;

/**
 * This is a class representing a review object. Users should be able to retrieve any fields in this
 * class or create a new review object.
 */
public class Review {
    private Integer reviewId;
    private String userName;
    private Integer restaurantId;
    private LocalDate createdTime;
    private String comment;
    private Double rating;

    /** Constructor for a Review object */
    public Review(
            Integer reviewId,
            String userName,
            Integer restaurantId,
            LocalDate createdTime,
            String comment,
            Double rating) {
        this.reviewId = reviewId;
        this.userName = userName;
        this.restaurantId = restaurantId;
        this.createdTime = createdTime;
        this.comment = comment;
        this.rating = rating;
    }

    /**
     * Pass in a string to set it as the comment for the review.
     *
     * @param String - user's comment for the review
     */
    public void setComment(String userComment) {
        this.comment = userComment;
    }

    /**
     * Pass in a double to set it as the rating for the review.
     *
     * @param Double - user's rating for the review
     */
    public void setRating(Double userRating) {
        this.rating = userRating;
    }

    /**
     * Return the reviewId.
     *
     * @return Integer - representing reviewId
     */
    public Integer getReviewId() {
        return this.reviewId;
    }

    /**
     * Return the userName who created this review.
     *
     * @return String - representing userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Return the restaurantId that is being reviewed.
     *
     * @return Integer - representing restaurantId
     */
    public Integer getRestaurantId() {
        return this.restaurantId;
    }

    /**
     * Return the date when the review was created.
     *
     * @return LocalDate - representing createdTime
     */
    public LocalDate getCreatedTime() {
        return this.createdTime;
    }

    /**
     * Return the cotent of this review.
     *
     * @return String - representing comment
     */
    public String getCommet() {
        return this.comment;
    }

    /**
     * Return the rating of this review.
     *
     * @return Double - representing rating
     */
    public Double getRating() {
        return this.rating;
    }
}
