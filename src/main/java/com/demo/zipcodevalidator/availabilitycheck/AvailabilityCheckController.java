package com.demo.zipcodevalidator.availabilitycheck;

import com.demo.zipcodevalidator.availabilitycheck.utils.Zipcode;
import com.demo.zipcodevalidator.availabilitycheck.utils.ZipcodeParser;
import com.demo.zipcodevalidator.availabilitycheck.utils.ZipcodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/zipcode")
public class AvailabilityCheckController {

    ZipcodeProcessor zipcodeProcessor;
    ZipcodeParser zipcodeParser;

    @Autowired
    public AvailabilityCheckController(ZipcodeProcessor zipcodeProcessor, ZipcodeParser zipcodeParser) {
        this.zipcodeProcessor = zipcodeProcessor;
        this.zipcodeParser = zipcodeParser;
    }

    @PostMapping("/validate")
    LinkedList<String> all(@RequestBody Map<String, String> inputObject) {
        String zipcodeRanges = inputObject.get("input");
        List<Zipcode> zipcodeList = zipcodeProcessor.stripZipcode(zipcodeRanges);
        List<Zipcode> sortedZipCodeList = zipcodeParser.sortByLowerBounds(zipcodeList);
        List<Zipcode> mergedZipcodeList = zipcodeParser.mergeZipcodes(sortedZipCodeList);
        return zipcodeParser.constructResult(mergedZipcodeList);
    }
}