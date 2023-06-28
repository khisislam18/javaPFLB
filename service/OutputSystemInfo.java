package service;

import object.GameType;

import java.io.IOException;
import java.util.Scanner;

public class OutputSystemInfo {

    public static void winCondition(GameType gameType) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ComputerTypeService computerTypeService = new ComputerTypeService();
        PlayerTypeService playerTypeService = new PlayerTypeService();

        if(gameType.equals(GameType.COMPUTER)){
            System.out.println("Хотите начать игру заного? Напишите \"y\", если да,\"c\", если хотите поменять режим игры на Игрок, \"n\", если хотите выйти");
        }else{
            System.out.println("Хотите начать игру заного? Напишите \"y\", если да,\"c\", если хотите поменять режим игры на Компьютер, \"n\", если хотите выйти");
        }
        String checkOutput = scanner.next().toLowerCase();
        if(checkOutput.equals("y")){
            if(gameType.equals(GameType.COMPUTER)){
                computerTypeService.startGame();
            }else{
                playerTypeService.startGame();
            }
        } else if (checkOutput.equals("c")) {
            if(gameType.equals(GameType.COMPUTER)){
                playerTypeService.startGame();
            }else{
                computerTypeService.startGame();
            }
        }else{
            System.out.println("Выход...");
        }
    }
}
