package com.example.hotels.service;

import com.example.hotels.model.HotelReview;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HotelReviewService extends CrudRepository<HotelReview, Integer> {

    Iterable<HotelReview> findByHotelId(Integer hotelId);

    Optional<HotelReview> findByIdAndHotelId(Integer id, Integer hotelId);
}
