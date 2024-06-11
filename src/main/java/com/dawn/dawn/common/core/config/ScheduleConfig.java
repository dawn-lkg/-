package com.dawn.dawn.common.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author chenliming
 * @date 2024/6/2 上午11:45
 */

@Configuration
public class ScheduleConfig {
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);                        // 线程池大小
        threadPoolTaskScheduler.setThreadNamePrefix("taskExecutor-");   // 线程名称
        threadPoolTaskScheduler.setAwaitTerminationSeconds(60);         // 等待时长
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);  // 调度器shutdown被调用时等待当前被调度的任务完成
        return threadPoolTaskScheduler;
    }
}
