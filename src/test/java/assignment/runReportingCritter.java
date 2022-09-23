package assignment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class runReportingCritter {

    @Test
    void testReportingCritter() throws IOException {
        String filename = "species/Food.cri";
        CritterInterpreter ci = new Interpreter();

        CritterSpecies cs = ci.loadSpecies(filename);
        ReportingCritter rc = new ReportingCritter(cs);
        for (int i=0; i<5; i++) {
            ci.executeCritter(rc);
        }
    }
}
