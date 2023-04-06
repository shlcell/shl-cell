package com.shl.demo.region;

import lombok.Data;

import java.util.List;

@Data
public class RegionVO {
    private Long id;
    private String label;
    private List<RegionVO> children;
    // getter and setter methods
}
