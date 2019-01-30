package com.fancyfrog.example1.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Data
@RedisHash("persons")
@EqualsAndHashCode(exclude = { "children" })
@NoArgsConstructor
public class Person {

    private @Id String id;
    private @Indexed String firstName;
    private @Indexed String lastName;
    private Gender gender;
    private Address address;
    private @Reference List<Person> children;
}
