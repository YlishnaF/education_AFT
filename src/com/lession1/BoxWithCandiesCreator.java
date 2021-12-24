package com.lession1;

import java.util.List;

public interface BoxWithCandiesCreator {
    void addCandy(Candy candy);
    void deleteCandyByIndex(List<Candy> list, int index);
    void deleteCandyLast(List<Candy> list);
    void printTotalWeightOfBox();
    void printTotalPriceOfBox();
    void printInformationAboutBox();
    void optimisationByWeight(double maxWeight);
    void optimisationByPrice(double maxWeight);
}
