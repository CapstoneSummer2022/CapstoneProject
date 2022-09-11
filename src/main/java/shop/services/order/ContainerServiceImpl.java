package  shop.services.order;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.config.ModelMap;
import shop.db.dto.ContainerDTO;
import shop.db.entities.Container;
import shop.db.repositories.ContainerRepository;
import shop.services.ContainerService;

import java.util.NoSuchElementException;
import java.util.Optional;

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



}