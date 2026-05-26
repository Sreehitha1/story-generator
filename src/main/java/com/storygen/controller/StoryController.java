package com.storygen.controller;

import com.storygen.model.StoryRequest;
import com.storygen.model.StoryResponse;
import com.storygen.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StoryController {

    @Autowired
    private StoryService storyService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("storyRequest", new StoryRequest());
        return "index";
    }

    @PostMapping("/generate")
    @ResponseBody
    public StoryResponse generateStory(@RequestBody StoryRequest request) {
        return storyService.generateStory(request);
    }
}
