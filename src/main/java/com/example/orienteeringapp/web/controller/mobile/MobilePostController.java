package com.example.orienteeringapp.web.controller.mobile;

import com.example.orienteeringapp.application.service.PostService;
import com.example.orienteeringapp.web.controller.base.BasePostController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/posts")
@Tag(name = "Mobile Posts API")
public class MobilePostController extends BasePostController {
    public MobilePostController(PostService postService) {
        super(postService);
    }
}


