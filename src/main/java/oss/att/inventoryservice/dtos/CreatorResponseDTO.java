package oss.att.inventoryservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatorResponseDTO {
    private Long id;
    private String name;
    private String email;
}
