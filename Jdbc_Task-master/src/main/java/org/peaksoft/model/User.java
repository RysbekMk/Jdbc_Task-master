package org.peaksoft.model;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
    private Long carId;


    public User(String name, String lastName, Byte age, Long carId) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.carId = carId;
    }

}