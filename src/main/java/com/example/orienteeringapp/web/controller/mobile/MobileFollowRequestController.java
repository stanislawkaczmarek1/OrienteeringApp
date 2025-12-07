package com.example.orienteeringapp.web.controller.mobile;

import com.example.orienteeringapp.application.service.FollowRequestService;
import com.example.orienteeringapp.web.controller.base.BaseFollowRequestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/follow-requests")
@Tag(name = "Mobile Follow Requests API")
public class MobileFollowRequestController extends BaseFollowRequestController {
    public MobileFollowRequestController(FollowRequestService followRequestService) {
        super(followRequestService);
    }
}

