package com.backend.controller;

import com.backend.dto.Response;
import com.backend.entity.Event;
import com.backend.enums.BookingStatus;
import com.backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
//@CrossOrigin(origins = "http://localhost:59753")
public class EventController {

    @Autowired
    private EventService eventService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Event event) {
        try{
            Event event1 = eventService.createEvent(event);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder().status(200).message("Event created successfully.").build();
        return ResponseEntity.ok(res);
    }

    // GET ALL
    @GetMapping("/all-event")
    public ResponseEntity<List<Event>> all() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Event dto) {
        try{
            eventService.updateEvent(id, dto);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder().status(200).message("Event Updated successfully.").build();
        return ResponseEntity.ok(res);
    }


    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try{
            eventService.deleteEvent(id);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder().status(200).message("Event Deleted successfully.").build();
        return ResponseEntity.ok(res);
    }

    // GET BY DEPARTMENT
    @GetMapping("/department/{dept}")
    public ResponseEntity<List<Event>> byDept(@PathVariable("dept") String department) {
        //return ResponseEntity.ok(eventService.getByDepartment(department));
        return null;
    }

    // GET BY BOOKING STATUS
    @GetMapping("/booking-status/{status}")
    public ResponseEntity<List<Event>> getByBookingStatus(
            @PathVariable BookingStatus status) {
        //return ResponseEntity.ok(eventService.getByBookingStatus(status));
        return null;
    }

}
