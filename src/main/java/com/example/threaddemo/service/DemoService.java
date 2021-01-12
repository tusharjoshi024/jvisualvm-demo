package com.example.threaddemo.service;

import com.example.threaddemo.process.HeavyProcess;
import com.example.threaddemo.process.LightProcess;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class DemoService {

    private static int countHeavy=0;
    private static int countLight=0;

    public void startHeavyProcess() {
        countHeavy++;
        Thread heavyThread = new Thread(new HeavyProcess(10000));
        heavyThread.setName("Heavy-Process-"+countHeavy);
        heavyThread.start();
    }


    public void startLightProcess() {
        countLight++;
        Thread lightThread1 = new Thread(new LightProcess());
        lightThread1.setName("Light-Process-"+countLight+"-1");
        lightThread1.start();
        Thread lightThread2 = new Thread(new LightProcess());
        lightThread2.setName("Light-Process-"+countLight+"-2");
        lightThread2.start();
        Thread lightThread3 = new Thread(new LightProcess());
        lightThread3.setName("Light-Process-"+countLight+"-3");
        lightThread3.start();

    }
}
