package service;

import object.Game;
import object.GameType;
import object.Query;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.*;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class PlayerTypeService implements GameService{
    private final Game game = new Game();
    private final Path filePath = Path.of("service/logs/player_style.txt");

    @Override
    public void startGame() throws IOException {
        GameLogService gameLogService = new GameLogService(new BufferedWriter(new FileWriter(filePath.toFile(), true)), game);
        CustomFileReader customFileReader = new CustomFileReader(filePath);
        BoundaryService boundaryService = new BoundaryService();
        CustomRandomGenerator customRandomGenerator = new CustomRandomGenerator();
        //2.1
        game.setId(UUID.randomUUID());
        //2.2
        game.setGameType(GameType.PLAYER);
        game.setDateCreation(LocalDateTime.now());
        //2.3
        game.setQuery(customFileReader.querySearcher());
        //2.4
        System.out.println("Игра номер " + game.getQuery());
        List<Character> generatedLine = customRandomGenerator.randomGeneratedStringOf4();
        StringBuilder charSequenceForGenerated = new StringBuilder(String.valueOf(generatedLine.get(0)) + generatedLine.get(1) + generatedLine.get(2) + generatedLine.get(3));
        gameLogService.logGameInfo(charSequenceForGenerated);
        Scanner scanner = new Scanner(System.in);
        //2.5

        int stepCounter = 0;
        while(true){
            System.out.println("Введите набор из 4 разных цифр без пробелов");
            Query query = new Query();
            String inputLine = scanner.nextLine();
            char[] charTmp = inputLine.toCharArray();
            //2.6
            List<Character> inputSetLine = new ArrayList<>();
            for (char symbol:charTmp) {
                inputSetLine.add(symbol);
            }
            boundaryService.bounderQueryRequest(generatedLine, inputSetLine, gameLogService, query);
            stepCounter++;
            //2.7
            if(generatedLine.equals(inputSetLine)){

                //2.7.1.1
                System.out.println("Поздравляем! Вы угадали за " + (stepCounter != 0 ? stepCounter +" ход(-ов)" : "сразу") + "!\nЗагаданный набор символов: " + generatedLine.get(0) + generatedLine.get(1) + generatedLine.get(2) + generatedLine.get(3));
                //2.7.1.2
                game.setStepAmount(stepCounter);
                //2.7.1.3
                gameLogService.logAttemptsAmount();
                //2.7.1.4
                OutputSystemInfo.winCondition(GameType.PLAYER);
                break;
            }
        }
    }
}
