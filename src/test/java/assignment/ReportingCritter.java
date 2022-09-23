package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ReportingCritter implements Critter {
    private CritterSpecies species;
    private int codeLine = 1;
    private int[] reg = new int[10];
    private int hungerLevel = 0;
    PrintWriter out;
    private int pendingCodeLine = 1;
    public ArrayList<String> output;

    public ReportingCritter(CritterSpecies species) throws IOException {
        this.species = species;
        out = new PrintWriter(new FileWriter("src/test/java/assignment/CritterReport.txt"));
        output = new ArrayList<String>();
    }

    public List getCode() {
        return this.species.getCode();
    }

    public int getNextCodeLine() {
        System.out.println("codeline: " + this.codeLine);
        return this.codeLine;
    }

    public void setNextCodeLine(int var1) {
        this.codeLine = var1;
    }

    public int getReg(int reg) {
        if (reg > 0 && reg <= 10) return this.reg[reg-1];
        return 0;
    }

    public void setReg(int reg, int val) {
        if (reg > 0 && reg <= 10) this.reg[reg-1] = val;
    }

    public Critter.HungerLevel getHungerLevel() {
        // return with equal probability
        double probability = Math.random();
        if (probability < 0.33) {
            System.out.println("HUNGRY");
            return HungerLevel.HUNGRY;
        }
        else if (probability < 0.66) {
            System.out.println("STARVING");
            return HungerLevel.STARVING;
        }
        else {
            System.out.println("SATISFIED");
            return HungerLevel.SATISFIED;
        }
    }

    public void hop() {
        output.add("hop");
        out.println("hop");
        System.out.println("hop");
    }

    public void left() {
        output.add("left");
        out.println("left");
        System.out.println("left");
    }

    public void right() {
        output.add("right");
        out.println("right");
        System.out.println("right");
    }

    public void eat() {
        output.add("eat");
        out.println("eat");
        System.out.println("eat");
    }

    public void infect() {
        this.pendingCodeLine = 1;
        output.add("infect");
        out.println("infect");
        System.out.println("infect");
    }

    public void infect(int n) {
        this.pendingCodeLine = n;
        output.add("infect " + n);
        out.println("infect " + n);
        System.out.println("infect " + n);
    }

    public int getCellContent(int n) {
        // return with equal probability
        double probability = Math.random();
        if (probability < 0.2) {
            System.out.println("BAD");
            return -1;
        }
        else if (probability < 0.4) {
            System.out.println("EMPTY");
            return 0;
        }
        else if (probability < 0.6) {
            System.out.println("WALL");
            return 1;
        }
        else if (probability < 0.8) {
            System.out.println("ENEMY");
            return 2;
        }
        else {
            System.out.println("ALLY");
            return 3;
        }
    }

    public int getOffAngle(int n) {
        // return with equal probability

        int[] arr = {-1, 0, 45, 90, 135, 180, 225, 270, 315};
        int length = arr.length;
        double probability = Math.random() * length;

        for (int i=0; i<length; i++) {
            if (probability < i+1) {
                System.out.println("getOffAngle: " + arr[i]);
                return arr[i];
            }
        }
        return -1;
    }

    public boolean ifRandom() {
        if (Math.random() < 0.5) return true;
        else return false;
    }
}
