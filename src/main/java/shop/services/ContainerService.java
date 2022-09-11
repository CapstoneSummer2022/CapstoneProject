package shop.services;


import shop.db.dto.ContainerDTO;
import shop.db.entities.Container;

public interface ContainerService {
    ContainerDTO convertToDto(Container container);

    Container getContainerById(String id);

}
