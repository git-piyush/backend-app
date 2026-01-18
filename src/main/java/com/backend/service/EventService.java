package com.backend.service;


import com.backend.entity.Event;
import com.backend.enums.BookingStatus;

import java.util.List;

public interface EventService {
    Event createEvent(Event e);

    Event updateEvent(Long id, Event e);

    List<Event> getAllEvents();

    Event getById(Long id);

    void deleteEvent(Long id);

    //List<Event> getByDepartment(String department);

    //List<Event> getByBookingStatus(BookingStatus status);

    //List<Event> getByCreatedBy(String title);
}
