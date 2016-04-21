package com.example.santi.mineswiper;

/**
 * Created by santi on 21/04/16.
 */
public class Element {
    boolean isCovered;
    boolean isUncovered;
    boolean isQuestioned;
    boolean isMined;
    int numMinesAround;

    public boolean isCovered() {
        return isCovered;
    }

    public void setCovered(boolean covered) {
        isCovered = covered;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public void setUncovered(boolean uncovered) {
        isUncovered = uncovered;
    }

    public boolean isQuestioned() {
        return isQuestioned;
    }

    public void setQuestioned(boolean questioned) {
        isQuestioned = questioned;
    }

    public boolean isMined() {
        return isMined;
    }

    public void setMined(boolean mined) {
        isMined = mined;
    }

    public int getNumMinesAround(){
        return numMinesAround;
    }

    public void setNumMinesAround(int numMinesAround) {
        this.numMinesAround = numMinesAround;
    }
}
