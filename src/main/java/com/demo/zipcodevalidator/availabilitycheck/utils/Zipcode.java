package com.demo.zipcodevalidator.availabilitycheck.utils;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zipcode {
    private int lowerBound;
    private int upperBound;
}
