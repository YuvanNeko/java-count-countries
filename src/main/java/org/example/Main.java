package org.example;

import com.eclipsesource.json.JsonObject;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) throws IOException {

        AtomicInteger NumberOfContinents = new AtomicInteger();
        AtomicInteger NumberOfCountries = new AtomicInteger();
        AtomicInteger NumberOfStates = new AtomicInteger();
        AtomicInteger NumberOfCities = new AtomicInteger();

        AtomicInteger PopulationOfAll = new AtomicInteger();

        String jsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject jsonObject = JsonObject.readFrom(jsonString);


        JsonObject continent = JsonObject.readFrom(String.valueOf(jsonObject.get("continent")));

        NumberOfContinents.set(continent.size());


        continent.forEach((continentName)-> {
            JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(continentName.getName())));
            JsonObject country = JsonObject.readFrom(String.valueOf(continents.get("countries")));
            NumberOfCountries.addAndGet(country.size());
            country.forEach((countryName)->{
                JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
                JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
                NumberOfStates.addAndGet(state.size());
                state.forEach((stateName) -> {
                    JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                    NumberOfCities.addAndGet(city.size());
                    city.forEach((city_name)->{
                        JsonObject cityName = JsonObject.readFrom(String.valueOf(city.get(city_name.getName())));
                    });
                });
            });
        });

        System.out.println("Number of continents : " + NumberOfContinents);
        System.out.println("Number of countries : " + NumberOfCountries);
        System.out.println("Number of states : " + NumberOfStates);
        System.out.println("Number of cities : " + NumberOfStates);
    }
}