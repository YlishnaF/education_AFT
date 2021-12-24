package com.lession1;

public class Main {

    public static void main(String[] args) {

        NewYearCandyBox box = new NewYearCandyBox();
        box.addCandy(new AlenkaSweet());
        box.addCandy(new MarsChocolate());
        box.addCandy(new MorningCookies());
        box.addCandy(new AlenkaSweet());
        box.printTotalPriceOfBox();
        box.printTotalWeightOfBox();
        box.printInformationAboutBox();
        box.optimisationByPrice(150);
        box.printInformationAboutBox();
    }
}
