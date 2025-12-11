package com.example.orienteeringapp.web.controller.web;

import com.example.orienteeringapp.application.service.FollowRequestService;
import com.example.orienteeringapp.web.controller.base.BaseFollowRequestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/follow-requests")
@Tag(name = "Web Follow Requests API")
public class WebFollowRequestController extends BaseFollowRequestController {
    public WebFollowRequestController(FollowRequestService followRequestService) {
        super(followRequestService);
    }
}


