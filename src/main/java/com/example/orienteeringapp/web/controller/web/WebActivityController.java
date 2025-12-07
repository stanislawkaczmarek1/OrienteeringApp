package com.example.orienteeringapp.web.controller.web;

import com.example.orienteeringapp.application.service.ActivityService;
import com.example.orienteeringapp.web.controller.base.BaseActivityController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/activities")
@Tag(name = "Web Activities API")
public class WebActivityController extends BaseActivityController {
    public WebActivityController(ActivityService activityService) {
        super(activityService);
    }
}

