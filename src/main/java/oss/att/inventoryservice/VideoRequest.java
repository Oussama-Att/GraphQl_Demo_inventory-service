package oss.att.inventoryservice;

import lombok.Data;
import oss.att.inventoryservice.dtos.CreatorRequest;

@Data
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private CreatorRequest creator;
}