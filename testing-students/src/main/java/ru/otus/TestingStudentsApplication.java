package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.ProcessService;

public class TestingStudentsApplication {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("/spring-context.xml");
        var processBean = context.getBean(ProcessService.class);
        processBean.showQuiz();
    }
}