package com.demo.zipcodevalidator.availabilitycheck.utils;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class ZipcodeParser {

    public List<Zipcode> sortByLowerBounds(List<Zipcode> zipcodeRangeList) {
        Collections.sort(zipcodeRangeList, new ZipcodeComparator());
        return zipcodeRangeList;
    }

    public List<Zipcode> mergeZipcodes(List<Zipcode> sortedZipCodeList) {
        List<Zipcode> mergedZipcodeList = new LinkedList<>();
        Zipcode zipcode = null;
        for (Zipcode zipcodeInterval : sortedZipCodeList) {
            if (zipcode == null)
                zipcode = zipcodeInterval;
            else {
                if (zipcode.getUpperBound() >= zipcodeInterval.getLowerBound()) {
                    zipcode.setUpperBound(Math.max(zipcode.getUpperBound(), zipcodeInterval.getUpperBound()));
                } else {
                    mergedZipcodeList.add(zipcode);
                    zipcode = zipcodeInterval;
                }
            }
        }
        mergedZipcodeList.add(zipcode);
        return mergedZipcodeList;
    }

    public LinkedList<String> constructResult(List<Zipcode> mergedZipcodeList) {
        LinkedList<String> result = new LinkedList<>();
        mergedZipcodeList.forEach(zipcode -> {
            result.add("[" + zipcode.getLowerBound() + "," + zipcode.getUpperBound() + "]");
        });
        return result;
    }
}
