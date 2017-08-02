package com.noelchew.ncutils.others;

import java.util.Random;

/**
 * Created by noelchew on 11/23/15.
 */
public class NumberUtil {

    public static boolean getXPercentReturnTrue(int x) {
        int rand = randInt(0, 99);
        if (rand < x) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a psuedo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimim value
     * @param max Maximim value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static int[] randIntArray(int min, int max, int arrayLength) {
        int[] intArray = new int[arrayLength];
        for (int i = 0;  i < arrayLength; i++) {
            intArray[i] = randInt(min, max);
        }
        return intArray;
    }

    // generates an array with no equal adjacent elements
    public static int[] uniqueRandIntArray(int min, int max, int arrayLength) {
        int[] intArray = new int[arrayLength];
        for (int i = 0;  i < arrayLength; i++) {
            intArray[i] = randInt(min, max);
            if (i > 0 && min < max) {
                while (intArray[i] == intArray[i-1]) {
                    intArray[i] = randInt(min, max);
                }
            }
        }
        return intArray;
    }

}
