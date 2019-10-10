package com.tsg.fischer.vending_machine_spring.dto;

public class ChangePurse {
    private int numPennies, numNickels, numDimes, numQuarters;

    public ChangePurse() {
        this.numPennies = 0;
        this.numNickels = 0;
        this.numDimes = 0;
        this.numQuarters = 0;
    }

    public ChangePurse(int numPennies, int numNickels, int numDimes, int numQuarters) {
        this.numPennies = numPennies;
        this.numNickels = numNickels;
        this.numDimes = numDimes;
        this.numQuarters = numQuarters;
    }

    public ChangePurse(int[] numCoins) {
        this.numPennies = numCoins[0];
        this.numNickels = numCoins[1];
        this.numDimes = numCoins[2];
        this.numQuarters = numCoins[3];
    }

    public int getNumPennies() {
        return numPennies;
    }

    public void setNumPennies(int numPennies) {
        this.numPennies = numPennies;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public void setNumNickels(int numNickels) {
        this.numNickels = numNickels;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public void setNumDimes(int numDimes) {
        this.numDimes = numDimes;
    }

    public int getNumQuarters() {
        return numQuarters;
    }

    public void setNumQuarters(int numQuarters) {
        this.numQuarters = numQuarters;
    }
}
