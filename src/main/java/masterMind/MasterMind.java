package masterMind;

import java.io.IOException;
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
        game();
    }

    public static void game() {

        //  1. Getting random number from an generateNumber method
        String content = generateNumber("4", "0", "7");


//      2. Getting guess from player
       Scanner myObj = new Scanner(System.in);

//      converting randomNumber and playerInput to an array
        String[] input;
        String[] randomNumber = content.split("");

        // 3.loop of the game
        int chances = 10;
        int turn = 0;

        while(turn < chances) {

            System.out.println("Enter your guess: ");
            String playerInput = myObj.nextLine();

            while(playerInput.length() != 4) {
                myObj = new Scanner(System.in);
                System.out.println("Your input is not 4 digits, please enter any 4 digits between 0 and 7!");
                playerInput = myObj.nextLine();
            }
            System.out.println("Your guess is  " + playerInput);
            input = playerInput.split("");



            // 4.if the player wins the game
            if (content.equals(playerInput)) {
                System.out.println("Congrats you won! You guessed the right number!");
                break;
            }
            

//            int flag = 0;
//            Integer guess_codes[][] = new Integer[4][10];
//            String guess_flags[][] = new String[4][10];
//
//
//            if (flag == 1) {
//                System.out.println("Wrong choice! Try again!");
//            }

            //5. the logic of the game
            int guessedNumbers = 0, guessedNumberswithLocation = 0, unguessed = 4;


            for (int i =0; i < randomNumber.length; i++) {
                String currentNumber = randomNumber[i];
                if (currentNumber.equals(input[i])) {

//                    guess_flags[turn][pos] = "R";
                    guessedNumberswithLocation += 1;
                    input[i] = "U";
                }else if(contains(currentNumber, input)) {
//                    guess_flags[turn][pos] = "W";
                    guessedNumbers += 1;
                    input[indexOf(currentNumber,input)] = "U";
                    }

                }
            unguessed -= (guessedNumbers + guessedNumberswithLocation);
            turn += 1;
            int remain = chances - turn;
            // System.out.println("Guessed-codes " + Arrays.toString(guess_codes));
            // 6. Printing the feedback to the player
            System.out.println("You have  " + guessedNumbers + " guessed number!");
            System.out.println("You have  " + guessedNumberswithLocation + " guessed number with correct location!");
            System.out.println("You have " + unguessed + "  unguessed number!");
            System.out.println("You have " + remain + " chances left!");

            //7.When you lose the game
            if (turn == chances) {
                System.out.println("The number was " + Arrays.toString(randomNumber));
                System.out.println("Sorry! You lost the game! Please try it again!");
               break;
           }
//           8. Asking for next guess if player still has a chance




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
//            System.out.println("Your random number is " + content);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;


    }


}
