package assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

// confirm that malformed critter files are parsed as null without throwing exception
public class testInvalidCritterFile {

    @Test
    void testInvalidFile() throws IOException {
        String filePrefix = "species/Error";
        String fileSuffix = ".cri";

        CritterInterpreter ci = new Interpreter();

        for (int i = 0; i < 10; i++) {
            String filename = filePrefix + i + fileSuffix;
            CritterSpecies cs = ci.loadSpecies(filename);
            Assertions.assertEquals(cs, null);
        }
    }
}
