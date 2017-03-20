package org.d_m_n.callspider.callspider.tools;

import java.util.Random;

/**
 * Created by d1m11n on 3/17/17.
 */

public class RandomGenerator {


    public static String randomString(int max) {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(max);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

}
