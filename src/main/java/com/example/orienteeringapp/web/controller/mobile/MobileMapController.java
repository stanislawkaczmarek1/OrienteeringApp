package com.example.orienteeringapp.web.controller.mobile;

import com.example.orienteeringapp.application.service.MapService;
import com.example.orienteeringapp.web.controller.base.BaseMapController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/maps")
@Tag(name = "Mobile Maps API")
public class MobileMapController extends BaseMapController {
    public MobileMapController(MapService mapService) {
        super(mapService);
    }
}


