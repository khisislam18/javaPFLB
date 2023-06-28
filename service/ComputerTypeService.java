package service;

import object.Game;
import object.GameType;
import object.Query;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ComputerTypeService implements GameService{
    private final Game game = new Game();
    private final Path filePath = Path.of("service/logs/computer_style.txt");
    @Override
    public void startGame() throws IOException {
        GameLogService gameLogService = new GameLogService(new BufferedWriter(new FileWriter(filePath.toFile(), true)), game);
        CustomFileReader customFileReader = new CustomFileReader(filePath);
        BoundaryService boundaryService = new BoundaryService();
        List<Character> listAccessChars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listAccessChars.add((char) (i + '0'));
        }
        CustomRandomGenerator customRandomGenerator = new CustomRandomGenerator();

        game.setId(UUID.randomUUID());
        game.setGameType(GameType.COMPUTER);
        game.setDateCreation(LocalDateTime.now());
        game.setQuery(customFileReader.querySearcher());
        System.out.println("Игра номер " + game.getQuery());

        System.out.println("Введите набор из 4 разных цифр без пробелов");
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        char[] charTmp = inputLine.toCharArray();
        List<Character> inputSetLine = new ArrayList<>();
        for (char symbol:charTmp) {
            inputSetLine.add(symbol);
        }
        StringBuilder charSequenceForGenerated = new StringBuilder(String.valueOf(inputSetLine.get(0)) + inputSetLine.get(1) + inputSetLine.get(2) + inputSetLine.get(3));
        gameLogService.logGameInfo(charSequenceForGenerated);
        int stepCounter = 0;
        boolean flag = true;
        while(true){
            Query query = new Query();
            List<Character> generatedListWithAccess = customRandomGenerator.randomGeneratedStringOf4(listAccessChars);
            System.out.println("Проверка строки " + generatedListWithAccess);
            boundaryService.bounderQueryRequest(inputSetLine, generatedListWithAccess, gameLogService, query);
            stepCounter++;

            if(generatedListWithAccess.equals(inputSetLine)){
                System.out.println("Компьютер угадал за " + (stepCounter != 0 ? stepCounter +" ход(-ов)" : "сразу") + "!\nЗагаданный набор символов: " + inputSetLine.get(0) + inputSetLine.get(1) + inputSetLine.get(2) + inputSetLine.get(3));
                game.setStepAmount(stepCounter);
                gameLogService.logAttemptsAmount();
                OutputSystemInfo.winCondition(GameType.COMPUTER);
                break;
            }

            if(query.getCows() + query.getBulls() == 0){
                listAccessChars.removeAll(generatedListWithAccess);
            }else if(query.getCows() + query.getBulls() == 4 && flag){
                flag = false;
                listAccessChars = new ArrayList<>(generatedListWithAccess);
            }
        }
    }
}
