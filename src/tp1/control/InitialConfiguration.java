package tp1.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InitialConfiguration {
    public static final InitialConfiguration NONE = new InitialConfiguration();

    private List<String> stringDescriptions;
    private InitialConfiguration(List<String> stringDescriptions){
        this.stringDescriptions = stringDescriptions;
    }
    private InitialConfiguration(){
    }
    public static InitialConfiguration readFromFile(String fileName) throws FileNotFoundException, IOException {
        FileReader file = new FileReader(fileName);
        Scanner scanner = new Scanner(file);
        List<String> descriptions = new ArrayList<>();

        while (scanner.hasNextLine()) {
            descriptions.add(scanner.nextLine());
        }
        return new InitialConfiguration(descriptions);
    }

    public List<String> getShipDescription() {
        return Collections.unmodifiableList(stringDescriptions);
    }
}
