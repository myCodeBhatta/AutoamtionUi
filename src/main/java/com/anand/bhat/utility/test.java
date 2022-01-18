package com.anand.bhat.utility;

import java.util.HashMap;
import java.util.Map;

public class test {

    /**
     * @auther: anand
     * @created: 18-01-2022
     **/

    public static void main(String[] args) {


        Map<Integer, String> studentDetails = new HashMap<>();

        studentDetails.put(1, "Bhat");
        studentDetails.put(2, "Anand");
        studentDetails.put(3, null);
        studentDetails.put(null, "demo");
        studentDetails.put(null, "demoa");

        System.out.println(studentDetails);

        System.out.println(studentDetails.get(null));
    }


}
