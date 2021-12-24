package com.lession1;


import java.util.ArrayList;
import java.util.List;

public class NewYearCandyBox implements BoxWithCandiesCreator {
    private List<Candy> candies = new ArrayList<>();

    public List<Candy> getCandies() {
        return candies;
    }

    @Override
    public void addCandy(Candy candy) {
        candies.add(candy);
    }

    @Override
    public void deleteCandyByIndex(List<Candy> list, int index) {
        candies.remove(index);
    }

    @Override
    public void deleteCandyLast(List<Candy> list) {
        candies.remove(list.size() - 1);
    }

    @Override
    public void printTotalWeightOfBox() {

        System.out.println(weightOfBox());

    }

    @Override
    public void printTotalPriceOfBox() {
        double totalPriceOfBox = 0;
        for (Candy candy : candies) {
            totalPriceOfBox += candy.price;
        }
        System.out.println(totalPriceOfBox);
    }

    @Override
    public void printInformationAboutBox() {
        for (Candy candy : candies) {
            System.out.printf("Название: %s, цена: %.2f, вес: %.2f, уникальность: %s \n", candy.name, candy.price, candy.weight, candy.uniqueness);
        }

    }

    @Override
    public void optimisationByWeight(double maxWeight) {
        while (weightOfBox() > maxWeight) {
            int id = 0;
            double minWeight = candies.get(0).weight;
            for (int i = 1; i < candies.size(); i++) {
                if (candies.get(i).weight < minWeight) {
                    minWeight = candies.get(i).weight;
                    id = i;
                }
            }
            candies.remove(id);
        }
    }

    @Override
    public void optimisationByPrice(double maxWeight) {
        while (weightOfBox() > maxWeight) {
            int id = 0;
            double minPrice = candies.get(0).price;
            for (int i = 1; i < candies.size(); i++) {
                if (candies.get(i).price < minPrice) {
                    minPrice = candies.get(i).price;
                    id = i;
                }
            }
            candies.remove(id);
        }

    }

    public double weightOfBox() {
        double totalWeightOfCandies = 0;
        for (Candy candy : candies) {
            totalWeightOfCandies += candy.weight;
        }
        return totalWeightOfCandies;
    }
}
