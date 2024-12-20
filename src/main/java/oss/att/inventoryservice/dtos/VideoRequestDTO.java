package oss.att.inventoryservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoRequestDTO {
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private CreatorRequestDTO creator;
}
