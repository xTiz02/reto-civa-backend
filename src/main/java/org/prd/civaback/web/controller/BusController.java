package org.prd.civaback.web.controller;

import org.prd.civaback.persistence.dto.PageResponse;
import org.prd.civaback.persistence.entity.BusEntity;
import org.prd.civaback.service.BusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping
    public ResponseEntity<PageResponse> getBusPage(
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        return ResponseEntity.ok(busService.getAllBuses(page,size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusEntity> getBusDetail(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(busService.getBusById(id));
    }


}