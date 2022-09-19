package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportingCritter implements Critter {
    private CritterSpecies species;
    private int codeLine = 1;
    private int[] reg = new int[10];
    private int hungerLevel = 0;
    PrintWriter out;
    private int pendingCodeLine = 1;

    public ReportingCritter(CritterSpecies species) throws IOException {
        this.species = species;
        out = new PrintWriter(new FileWriter("src/test/java/assignment/CritterReport.txt"));
    }

    public List getCode() {
        return this.species.getCode();
    }

    public int getNextCodeLine() {
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
            return HungerLevel.HUNGRY;
        }
        else if (probability < 0.66) {
            return HungerLevel.STARVING;
        }
        else return HungerLevel.SATISFIED;
    }

    public void hop() {
        out.println("hop");
    }

    public void left() {
        out.println("left");
    }

    public void right() {
        out.println("right");
    }

    public void eat() {
        out.println("eat");
    }

    public void infect() {
        this.pendingCodeLine = 1;
        out.println("infect");
    }

    public void infect(int n) {
        this.pendingCodeLine = n;
        out.println("infect " + n);
    }

    public int getCellContent(int n) {
        // return with equal probability
        double probability = Math.random();
        if (probability < 0.2) {
            return -1;
        }
        else if (probability < 0.4) {
            return 0;
        }
        else if (probability < 0.6) {
            return 1;
        }
        else if (probability < 0.8) {
            return 2;
        }
        else return 3;
    }

    public int getOffAngle(int n) {
        // return with equal probability

        int[] arr = {-1, 0, 45, 90, 135, 180, 225, 270, 315};
        int length = arr.length;
        double probability = Math.random() * length;

        for (int i=0; i<length; i++) {
            if (probability < i+1) {
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
