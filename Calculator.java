import java.util.HashMap;
import java.util.Scanner;

class Main {
    public static String calc(String input) {
        String[] tokens = input.split(" ");

        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid input format");
        }

        String operand1 = tokens[0];
        String operator = tokens[1];
        String operand2 = tokens[2];

        if (!isValidOperand(operand1) || !isValidOperand(operand2)) {
            throw new IllegalArgumentException("Invalid operands");
        }

        int num1 = convertToNumber(operand1);
        int num2 = convertToNumber(operand2);

        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Invalid operator");
        }

        if (isRoman(operand1) && isRoman(operand2)) {
            if (result <= 0) {
                throw new IllegalArgumentException("Roman result must be positive");
            }
            return toRoman(result);
        }

        return String.valueOf(result);
    }

    private static boolean isValidOperand(String operand) {
        return operand.matches("[IVX]{1,3}") || operand.matches("[1-9]|10");
    }

    private static boolean isRoman(String operand) {
        return operand.matches("[IVX]{1,3}");
    }

    private static int convertToNumber(String operand) {
        if (isRoman(operand)) {
            return fromRoman(operand);
        } else {
            return Integer.parseInt(operand);
        }
    }

    private static String toRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number out of range (1-3999)");
        }

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");

        StringBuilder roman = new StringBuilder();
        int[] numbers = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        int i = 0;
        while (number > 0) {
            if (number - numbers[i] >= 0) {
                roman.append(map.get(numbers[i]));
                number -= numbers[i];
            } else {
                i++;
            }
        }
        return roman.toString();
    }

    private static int fromRoman(String roman) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = map.get(roman.charAt(i));
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println(">>>");

        String num = myObj.nextLine();

        try {
            System.out.println(calc(num));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
