package com.example.orienteeringapp.web.controller.mobile;

import com.example.orienteeringapp.application.service.ActivityService;
import com.example.orienteeringapp.web.controller.base.BaseActivityController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/activities")
@Tag(name = "Mobile Activities API")
public class MobileActivityController extends BaseActivityController {
    public MobileActivityController(ActivityService activityService) {
        super(activityService);
    }
}


