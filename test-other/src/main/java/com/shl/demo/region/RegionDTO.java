package com.shl.demo.region;

import lombok.Data;

@Data
public class RegionDTO {
    private Long id;
    private String name;
    private Long parentId;
    private Integer level;
    private String code;
    // getter and setter methods
}


