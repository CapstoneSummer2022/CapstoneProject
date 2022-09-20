package com.example.electriccomponentsshop.services.impl;



import com.example.electriccomponentsshop.config.ModelMap;
import com.example.electriccomponentsshop.dto.ContainerDTO;
import com.example.electriccomponentsshop.entities.Container;
import com.example.electriccomponentsshop.repositories.ContainerRepository;
import com.example.electriccomponentsshop.services.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContainerServiceImpl implements ContainerService {
    @Autowired
    ContainerRepository containerRepository;
    @Autowired
    ModelMap modelMap;
    @Override
    public ContainerDTO convertToDto(Container container){
        return modelMap.modelMapper().map(container,ContainerDTO.class);
    }
    @Override
    public Container getContainerById(String id){
        try{
            int cId = Integer.parseInt(id);
            Optional<Container> containerOptional = containerRepository.findById(cId);
            if(containerOptional.isPresent()){
                return containerOptional.get();
            }
            else  throw new NoSuchElementException("Không tìm thấy ô chứa này");
        }catch (NumberFormatException e){
            throw  new NoSuchElementException("Không tìm thấy ô chứa này");
        }
    }

   @Override
   public List<ContainerDTO> getAllContainer(){
        List<Container> containerList = containerRepository.findAll();
        if(containerList.isEmpty()){
            throw new NoSuchElementException("Không có ô chứ nào");
        }else return containerList.stream().map(this::convertToDto).collect(Collectors.toList());
   }

}
