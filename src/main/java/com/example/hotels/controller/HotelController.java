package com.example.hotels.controller;

import com.example.hotels.model.Hotel;
import com.example.hotels.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hotel/")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping
    public Iterable<Hotel> getAll(){
        return hotelService.findByOrderByNameAsc();
    }

    @GetMapping("/{id}")
    public Optional<Hotel> get(@PathVariable Integer id){
        return  hotelService.findById(id);
    }

    @PutMapping
    public ResponseEntity<Hotel> update(@RequestBody Hotel hotel){
        boolean hotelExist = hotelService.findById(hotel.getId()).isPresent();
        if(hotelExist){
            return ResponseEntity.ok().body(hotel);
        }
        return ResponseEntity.badRequest().body(hotel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel create(@Valid @RequestBody Hotel hotel){
        return hotelService.save(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        hotelService.deleteById(id);
    }

    @GetMapping("/search")
    public Iterable<Hotel> search(@RequestParam(value="name", required = false) String name, @RequestParam(value="address", required = false) String address){
        if(name!= null && address!=null) {
            return hotelService.findByNameAndAddress(name, address);
        }
        if(name != null){
            return hotelService.findByName(name);
        }
        if(address != null ){
            return hotelService.findByAddress(address);
        }
        return hotelService.findAll();
    }

}
