package com.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "vw_dashboard")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Immutable
public class Dashboard {

    @Id
    private Long id;

    @Column(name="total_vehicle")
    private Long totalVehicle;

    @Column(name="total_event")
    private Long totalEvent;
}
