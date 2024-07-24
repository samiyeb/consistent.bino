package bino.consistent.grind.controllers;
import bino.consistent.grind.entities.*;
import bino.consistent.grind.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progresses")
public class ProgressController {
    @Autowired
    private ProgressService progressService;

    @PostMapping
    public Progress createProgress(@RequestBody Progress progress) {
        return progressService.createProgress(progress);
    }

}
