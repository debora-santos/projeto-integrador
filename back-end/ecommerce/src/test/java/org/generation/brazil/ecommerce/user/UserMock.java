package org.generation.brazil.ecommerce.user;

import com.github.javafaker.Faker;

public class UserMock {
    public static User getUserMock(){
        Faker faker = new Faker();
        User user = new User();
        user.setName(faker.name().fullName());
        user.setUsername(faker.name().username());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        return user;
    }
}
