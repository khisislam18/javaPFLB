package service;

import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
@AllArgsConstructor
public class CustomFileReader {
    private final Path path;
    public long querySearcher() throws IOException {
        File file = path.toFile();
        if(file.length() == 0){
            //2.3.1
            System.out.println("Это ваша первая игра");
            return(1L);
        }else{
            //2.3.2
            List<String> filesAccess = Files.readAllLines(path);
            List<String> gameContainsLines = filesAccess.stream().filter(x -> x.startsWith("Game")).toList();
            long c = Long.parseLong(
                    String.valueOf(gameContainsLines.get(
                            gameContainsLines.size() - 1).split(" ")[1].charAt(1)));
            return c + 1L;
        }
    }
}
