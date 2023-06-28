package service;

import object.Query;

import java.io.IOException;
import java.util.List;

public class BoundaryService {
    public void bounderQueryRequest(List<Character> hiddenLine, List<Character> inputLine, GameLogService gameLogService, Query query) throws IOException {
        byte bullCounter = 0;
        byte cowCounter = 0;
        for (int i = 0; i < 4; i++) {
            if(hiddenLine.get(i).equals(inputLine.get(i))){
                bullCounter += 1;
            } else if (hiddenLine.contains(inputLine.get(i))) {
                cowCounter += 1;
            }
        }
        query.setCows(cowCounter);
        query.setBulls(bullCounter);
        String serverReply = cowCounter
                + (cowCounter == 1 ? " корова " : " коровы ")
                + bullCounter
                + (bullCounter == 1 ? " бык" : " быка");
        System.out.println(serverReply);
        StringBuilder charSequenceForQuery = new StringBuilder(String.valueOf(inputLine.get(0)) + inputLine.get(1) + inputLine.get(2) + inputLine.get(3));
        gameLogService.logQueryInfo(charSequenceForQuery, serverReply);
    }
}
