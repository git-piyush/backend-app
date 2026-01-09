package com.backend.entity;

import com.backend.enums.PermitLevel;
import com.backend.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_vehicle")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Auto-generated Vehicle ID (Business ID)
    @Column(name = "vehicle_id", nullable = false, unique = true, updatable = false)
    private String vehicleId;

//    @PrePersist
//    public void generateVehicleId() {
//        if (this.vehicleId == null) {
//            this.vehicleId = "VH-" + UUID.randomUUID().toString().substring(0, 8);
//        }
//    }

    @Enumerated(EnumType.STRING)
    private PermitLevel permitLevel;

    @Min(value = 4, message = "Mobile Number must be at least 4 Char")
    @Column(unique = true)
    private Long driverMob;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Vehicle type is required")
    @Column(nullable = false)
    private VehicleType vehicleType;

    @DecimalMin(value = "0.1", message = "Price One Way is required")
    private Double price;

    @Min(value = 1, message = "Capacity required")
    private Integer capacity;

    private String description;

    private String imageUrl;

    private String originCity;

    private String destinationCity;

    private String vehicleRegNo;

    private Date createdDate;

    private String createdBy;

    private Date modifiedDate;

    private String modifiedBy;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Transient
    private MultipartFile imageFile;

    @PreUpdate
    @PrePersist
    public void updateTimeStamps()
    {
        String userName = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            userName = authentication.getName();
        }
        this.modifiedDate = new Date();
        this.modifiedBy = userName;
        if(this.createdDate == null) {
            this.createdDate = new Date();
            this.createdBy = userName;
        }

        if (this.vehicleId == null) {
            this.vehicleId = "VH-" + UUID.randomUUID().toString().substring(0, 8);
        }
    }

}
