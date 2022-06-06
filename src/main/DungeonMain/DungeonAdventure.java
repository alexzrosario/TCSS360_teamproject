package main.DungeonMain;

import main.ConsoleController;
import java.io.*;
import java.util.Scanner;

public class DungeonAdventure implements Serializable {
    private static final long serialVersionUID = 3536060713340084481L;
    private ConsoleController controller = new ConsoleController(this);
    public void start() {
        boolean quit = false;
        System.out.println("Welcome to the Dungeon\n");
        String choice;
        Scanner scan = new Scanner(System.in);
        while (!quit)   {
            System.out.println("Select an option from the following:\n");
            System.out.println("To play game press: 1");
            System.out.println("To load game press: 2");
            System.out.println("To quit press: q");
            choice = scan.next();
            switch (choice){
                case "1" :
                    chooseHero();
                    play();
                    break;
                case "2" :
                    controller.loadGame();
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    break;
            }
        }
    }

    public void chooseHero() {
        Scanner scan = new Scanner(System.in);
        String name;
        String theClass;
        System.out.println("Enter your name: \n");
        name = scan.nextLine();
        boolean repeat = false;
        outerloop:
        do {
            System.out.println("Choose your class:\n");
            System.out.println("To play the Warrior: 1");
            System.out.println("To play the Priestess: 2");
            System.out.println("To play the Thief: 3");
            System.out.println("To play the Barbarian: 4");
            System.out.println("To play the Mage: 5");
            System.out.println("To play the Swordsman: 6");
            System.out.println("To play the Monk: 7");
            System.out.println("To play the Samurai: 8");
            System.out.println("To play the Occultist: 9");
            theClass = scan.next();
            switch (theClass) {
                case "1":
                    controller.startGame(name, 1);
                    break outerloop;
                case "2":
                    controller.startGame(name, 2);
                    break outerloop;
                case "3":
                    controller.startGame(name, 3);
                    break outerloop;
                case "4":
                    controller.startGame(name, 4);
                    break outerloop;
                case "5":
                    controller.startGame(name, 5);
                    break outerloop;
                case "6":
                    controller.startGame(name, 6);
                    break outerloop;
                case "7":
                    controller.startGame(name, 7);
                    break outerloop;
                case "8":
                    controller.startGame(name, 8);
                    break outerloop;
                case "9":
                    controller.startGame(name, 9);
                    break outerloop;
                default:
                    System.out.println("Invalid Input");
                    repeat = true;
            }
        }while(repeat);
    }

    public int dungeonSizePrompt() {
        int theSize = 0;
        System.out.println("Enter the size of your dungeon; The number inputted is for rows and columns");
        System.out.println("Number be a minimum of 3, and a maximum of 10");
        Scanner scan = new Scanner(System.in);
        boolean repeat = false;
        do {
            try{
                System.out.println("Enter an integer: ");
                theSize = Integer.parseInt(scan.next());
                while (theSize < 3 || theSize > 10) {
                    System.out.println("Invalid Input. Try Again");
                    System.out.println("Number be a minimum of 3, and a maximum of 10");
                    theSize = Integer.parseInt(scan.next());
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("Invalid Input. Try Again");
                repeat = true;
            }
        }while (repeat);
        return theSize;
    }

    public String difficultyPrompt() {
        String theDifficulty;
        String diff = "";
        boolean repeat = false;
        outerloop:
        do {
            System.out.println("Select your difficulty");
            System.out.println("1: Easy");
            System.out.println("2: Normal");
            System.out.println("3: Hard");
            Scanner scan = new Scanner(System.in);
            theDifficulty = scan.next();
            switch (theDifficulty) {
                case "1":
                    diff = "EASY";
                    break outerloop;
                case "2":
                    diff = "NORMAL";
                    break outerloop;
                case "3":
                    diff = "HARD";
                    break outerloop;
                default:
                    repeat = true;
            }
        }while (repeat);
        return diff;
    }

    public void play(){
        System.out.println(controller.getMyDungeon());
        controller.traverse();
    }
}
