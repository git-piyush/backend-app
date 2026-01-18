package com.backend.service.serviceImpl;

import com.backend.entity.Event;
import com.backend.enums.BookingStatus;
import com.backend.repositories.EventRepository;
import com.backend.service.EventService;
import com.backend.utils.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    UserUtility userUtility;

    @Override
    public Event updateEvent(Long id, Event event) {
        Event ex = eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        // update fields
        if(event.getRfcBookingStatus()!=null && ex.getRfcBookingStatus()!=null && (!event.getRfcBookingStatus().equals(ex.getRfcBookingStatus()))){
            ex.setRfcBookingStatus(event.getRfcBookingStatus());
        }
//        if(event.getCategory()!=null && ex.getCategory()!=null && (!event.getCategory().equals(ex.getCategory()))){
//            ex.setCategory(event.getCategory());
//        }
        if(event.getRfcDepartment()!=null && ex.getRfcDepartment()!=null && (!event.getRfcDepartment().equals(ex.getRfcDepartment()))){
            ex.setRfcDepartment(event.getRfcDepartment());
        }
        if(event.getDescription()!=null && ex.getDescription()!=null && (!event.getDescription().equalsIgnoreCase(ex.getDescription()))){
            ex.setTitle(event.getDescription());
        }
        if(event.getDueDate()!=null && ex.getDueDate()!=null && (!event.getDueDate().equals(ex.getDueDate()))){
            ex.setDueDate(event.getDueDate());
        }
        if(event.getStartDate()!=null && ex.getStartDate()!=null && (!event.getStartDate().equals(ex.getStartDate()))){
            ex.setStartDate(event.getStartDate());
        }
        if(event.getEndDate()!=null && ex.getEndDate()!=null && (!event.getEndDate().equals(ex.getEndDate()))){
            ex.setEndDate(event.getEndDate());
        }
        if(event.getRfcEventType()!=null && ex.getRfcEventType()!=null && (!event.getRfcEventType().equals(ex.getRfcEventType()))){
            ex.setRfcEventType(event.getRfcEventType());
        }
        if(event.getRfcVehiclePriority()!=null && ex.getRfcVehiclePriority()!=null && (!event.getRfcVehiclePriority().equals(ex.getRfcVehiclePriority()))){
            ex.setRfcVehiclePriority(event.getRfcVehiclePriority());
        }
        if(event.getTitle()!=null && ex.getTitle()!=null && (!event.getTitle().equalsIgnoreCase(ex.getTitle()))){
            ex.setTitle(event.getTitle());
        }
        if (event.isDepartmentEvent() != ex.isDepartmentEvent()) {
            ex.setDepartmentEvent(event.isDepartmentEvent());
        }
        if (event.isPrivateEvent() != ex.isPrivateEvent()) {
            ex.setPrivateEvent(event.isPrivateEvent());
        }
        if (event.isVehicleUpdate() != ex.isVehicleUpdate()) {
            ex.setVehicleUpdate(event.isVehicleUpdate());
        }
        return eventRepository.save(ex);
    }

    @Override
    public Event createEvent(Event event) {

        event.setUser(userUtility.getLoggedInUser());

        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }


    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    //@Override
    public List<Event> getByDepartment(String department) {

        //return eventRepository.findByDepartment(department);
        return null;
    }


   // @Override
    public List<Event> getByBookingStatus(BookingStatus bookingStatus) {
        //return eventRepository.findByBookingStatus(bookingStatus);
        return null;
    }


    //@Override
    public List<Event> getByCreatedBy(String title) {
       // return eventRepository.findByCreatedBy(title);
        return null;
    }
}

