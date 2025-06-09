package com.springboot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BeanNames implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        final String[] beanNames = applicationContext.getBeanDefinitionNames();
        log.info("************** Total No.of Beans: {} and below are then bean names form package 'com.springboot' ************* ", beanNames.length);

        for (String beanName: beanNames) {
            final Object beanObject = applicationContext.getBean(beanName);
            if (beanObject.getClass().getPackageName().startsWith("com.springboot"))
                log.info("bean: {}", beanName);
        }

        log.info("************* Bean names print completed ******************");
    }
}
