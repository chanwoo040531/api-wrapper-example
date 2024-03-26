package me.chnu.apiwrapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiWrapperApplicationTests {

    @Test
    void contextLoads() {


    }
}

sealed interface Animal permits Cat, Dog {
    String getName();
}

final class Dog implements Animal {
    @Override
    public String getName() {
        return "Dog";
    }
}

final class Cat implements Animal {
    @Override
    public String getName() {
        return "Cat";
    }
}