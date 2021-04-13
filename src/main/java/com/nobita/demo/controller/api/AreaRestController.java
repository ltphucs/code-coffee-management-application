package com.nobita.demo.controller.api;

import com.nobita.demo.model.Area;
import com.nobita.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "areas")
public class AreaRestController {
    @Autowired
    AreaService areaService;

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
        if (area != null) {
            return new ResponseEntity<>(area, HttpStatus.OK);
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
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Area area) {
        Area currentArea = areaService.findByID(id);
        if (currentArea == null) {
            return new ResponseEntity<Area>(HttpStatus.NOT_FOUND);
        }
        currentArea = area;
        try {
            areaService.update(currentArea);
            return new ResponseEntity<>(currentArea, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Area>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
