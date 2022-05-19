package main.DungeonMain;

import main.Controller;
import main.DungeonCharacter.Hero;
import main.DungeonCharacter.Monster;

import java.util.Scanner;

public class DungeonAdventure {
    Controller controller = new Controller();
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
                    System.out.println("2");
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
        while(controller.isAlive()) {
            controller.checkRoom();
            traverse();
        }
    }

    private void traverse() {
        Scanner scan = new Scanner(System.in);
        boolean gameNotDone = true;
        String dir;
        while (gameNotDone)   {
            System.out.println("Select an option to move the following:\n");
            System.out.println("N for North, S for South, E for East, or W for West");
            System.out.println("H to see hero info");
            System.out.println("I to open hero inventory");
            dir = scan.next();
            switch (dir){
                case "N" :
                    controller.moveNorth();
                    break;
                case "S" :
                    controller.moveSouth();
                    break;
                case "E":
                    controller.moveEast();
                    break;
                case "W":
                    controller.moveWest();
                    break;
                case "H":
                    controller.heroInfo();
                    break;
                case "I":
                    controller.heroInventory();
                    break;
                default:
                    break;
            }
        }
    }
}
