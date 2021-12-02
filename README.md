# Mastermind-game

Mastermind is a code-breaking game normally played between two players. For my project, the user will play "against" the computer which will randomly select 4 number combinations.
This is a game where a player tries to guess the number combinations.\
At the end of each attempt to guess the number combinations, the computer will provide feedback whether the
player had guessed a number correctly, or/and digit correctly. A player must guess
the right number combinations within 10 attempts to win the game.

Rules of the Game:

At the start of the game the computer will randomly select a pattern of four different
numbers from a total of 8 different numbers. It may contain duplicate digits between 0 and 7.\
•A player will have 10 attempts to guess the number combinations\
• At the end of each guess, computer will provide the following response
as feedback:\
• The player had guessed a correct number\
• The player had guessed a correct number and its correct location\
• The player’s guess was incorrect

**Note that the computer’s feedback will not reveal which number the player guessed
correctly

You can check more about this game here
https://en.wikipedia.org/wiki/Mastermind_(board_game)

## Prerequisites

* JDK 11
* Maven(bundled)
* IntelliJIDEA(you can use Eclipse) or you can use Command Line

##Overview

I created this game as Maven project in  Java language and my User Interface is Command Line or Terminal of IntellijIDEA.\
First I created Mastermind class with main method which calls my play method. In play method I implemented the logic of the game.
I used Random Number Generator API (https://www.random.org/clients/http/api/) for generating 4 number combinations.
I am handling this call in separate method,then I called that method at the very beginning of the code to get randomNumber. 
I also used Scanner object from Java library to get input/guesses from player. In my play method I compare input with randomnuNumber and printed the correct feedback.
It keeps taking input from player with until player guesses correct combination or lose if it is already 10 attempts.

##Play


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
Run\
`mvn clean install`\
`mvn exec:java`

