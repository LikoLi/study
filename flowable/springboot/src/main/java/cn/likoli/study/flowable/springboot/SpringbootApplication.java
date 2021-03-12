package cn.likoli.study.flowable.springboot;

import cn.likoli.study.flowable.springboot.service.MyService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final MyService myService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                myService.createDemoUsers();
            }
        };
    }
}
