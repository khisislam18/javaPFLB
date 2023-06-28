package service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;


public class CustomRandomGenerator {
    public List<Character> randomGeneratedStringOf4() {
        Set<Character> line = new HashSet<>();
        while (line.size() < 4) {
            Random r = new Random();
            line.add((char) (r.nextInt(10) + '0'));
        }
        return line.stream().toList();
    }

    public List<Character> randomGeneratedStringOf4(List<Character> accessChars) {
        List<Character> line = new ArrayList<>();
        while (line.size() < 4) {
            Random r = new Random();
            char tmp = (char) (r.nextInt(10) + '0');
            if(accessChars.contains(tmp) && !line.contains(tmp)){
                line.add(tmp);
            }
        }
        return line;
    }
}
