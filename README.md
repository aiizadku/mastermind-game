# Mastermind-game

Mastermind is a code-breaking game normally played between two players. For my project, the user will play "against" the computer which will randomly select 4 number combinations.
This is a game where a player tries to guess the number combinations.\
At the end of each attempt, the computer will provide feedback whether the
player had guessed a number correctly, or/and digit correctly. A player must guess
the right number combinations within 10 attempts to win the game.

Rules of the Game:

At the start of the game the computer will randomly select a pattern of four different
numbers from a total of 8 different numbers between 0 and 7.

• A player will have 10 attempts to guess the number combinations.\
• At the end of each guess, computer will provide the following response
as feedback:
1. The player had guessed a correct number
2. The player had guessed a correct number and its correct location
3. The player’s guess was incorrect

**Note that the computer’s feedback will not reveal which number the player guessed
correctly\
**Note that the number combination may contain duplicate numbers

You can check more about this game here
[Mastermind (Board game)](https://en.wikipedia.org/wiki/Mastermind_(board_game)).

## Prerequisites

* JDK 11
* Apache Maven 3.8.4
* IntelliJIDEA(you can use Eclipse) or you can use Command Line

## Overview

I created this game as Maven project in Java programming language and my User Interface is Command Line or Terminal of IntellijIDEA.\
First I created Mastermind class with `main` method which calls my `play` method of GameManager class. In `play` method I implemented the logic of the game.
I used Random Number Generator API (https://www.random.org/clients/http/api/) for generating 4 number combinations.
I am handling this call in separate method, then I called that method at the very beginning of the code to get randomNumber. 
I also created `Scanner` object from Java library to get input/guesses from player. In my `play` method I compare input with randomNumber and print out feedback.
It keeps taking input from player until player guesses correct combinations and wins or loses if no more attempts left.

## Play/Run
 I added `maven exec plugin`, so user can be able to run locally using Command Line.

 ```<build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>exec-maven-plugin</artifactId>
                <version>3.0.0</version>
                <configuration>
                    <mainClass>masterMind.MasterMind</mainClass>
                    <arguments>
                        <argument>argument1
                        </argument>
                    </arguments>
                </configuration>
            </plugin>
        </plugins>
    </build>
```
After cloning the repo you can open it in IntellijIDEA and press the "Run" button, and you can see that game starts on the Terminal.
You can start playing putting your guesses

OR

In your Command Line:

`mvn clean install`

`mvn exec:java`

This will run the Maven project, and you will be able to play the game using Command Line.

## Helpful links

• [OpenJDK](https://openjdk.java.net/)

• [Apache Maven](https://maven.apache.org/download.cgi)

• [Maven Exec Plugin](https://www.mojohaus.org/exec-maven-plugin/)


• [How to play Mastermind Game](https://www.youtube.com/watch?v=5jtcsBERDEQ)