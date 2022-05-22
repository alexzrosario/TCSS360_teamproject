package main.DungeonMain;

import main.Controller;
import main.DungeonCharacter.Hero;
import main.DungeonCharacter.Monster;

import java.io.*;
import java.util.Scanner;

public class DungeonAdventure implements Serializable {
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
            default -> {
                System.out.println("Invalid Input");
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
    }

}
