package com.backend.service;

import com.backend.dto.Response;
import com.backend.entity.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    List<String> getAllOriginCity();

    List<String> getAllDestinationCity();

    Response addVehicle(Vehicle vehicle);

    Response getAvailableVehicles();
}
