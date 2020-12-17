package com.raishin.cardbatch

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableBatchProcessing
class SampleBatch() {
    @Autowired
    lateinit var jobBuilderFactory: JobBuilderFactory

    @Autowired
    lateinit var stepBuilderFactory: StepBuilderFactory

    @Autowired
    lateinit var tasklet1: Tasklet1

    @Bean
    fun sampleJob(): Job {
        return jobBuilderFactory["sampleJob"]
                .start(sampleStep1())
                .build()
    }

    @Bean
    fun sampleStep1(): Step {
        return stepBuilderFactory["sampleStep1"].allowStartIfComplete(true)
                .tasklet(tasklet1)
                .build()
    }
}

