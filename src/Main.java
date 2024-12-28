import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Prompt to input the number
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter 6 digit number:");
        String ticketNumber = sc.nextLine();

        if (validness(ticketNumber,true)) {
            if (isLucky(ticketNumber)) {
                System.out.println("You are the winner");
            } else {
                System.out.println("Bad luck with these numbers, lets generate you something better.");
                Random random = new Random();
                boolean isGenerating = true;
                int counter = 0;
                while (isGenerating) {
                    counter++;

                    int number = random.nextInt(1000000);
                    String generatedNumber = String.format("%06d", number);

                    if (validness(generatedNumber,false)) {
                        if (isLucky(generatedNumber)) {
                            isGenerating = false;
                            System.out.println("You lucky number is " + generatedNumber + " and it took " + counter + " cycles to generate it.");

                        }
                    }
                }
            }


        }
    }

    private static boolean validness(String ticketNumber, boolean showErrors) {
        //Checking if inputted number are correct
        if (!ticketNumber.matches("\\d+")) {//Only ints are allowed
            if (showErrors) {
                System.out.println("Input is not a valid number!");
            }
            return false;
        } else if (ticketNumber.length() != 6) {//Check if length is correct
            if (showErrors) {
                System.out.println("Number must be exactly 6 digits!");
            }
            return false;
        }
        //Checking uniqueness of entered number
        String refNumber = "";

        for (int i = 0; i < ticketNumber.length(); i++) {
            if (i == 0) {
                refNumber = String.valueOf(ticketNumber.charAt(i));
            } else {
                for (int j = 0; j < refNumber.length(); j++) {
                    if (refNumber.charAt(j) == ticketNumber.charAt(i)) {
                        if (showErrors) {
                            System.out.println("Number must have unique digits!");
                        }
                        return false;
                    }
                }
                refNumber = refNumber + ticketNumber.charAt(i);
            }
        }
        return true;
    }

    private static boolean isLucky(String ticketNumber) {
        //Checking if numbers are "lucky"
        int firstTriplet = 0;
        int secondTriplet = 0;

        for (int i = 0; i < ticketNumber.length(); i++) {
            if (i < 3) {
                firstTriplet += Character.getNumericValue(ticketNumber.charAt(i));
            } else {
                secondTriplet += Character.getNumericValue(ticketNumber.charAt(i));
            }
        }
        return firstTriplet == secondTriplet;
    }

}