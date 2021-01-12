# Java Visual VM Demo

This example demonstrates the use of Java Visual VM for monitoring the utilization of CPU by java processes and figuring out the Hot-Spot method for high resource utilizing Java function / code.

## About the source code -

This code is just a dummy application that simulates PROD like situations where we may have number of heavy and light processes executing. 
It is a Spring Boot application that provides two endpoints for spawning heavy and light application processes.

## Steps to use the tool and start monitoring -

1. Go to java installation folder and then go inside "bin" directory
  ######
    cd <java_installation_path>/bin
    
2. Locate and execute the file jvisualvm 
  ######
    sh jvisualvm
  OR for Windows
  ######
    jvisualvm.exe

3. If you do not have jvisualvm - download it from https://visualvm.github.io/download.html, extract the zip distribution and locate jvisualvm in bin directory, execute the jvisualvm as in step 2
  ######
    cd <jvisualvm_installation_path>/bin

> Post Java 8, jvisualvm is not part of the Java distribution, so if you are using Java 9 or higher, you will need to explicitly download it from their official github link given above.

4. You should be seeing this window - 
<img src="img/jvisualvm_screen.png" width="750"> 

## Steps for Demo - 

### A. Setup the app and do some damage!

1. Clone the repository 
  ######
    git clone git@github.com:tusharjoshi024/jvisualvm-demo.git
> Or you can use your own project

2. Start the project  
  ######
    gradle bootRun

3. You should be seeing your application name in the jvisualvm like this - 
<img src="img/app_stats.png" width="750">


4.  Double click on the app name to access the stats for the same.

5. Open your browser and access the application on 
  ######
     http://localhost:8080/

6. Now you can spawn some light processes first
<img src="img/lp-call.png" width="750">
> Or you can use "curl" command.

7. Observe the CPU utilization 
  ###### For Linux
     top -H
  
  ###### For Windows
     Start the "Task Manager"
     
8. Currently, CPU utilization is not spiked due to spawning a lightweight process.

9. Let's spawn heavy process similarly.
<img src="img/hp-call.png" width="750">

10. Check the CPU utilization again
>  if it is not enough, repeat Step-9 till your CPU utilization hits 95%-100% mark

<img src="img/CPU_at_almost_full_cap.png" width="750">


### B. Figure out which method/java code is damaging our "PROD" environment

1. Go to jvisualvm, and open the stats for our application

2. Click on "Monitor" tab to see various JVM related stats
<img src="img/monitor.png" width="750">

3. To check which threads are currently active, click on "Threads" tab.
We can see the list of threads spawned by our application. 
<img src="img/processes.png" width="750">
 
**4. To check thread dumps -** 
    
    4.1. Click on the "Thread Dump" button.
<img src="img/dmp_button.png" width="750">
    
    4.2. Dumps are opened in new tab, after navigating to this new tab, we can see thread dumps for our application
<img src="img/dump_heavy.png" width="750">
<img src="img/dump_light.png" width="750">
 
**5. To determine which java code is causing spikes in CPU utilization, we can follow these steps -** 
 <img src="img/CPU_Utilization_by_process.png" width="750">
 
    5.1. Click on "Sampler" tab.
    5.2. Click on CPU button.
    5.3. Sort the list by "Total Time (CPU)" property.
    5.4. We can see that HeavyProcess.run() is utilizing the CPU for most of the time and hence causing spike in total CPU consumption for our machine. 
    5.5. Also, see that LightProcess.run() is also listed here, but it is not utilizing the CPU for that much, so it is down in the list (marked with green)
    
### Conclusion - 
Here we can see that jvisualvm can be a great artillery in our arsenal if  we run into such situations where resources are being utilized over capacity, and we need to figure out the root cause of the same. 
