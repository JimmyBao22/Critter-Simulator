package assignment;

import java.io.*;

import static assignment.CreateCritters.*;

public class ReproduceCritters {

    public static void main(String[] args) throws IOException {
        String[] arr = {"hop", "hop", "hop", "hop", "hop", "hop", "hop", "hop", "left", "left", "left", "right", "right", "right",
                "infect", "infect", "infect", "eat", "eat", "eat", "go", "go", "go", "ifrandom", "ifhungry", "ifstarving", "ifempty",
                "ifally", "ifenemy", "ifwall", "ifangle", "write", "add", "sub", "inc", "dec", "iflt", "ifeq", "ifgt"};
        int n = arr.length;

        BufferedReader in = new BufferedReader(new FileReader("species/BestTestCritter.cri"));
        int k = 50;
        String[] bestCritterInstructions = new String[k];
        in.readLine();
        for (int i=0; i<k; i++) {
            bestCritterInstructions[i] = in.readLine();
        }

        for (int i=0; i<10; i++) {
            PrintWriter out = new PrintWriter("species/TestCritter" + i + ".cri");
            out.println("TestCritter" + i );

            for (int j=0; j<k; j++) {

                if (Math.random() < 0.9) {
                    // use previous critters
                    out.println(bestCritterInstructions[j]);
                    continue;
                }

                int index = (int)(Math.random() * n);
                int numInstructions = (int) (Math.random() * 100 + 10);

                switch (arr[index]) {
                    case "hop":
                        out.println("hop");
                        break;
                    case "left":
                        out.println("left");
                        break;
                    case "right":
                        out.println("right");
                        break;
                    case "infect":
                        out.println("infect " + createInstructionJump(numInstructions));
                        break;
                    case "eat":
                        out.println("eat");
                        break;
                    case "go":
                        out.println("go " + createInstructionJump(numInstructions));
                        break;
                    case "ifrandom":
                        out.println("ifrandom " + createInstructionJump(numInstructions));
                        break;
                    case "ifhungry":
                        out.println("ifhungry "+ createInstructionJump(numInstructions));
                        break;
                    case "ifstarving":
                        out.println("ifstarving "+ createInstructionJump(numInstructions));
                        break;
                    case "ifempty":
                        out.println("ifempty " + createBearing() + " " + createInstructionJump(numInstructions));
                        break;
                    case "ifally":
                        out.println("ifally " + createBearing() + " " + createInstructionJump(numInstructions));
                        break;
                    case "ifenemy":
                        out.println("ifenemy " + createBearing() + " " + createInstructionJump(numInstructions));
                        break;
                    case "ifwall":
                        out.println("ifwall " + createBearing() + " " + createInstructionJump(numInstructions));
                        break;
                    case "ifangle":
                        out.println("ifangle " + createBearing() + " " + createBearing() + " " + createInstructionJump(numInstructions));
                        break;
                    case "write":
                        out.println("write " + createRegister() + " " + (int)(Math.random() * 10));
                        break;
                    case "add":
                        out.println("add " + createRegister() + " " + createRegister());
                        break;
                    case "sub":
                        out.println("sub " + createRegister() + " " + createRegister());
                        break;
                    case "inc":
                        out.println("inc " + createRegister());
                        break;
                    case "dec":
                        out.println("dec " + createRegister());
                        break;
                    case "iflt":
                        out.println("iflt " + createRegister() + " " + createRegister() + " " + createInstructionJump(numInstructions));
                        break;
                    case "ifeq":
                        out.println("ifeq " + createRegister() + " " + createRegister() + " " + createInstructionJump(numInstructions));
                        break;
                    case "ifgt":
                        out.println("ifgt " + createRegister() + " " + createRegister() + " " + createInstructionJump(numInstructions));
                        break;
                }
            }
            out.println();
            out.println();
            out.close();
        }
    }
}