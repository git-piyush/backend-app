package com.backend.service.serviceImpl;

import com.backend.dto.Response;
import com.backend.dto.VehicleDTO;
import com.backend.entity.User;
import com.backend.entity.Vehicle;
import com.backend.repositories.VehicleRepository;
import com.backend.service.VehicleService;
import com.backend.utils.UserUtility;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    // Save images in backend folder
    private static final String IMAGE_DIRECTORY_BACKEND = "uploads/images/";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserUtility userUtility;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<String> getAllOriginCity() {
        // TODO: fetch from DB if needed
        return List.of();
    }

    @Override
    public List<String> getAllDestinationCity() {
        // TODO: fetch from DB if needed
        return List.of();
    }

    @Override
    public Response addVehicle(Vehicle vehicle) {

        User user = userUtility.getLoggedInUser();
        vehicle.setUser(user);
        if (vehicle.getImageFile() != null) {
            String imagePath = saveImage(vehicle.getImageFile());
            vehicle.setImageUrl(imagePath);
        }

        vehicleRepository.save(vehicle);

        return Response.builder()
                .status(200)
                .message("Vehicle successfully added")
                .build();
    }

    @Override
    public Response getAvailableVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<VehicleDTO> vehicleDTOList = modelMapper.map(vehicleList,
                new TypeToken<List<VehicleDTO>>() {}.getType());

        return Response.builder()
                .status(200)
                .message("success")
                .vehicleDTOS(vehicleDTOList)
                .build();
    }

    // Save image to backend folder
    private String saveImage(MultipartFile imageFile) {
        if (!imageFile.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        File directory = new File(IMAGE_DIRECTORY_BACKEND);
        if (!directory.exists()) {
            directory.mkdirs(); // create parent dirs if they don't exist
        }

        String uniqueFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
        String imagePath = IMAGE_DIRECTORY_BACKEND + uniqueFileName;

        try {
            File destinationFile = new File(imagePath);
            imageFile.transferTo(destinationFile);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Failed to save image: " + ex.getMessage());
        }

        // Return relative URL for frontend
        return "/images/" + uniqueFileName;
    }
}
