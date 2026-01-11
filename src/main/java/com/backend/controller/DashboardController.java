package com.backend.controller;

import com.backend.dto.DashboardResponse;
import com.backend.dto.Response;
import com.backend.entity.Event;
import com.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getDashBoardDate(){
        try{
            DashboardResponse dashboardResponse = dashboardService.findDashboardData();
            return ResponseEntity.ok(dashboardResponse);
        }catch(Exception e){
            Response res = Response.builder().status(200).message("Some Error Occured.").build();
            return ResponseEntity.ok(res);
        }
    }

}
