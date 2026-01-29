package com.backend.dto;

import com.backend.entity.Event;
import com.backend.entity.RefCode;
import com.backend.entity.Transport;
import com.backend.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    //generic
    private int status;
    private String message;

    //for login
    private String token;
    private UserRole role;
    private Boolean isActive;
    private String expirationTime;
    private String userName;
    private String validToken;
    //user data output
    private UserDTO user;
    private List<UserDTO> users;

    private final LocalDateTime timestamp = LocalDateTime.now();

    private List<VehicleDTO> vehicleDTOS;

    private List<Event> eventList;

    private DashboardResponse dashboardResponse;

    private RefCode refCode;

    private Map<String, String> refCodeMap;

    private Map<String, Map<String,String>> refCodeMap1;

    private List<String> refCodeList;

    private Transport transport;

    private Long id;
}
