package com.backend.service.serviceImpl;

import com.backend.dto.DashboardResponse;
import com.backend.entity.Dashboard;
import com.backend.repositories.DashboardRepository;
import com.backend.service.DashboardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public DashboardResponse findDashboardData() {

        Dashboard dashboard = dashboardRepository.findById(1L).get();

        return mapper.map(dashboard, DashboardResponse.class);
    }
}
