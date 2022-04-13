package masterMind;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class GameManager {
    HashMap<String, Integer> board = new HashMap<String,Integer>();

    public void  startGame() {
        System.out.println("-------------------------------------------");
        System.out.println("      Welcome to Mastermind Game!!!");
        System.out.println("-------------------------------------------");

        int cont = 0;
        int playerNummber = 2;
        System.out.println("In this round, " + playerNummber + " players are playing!");

        while(cont < playerNummber) {
            play();
            cont++;
        }
        System.out.println(board);
        pickWinner();
    }

    private void pickWinner() {
        int min = Integer.MAX_VALUE;
        String pl = "";

        for(Map.Entry<String, Integer> entry:  this.board.entrySet()) {
            if( entry.getValue() < min) {
                min = entry.getValue();
                pl = entry.getKey();
            }
        }
        System.out.println("The winner is " + pl + " with  " + min + " tries!");
    }

    public void play() {

        //  1. Getting random number from  generateNumber method
        String randomNumber = generateNumber("4", "0", "7");

        //    creating Scanner object
        Scanner myObj = new Scanner(System.in);
        Scanner myPlayer = new Scanner(System.in);
        System.out.println("Please print your name: ");
        String playerName = myPlayer.nextLine();
        System.out.println("Player " +  playerName + " is playing!");

        System.out.println("Please enter any 4 numbers between 0 and 7");

        // converting randomNumber and playerInput to an array
        String[] input;
        String[] randomNumberArr = randomNumber.split("");

        // 2.loop of the game
        int chances = 10;
        int turn = 0;
        List<String> previousGuesses = new ArrayList<>();

        while(turn < chances) {

            // 3. Getting guess from player
            System.out.println("Enter your guess: ");
            String playerInput = myObj.nextLine();

            // check for correct input
            while(playerInput.length() != 4 || !check(playerInput)) {
                System.out.println("Your input is not 4 digits, please enter any 4 digits between 0 and 7!");
                System.out.println("Enter your guess: ");
                playerInput = myObj.nextLine();
            }
            System.out.println("Your numbers are " + "-" + playerInput + "-");
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
                }
                else if((contains(currentNumber, input)) && (randomNumberArr[indexOf(currentNumber, input)].equals(currentNumber))) {
                    guessedNumberswithLocation += 1;
                    input[indexOf(currentNumber,input)] = "U";
                }else if ((contains(currentNumber, input)) && (!randomNumberArr[indexOf(currentNumber, input)].equals(currentNumber))) {
                    guessedNumbers += 1;
                    input[indexOf(currentNumber,input)] = "U";
                }
//                System.out.println("random" + Arrays.toString(randomNumberArr));
//                System.out.println("input" +Arrays.toString(input));
            }
            unguessed -= (guessedNumbers + guessedNumberswithLocation);
            previousGuesses.add(playerInput);
            turn += 1;
            int remain = chances - turn;

            //6.When player loses the game
            if (turn == chances) {
                System.out.println("The number was " + Arrays.toString(randomNumberArr));
                System.out.println("Sorry! You lost the game! Please try it again!");
                break;
            }

            // 7. Printing the feedback to the player
            System.out.println("-------------------------------------------");
            System.out.println("     Guessed numbers with correct position: " + guessedNumberswithLocation);
            System.out.println("     Guessed numbers:  " + guessedNumbers);
            System.out.println("     Not guessed numbers: " + unguessed);
            System.out.println("-------------------------------------------");
            System.out.println("     Remain chances: " + remain);
            System.out.println("     Your recent guesses " + previousGuesses);
            System.out.println("-------------------------------------------");
//            System.out.println(randomNumber);
        }

        board.put(playerName, turn);
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
            //  getting rid of spaces
            content = content.replaceAll("\\s+", "");
            System.out.println("Your random number is " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    //    checking for invalid inputs
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
