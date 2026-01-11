package com.backend.service;

import com.backend.dto.DashboardResponse;
import com.backend.entity.Dashboard;
import org.springframework.stereotype.Service;

@Service
public interface DashboardService {
    DashboardResponse findDashboardData();
}
