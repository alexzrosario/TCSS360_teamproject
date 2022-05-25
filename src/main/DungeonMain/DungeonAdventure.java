package main.DungeonMain;

import main.Controller;
import main.DungeonCharacter.Hero;
import main.DungeonCharacter.Monster;

import java.io.*;
import java.util.Scanner;

public class DungeonAdventure implements Serializable {
    private static final long serialVersionUID = 3536060713340084481L;
    private Controller controller = new Controller(this);
    public void start(){
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
                    controller.useLoad();
                    play();
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
        name = scan.next();
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
            case "1" -> {
                controller.startGame(name, 1);
            }
            case "2" -> {
                controller.startGame(name, 2);
            }
            case "3" -> {
                controller.startGame(name, 3);
            }
            case "4" -> {
                controller.startGame(name, 4);
            }
            case "5" -> {
                controller.startGame(name, 5);
            }
            case "6" -> {
                controller.startGame(name, 6);
            }
            case "7" -> {
                controller.startGame(name, 7);
            }
            case "8" -> {
                controller.startGame(name, 8);
            }
            case "9" -> {
                controller.startGame(name, 9);
            }
            default -> {
                System.out.println("Invalid Input");
            }
        }
    }

    public int dungeonSizePrompt() {
        int theSize;
        System.out.println("Enter the size of your dungeon; The number inputted is for rows and columns");
        System.out.println("Number be a minimum of 3, and a maximum of 10");
        Scanner scan = new Scanner(System.in);
        theSize = scan.nextInt();
        while (theSize < 3 || theSize > 10) {
            System.out.println("Invalid Input. Try Again");
            System.out.println("Number be a minimum of 3, and a maximum of 10");
            theSize = scan.nextInt();
        }
        return theSize;
    }

    public String difficultyPrompt() {
        String theDifficulty;
        System.out.println("Select your difficulty");
        System.out.println("1: Easy");
        System.out.println("2: Normal");
        System.out.println("3: Hard");
        Scanner scan = new Scanner(System.in);
        theDifficulty = scan.next();
        switch(theDifficulty) {
            case "1" -> {
                return "EASY";
            }
            case "2" -> {
                return "NORMAL";
            }
            case "3" -> {
                return "HARD";
            }
            default -> {
                return "Invalid Input";
            }
        }
    }

    public void play(){
        //while(controller.isAlive()) {
            controller.traverse();
        //}
    }

    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/savefile.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(controller);
            out.close();
            fileOut.close();
            System.out.println("Your game has been saved");
        } catch (IOException i) {
            i.printStackTrace();
        }
        controller = null;
    }

    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("src/savefile.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            controller = (Controller) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Controller class not found");
            c.printStackTrace();
        }
        System.out.println(controller.getMyDungeon());
        play();
    }

}
