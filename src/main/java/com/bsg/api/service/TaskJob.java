package com.bsg.api.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskJob {  
      
    public void job1() {  
        System.out.println("任务进行中。。。"+new Date());
    }  
}  