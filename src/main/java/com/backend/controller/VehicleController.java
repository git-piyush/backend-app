package com.backend.controller;

import com.backend.dto.Response;
import com.backend.entity.Vehicle;
import com.backend.repositories.VehicleRepository;
import com.backend.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
//@CrossOrigin(origins= "http://localhost:59753")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    // ✅ Add Vehicle
    @PostMapping("/add-vehicle")
    public ResponseEntity addVehicle(@Valid @RequestBody Vehicle vehicle) {
        Response res = vehicleService.addVehicle(vehicle);
        return ResponseEntity.ok(res);
    }

    // ✅ Get All Vehicles
    @GetMapping("/all-vehicle")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleRepository.findAll());
    }

    // ✅ Get Vehicle by DB ID
    @GetMapping("/get-vehicle/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));
        return ResponseEntity.ok(vehicle);
    }

    // ✅ Get Vehicle by Business Vehicle ID (VH-xxxx)
    @GetMapping("/by-vehicle-id/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleByVehicleId(@PathVariable String vehicleId) {
        Vehicle vehicle = vehicleRepository.findByVehicleId(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with VehicleId: " + vehicleId));
        return ResponseEntity.ok(vehicle);
    }

    // ✅ Update Vehicle
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(
            @PathVariable Long id,
            @Valid @RequestBody Vehicle updatedVehicle) {

        Vehicle existing = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + id));

        existing.setPermitLevel(updatedVehicle.getPermitLevel());
        existing.setDriverMob(updatedVehicle.getDriverMob());
        existing.setVehicleType(updatedVehicle.getVehicleType());
        existing.setPrice(updatedVehicle.getPrice());
        existing.setCapacity(updatedVehicle.getCapacity());
        existing.setDescription(updatedVehicle.getDescription());
        existing.setOriginCity(updatedVehicle.getOriginCity());
        existing.setDestinationCity(updatedVehicle.getDestinationCity());
        existing.setImageUrl(updatedVehicle.getImageUrl());

        return ResponseEntity.ok(vehicleRepository.save(existing));
    }

    // ✅ Delete Vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity deleteVehicle(@PathVariable Long id) {
        try{
            if (!vehicleRepository.existsById(id)) {
                throw new RuntimeException("Vehicle not found with ID: " + id);
            }
            vehicleRepository.deleteById(id);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
        Response res = Response.builder().status(200).message("Vehicle deleted successfully").build();
        return ResponseEntity.ok(res);
    }
}
