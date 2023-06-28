package service;

import java.io.IOException;

@FunctionalInterface
public interface GameService {

    void startGame() throws IOException;
}
