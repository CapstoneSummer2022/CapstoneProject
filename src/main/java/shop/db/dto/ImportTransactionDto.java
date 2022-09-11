package shop.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportTransactionDto {
    private String sku;
<<<<<<< HEAD:src/main/java/shop/db/dto/ImportTransactionDto.java
=======
    private String supplierId;
    private List<ImportItemDto> importItems;
    private String importDate;
    private WarehouseDTO warehouseDTO;
>>>>>>> 9e7c550 (ImportTransaction):src/main/java/com/example/electriccomponentsshop/dto/ImportTransactionDto.java
}
