package com.example.orienteeringapp.web.controller.web;

import com.example.orienteeringapp.application.service.PostService;
import com.example.orienteeringapp.web.controller.base.BasePostController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/posts")
@Tag(name = "Web Posts API")
public class WebPostController extends BasePostController {
    public WebPostController(PostService postService) {
        super(postService);
    }
}


