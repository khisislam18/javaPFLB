package service;


import lombok.AllArgsConstructor;
import lombok.Data;
import object.Game;
import java.time.format.DateTimeFormatter;

import java.io.BufferedWriter;
import java.io.IOException;

@AllArgsConstructor
public class GameLogService{
    BufferedWriter bufferedWriter;
    Game game;
    public void logGameInfo(StringBuilder query) throws IOException {
        bufferedWriter.write("Game №" + game.getQuery() + " " + game.getDateCreation().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " Загаданная строка: " + query.toString());
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
    public void logQueryInfo(StringBuilder userQuery, String serverReply) throws IOException {
        bufferedWriter.write("Запрос: " + userQuery.toString()
                + " Ответ: "
                + serverReply);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
    public void logAttemptsAmount() throws IOException {
        bufferedWriter.write("Строка была угадана за " + game.getStepAmount()
                + " " + (game.getStepAmount() > 9 && game.getStepAmount() < 21? "попыток":
                game.getStepAmount() % 10 == 1 ? "попытку" :
                        game.getStepAmount() % 10 > 1 && game.getStepAmount() % 10 < 5 ? "попытки"
                                : "попыток"));
        bufferedWriter.newLine();
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }
}
