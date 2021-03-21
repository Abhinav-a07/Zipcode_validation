package com.demo.zipcodevalidator.availabilitycheck.utils;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ZipcodeProcessor {

  public List<Zipcode> stripZipcode(String zipcodeRanges) {
    String[] zipcodeIntervals = zipcodeRanges.split(" ");
    return loadZipcode(zipcodeIntervals);
  }

  public int stringToInt(String zipcode) {
    return Integer.parseInt(zipcode);
  }

  public boolean checkZipLength(int zipcode) {
    if ((int) (Math.log10(zipcode) + 1) != 5)
      return false;
    return true;
  }

  public boolean compareZipcodeRange(int lowerBound, int upperBound) {
    if (lowerBound > upperBound)
      return false;
    return true;
  }

  public boolean validateZipcodeRange(int lowerBound, int upperBound) {
    if (!checkZipLength(lowerBound) && !checkZipLength(upperBound))
      throw new IllegalArgumentException(lowerBound + " " + upperBound + ": " + "Zipcode should have 5 digits");
    if (compareZipcodeRange(lowerBound, upperBound) == false)
      throw new IllegalArgumentException(
              lowerBound + " " + upperBound + ":  " + "Zipcode lower bound should be less than upper bound");
    return true;
  }

  public Zipcode validateAndAdd(String[] zipRange) {
    if (zipRange.length != 2)
      throw new IllegalArgumentException(zipRange[0] + "Zipcode should have lower and upper bounds");
    int lowerBound = stringToInt(zipRange[0]);
    int upperBound = stringToInt(zipRange[1]);
    Zipcode zipcode = null;
    if (validateZipcodeRange(lowerBound, upperBound) == true)
      zipcode = new Zipcode(lowerBound, upperBound);
    return zipcode;
  }

  public Zipcode getZipcodeRange(String zipcodeRange) {
    return validateAndAdd(zipcodeRange.replaceAll("\\[|\\]", "").split(","));
  }

  public List<Zipcode> loadZipcode(String[] zipcodeRange) {
    List<Zipcode> zipcodesList = new LinkedList<Zipcode>();
    for (int i = 0; i < zipcodeRange.length; i++) {
      zipcodesList.add(getZipcodeRange(zipcodeRange[i]));
    }
    return zipcodesList;
  }
}
