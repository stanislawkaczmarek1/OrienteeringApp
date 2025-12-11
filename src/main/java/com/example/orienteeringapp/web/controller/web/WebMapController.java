package com.example.orienteeringapp.web.controller.web;

import com.example.orienteeringapp.application.service.MapService;
import com.example.orienteeringapp.web.controller.base.BaseMapController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/maps")
@Tag(name = "Web Maps API")
public class WebMapController extends BaseMapController {
    public WebMapController(MapService mapService) {
        super(mapService);
    }
}


