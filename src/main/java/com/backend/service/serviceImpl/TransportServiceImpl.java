package com.backend.service.serviceImpl;

import com.backend.constants.AppConstants;
import com.backend.entity.Transport;
import com.backend.repositories.TransportRepository;
import com.backend.service.TransportService;
import com.backend.utils.UserUtility;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceImpl implements TransportService {

    @Autowired
    UserUtility userUtility;

    @Autowired
    TransportRepository transportRepository;

    @Override
    @Transactional
    public Transport bookTransport(Transport transport) {

        if(transport!=null){
            transport.setPayment(AppConstants.CAT_PAYMENT_STATUS_PENDING);
            transport.setStatus(AppConstants.CAT_TRANSPORT_STATUS_UPCOMING);
            transport.setUser(userUtility.getLoggedInUser());
            transport = transportRepository.save(transport);
        }
        return transport;
    }
}
