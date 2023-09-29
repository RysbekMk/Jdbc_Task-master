package org.peaksoft.model;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Car {
    private Long id;
    private String model;
    private LocalDate yearOfRelease;
    private String color;

    public Car(String model, LocalDate yearOfRelease, String color) {
        this.model = model;
        this.yearOfRelease = yearOfRelease;
        this.color = color;
    }

}