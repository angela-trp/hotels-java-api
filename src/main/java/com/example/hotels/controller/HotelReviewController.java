package com.example.hotels.controller;

import com.example.hotels.exception.ResourceNotFoundException;
import com.example.hotels.model.Hotel;
import com.example.hotels.model.HotelReview;
import com.example.hotels.service.HotelReviewService;
import com.example.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class HotelReviewController {

    @Autowired
    HotelReviewService hotelReviewService;

    @Autowired
    HotelService hotelService;

    @PostMapping("/api/v1/hotels/{id}/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public HotelReview create(@PathVariable(value="id")Integer hotelId,@Valid @RequestBody HotelReview review){
      Optional<Hotel> hotel = hotelService.findById(hotelId);
      if(hotel.isPresent()) {
          review.setHotel(hotel.get());
          return hotelReviewService.save(review);
      }
     else throw new ResourceNotFoundException("Hotel id " + hotelId + " not found");
    }

    @GetMapping("/api/v1/hotels/{id}/reviews")
    public Iterable<HotelReview> getAll(@PathVariable(value="id") Integer hotelId){
        return hotelReviewService.findByHotelId(hotelId);
    }

    @DeleteMapping("api/v1/hotels/{hotelId}/reviews/{reviewId}")
    public void delete(@PathVariable(value="hotelId") Integer hotelId, @PathVariable(value="reviewId") Integer reviewId){
        Optional<HotelReview> hotelReview = hotelReviewService.findByIdAndHotelId(reviewId, hotelId);

        if(hotelReview.isPresent()){
            hotelReviewService.delete(hotelReview.get());
        }
        else throw new ResourceNotFoundException("Hotel review with id: " + reviewId + " not found");
    }

}
