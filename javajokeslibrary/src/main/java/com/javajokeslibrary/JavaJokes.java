package com.javajokeslibrary;

import java.util.Random;

public class JavaJokes {

    private Random random = new Random();

    public String jokeList() {

        String joke;
        switch (random.nextInt(4)) {

            case 0:
                joke = "\"The Wave\" was banned in Sanford Stadium? " +
                        "\n\n because 2 Bulldog fans drowned last year.";
                break;
            case 1:

                joke = "The difference between a Tech and a UGA fan: " +
                        "\n\n A Tech fan actually went to college.";
                break;
            case 2:
                joke = "The average UGA student gets what on his SAT? " +
                        "\n\n Drool ";
                break;
            case 3:
                joke = "Did you hear about the fire in UGA's dorm that destroyed 20 books? " +
                        "\n\n Yeah, the real tragedy was that some hadn't been colored yet.";
                break;
            default:
                joke = "On the back of every UGA diploma, it is written: " +
                        "\n\n Will work for food.";
                break;
        }

        return joke;
    }
}

