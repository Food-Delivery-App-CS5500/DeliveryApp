package edu.northeastern.cs5500.delivery.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.ObjectId;


/**
 * Enum defined to categorize Cuisine Type
 */
public enum CuisineType {
    CHINESE, AFRICAN, AMERICAN, JAPANESE, INDIAN, KOREAN, VIETNAMESE
  }