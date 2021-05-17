package hu.nive.ujratervezes.kepesitovizsga.sumofdigits;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SumOfDigits {

    public int getSumOfDigits(Random rnd) {
        List<Integer> digits = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        int number = Math.abs(rnd.nextInt());
        int sum = 10;
        while (!digits.contains(sum)) {
            sum = 0;
            String numberString = Integer.toString(number);
            String[] temp = numberString.split("");
            for (String s : temp) {
                sum += Integer.parseInt(s);
            }
            number = sum;
        }
        return sum;
    }

    /* Rekurzióval:
    public static final int ASCII_CODE_OF_ZERO = 48;

    public int getSumOfDigits(Random random) {
        int number = random.nextInt();
        return countSum(number);
    }

    private int countSum(int value) {
        value = Math.abs(value);
        if (value < 10) {
            return value;
        }

        return countSum(
                String.valueOf(value)
                        .chars()
                        .map(a -> a - ASCII_CODE_OF_ZERO)
                        .sum());
    }
     */
}
