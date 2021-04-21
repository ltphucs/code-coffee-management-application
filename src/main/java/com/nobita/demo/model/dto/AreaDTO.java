package com.nobita.demo.model.dto;

import com.nobita.demo.model.Area;
import com.nobita.demo.model.Table;
import lombok.Data;

import java.util.List;

@Data
public class AreaDTO {

    private Area area;

    private List<Table> tableList;
}
