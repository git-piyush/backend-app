package com.backend.service;

import com.backend.entity.Transport;
import org.springframework.stereotype.Service;

@Service
public interface TransportService {

    Transport bookTransport(Transport transport);

}
