package masterMind;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


public class MasterMind {

    public static void main(String[] args) {
        System.out.println("Welcome to Mastermind Game!!!");
        System.out.println("Please enter any 4 numbers between 0 and 7");
        play();
    }

    public static void play() {

        //  1. Getting random number from an generateNumber method
        String randomNumber = generateNumber("4", "0", "7");

        //    getting instance of Scanner
       Scanner myObj = new Scanner(System.in);

//      converting randomNumber and playerInput to an array
        String[] input;
        String[] randomNumberArr = randomNumber.split("");

        // 2.loop of the game
        int chances = 10;
        int turn = 0;
        List<String> guesses = new ArrayList<>();

        while(turn < chances) {

            // 3. Getting guess from player
            System.out.println("Enter your guess: ");
            String playerInput = myObj.nextLine();


            while(playerInput.length() != 4 || !check(playerInput)) {
                System.out.println("Your input is not 4 digits, please enter any 4 digits between 0 and 7!");
                System.out.println("Enter your guess: ");
                playerInput = myObj.nextLine();
            }
            System.out.println("Your numbers are " + playerInput);
            input = playerInput.split("");

            // 4.if the player wins the game
            if (randomNumber.equals(playerInput)) {
                System.out.println("Congrats you won! You guessed the right number!");
                break;
            }

            //5. the logic of the game
            int guessedNumbers = 0, guessedNumberswithLocation = 0, unguessed = 4;

            for (int i =0; i < randomNumberArr.length; i++) {
                String currentNumber = randomNumberArr[i];
                if (currentNumber.equals(input[i])) {
                    guessedNumberswithLocation += 1;
                    input[i] = "U";
                }else if(contains(currentNumber, input)) {
                    guessedNumbers += 1;
                    input[indexOf(currentNumber,input)] = "U";
                    }

                }

            unguessed -= (guessedNumbers + guessedNumberswithLocation);
            guesses.add(playerInput);
            turn += 1;
            int remain = chances - turn;


            //6.When you lose the game
            if (turn == chances) {
                System.out.println("The number was " + Arrays.toString(randomNumberArr));
                System.out.println("Sorry! You lost the game! Please try it again!");
                break;
            }

            // 7. Printing the feedback to the player
            System.out.println("You have  " + guessedNumbers + " guessed number!");
            System.out.println("You have  " + guessedNumberswithLocation + " guessed number with correct location!");
            System.out.println("You have " + unguessed + "  unguessed number!");
            System.out.println("You have " + remain + " chances left!");
            System.out.println("Your recent guesses " + guesses);
        }
    }

// Implementing contains method
    public static boolean contains(String input, String... arr) {

        for(int i=0; i < arr.length; i++)
            if(arr[i].equals(input)) return true;

        return false;
    }

//  Implementing indexOf method
    public static int indexOf(String input, String[] arr) {

        for(int i=0; i < arr.length; i++)
           if(arr[i].equals(input)) return i;

        return -1;
    }

//    Making an API call
    public static String generateNumber(String  num,  String min, String max) {
        String content = "";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://www.random.org/integers/?num=" + num + "&min=" + min + "&max=" + max + "&col=1&base=10&format=plain&rnd=new");
        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            content = EntityUtils.toString(entity);
            content = content.replaceAll("\\s+", "");
            //System.out.println("Your random number is " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;


    }

//    checking for unvalid inputs
    public static boolean check(String arr) {
        for (int i = 0; i < arr.length(); i++) {
            boolean flag = Character.isDigit(arr.charAt(i));
            if(flag) {
                if (Character.getNumericValue(arr.charAt(i)) < 0 || Character.getNumericValue(arr.charAt(i)) >= 8) {
                    return false;
                }else {
                    continue;
                }
            }else
                return  false;

        }
        return true;
    }

}
