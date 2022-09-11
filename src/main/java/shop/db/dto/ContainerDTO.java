package shop.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContainerDTO {
    private String id;
    private String shelf;
    private String rowIn;
    private String columnIn;
}
