package com.example.hotels.service;

import com.example.hotels.model.Hotel;
import org.springframework.data.repository.CrudRepository;


public interface HotelService extends CrudRepository<Hotel, Integer> {

    Iterable<Hotel> findByOrderByNameAsc();

    Iterable<Hotel> findByName(String name);

    Iterable<Hotel> findByAddress(String address);

    Iterable<Hotel> findByNameAndAddress(String name, String address);
}
