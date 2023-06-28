import object.Game;
import service.ComputerTypeService;
import service.PlayerTypeService;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
        ComputerTypeService computerTypeService = new ComputerTypeService();
        PlayerTypeService playerTypeService = new PlayerTypeService();
        Scanner scanner  = new Scanner(System.in);
        System.out.println("Какой режим игры?\nНапишите 1 если вы хотите угадать, напишите 2 если угадывает компьютер");
        String firstIn = scanner.nextLine();
        if(firstIn.equals("1")){
            playerTypeService.startGame();
        }
        if(firstIn.equals("2")){
            computerTypeService.startGame();
        }
    }
}
