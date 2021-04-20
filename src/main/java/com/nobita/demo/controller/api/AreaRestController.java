package com.nobita.demo.controller.api;

import com.nobita.demo.model.dto.AreaDTO;
import com.nobita.demo.model.Area;
import com.nobita.demo.model.Table;
import com.nobita.demo.service.AreaService;
import com.nobita.demo.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/areas")
public class AreaRestController {
    @Autowired
    AreaService areaService;
    @Autowired
    TableService tableService;

    @GetMapping
    public ResponseEntity<?> list() {
        List<Area> areas = areaService.findAll();
        if (!areas.isEmpty()) {
            return new ResponseEntity<>(areas, HttpStatus.OK);
        }
        return new ResponseEntity<List<Area>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Area area = areaService.findByID(id);
        List<Table> tables = tableService.findByArea(id);

        AreaDTO areaDTO = new AreaDTO();

        areaDTO.setArea(area);
        areaDTO.setTableList(tables);
        if (area != null) {
            return new ResponseEntity<>(areaDTO, HttpStatus.OK);
        }
        return new ResponseEntity<Area>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Area area) {
        try {
            areaService.save(area);
            return new ResponseEntity<>(area, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Area>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Area area) {
        Area area1 = areaService.findByID(id);
        if (area1 != null) {
            area1.setName(area.getName());
            areaService.update(area1);
            return new ResponseEntity<>(area1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        Area area = areaService.findByID(id);
        if (area != null) {
            areaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
