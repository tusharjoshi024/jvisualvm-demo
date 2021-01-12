package com.example.threaddemo.controller;

import com.example.threaddemo.service.DemoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/startProcess")
public class DemoController {

    private final DemoService service;

    @GetMapping("/heavy")
    public void startHeavyProcess(){
        service.startHeavyProcess();
    }

    @GetMapping("/light")
    public void startLightProcess(){
        service.startLightProcess();
    }
}
