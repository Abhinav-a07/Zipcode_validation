package com.demo.zipcodevalidator.availabilitycheck.utils;

import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class ZipcodeComparator implements Comparator<Zipcode> {
    public int compare(Zipcode interval1, Zipcode interval2) {
        if (interval1.getLowerBound() > interval2.getLowerBound())
            return 1;
        else
            return -1;
    }
}
