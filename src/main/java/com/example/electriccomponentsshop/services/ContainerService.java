package com.example.electriccomponentsshop.services;


import com.example.electriccomponentsshop.dto.ContainerDTO;
import com.example.electriccomponentsshop.entities.Container;


import java.util.List;

public interface ContainerService {
    ContainerDTO convertToDto(Container container);

    Container getContainerById(String id);

    List<ContainerDTO> getAllContainer();
}
