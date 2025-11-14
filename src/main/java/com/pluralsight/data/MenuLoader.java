package com.pluralsight.data;

import com.pluralsight.drinks.*;
import java.io.*;
import java.util.*;

public class MenuLoader {

    public static List<MenuItem> loadMenu(String fileName){
        List<MenuItem> items = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String header = reader.readLine(); // skips the first line
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                int itemNumber = Integer.parseInt(parts[0].trim());
                String category = parts[1].trim();
                String name = parts[2].trim();
                double small = Double.parseDouble(parts[3].trim());
                double medium = Double.parseDouble(parts[4].trim());
                double large = Double.parseDouble(parts[5].trim());

                String cat = category.toLowerCase();
                MenuItem item;

                if (cat.contains("brew") || cat.contains("milk") || cat.contains("chilled") || cat.contains("sweet")){
                    item = new Coffee(itemNumber, category, name, small, medium, large);
                } else if (cat.contains("tea")) {
                    item = new Tea(itemNumber, category, name, small, medium, large);
                } else if (cat.contains("signature")) {
                    item = new SignatureDrink(itemNumber, category, name, small, medium, large);
                } else if (cat.contains("baked") || cat.contains("pastries")) {
                    item = new Food(itemNumber, category, name, small);
                }else {
                    item = new MenuItem(itemNumber, category, name, small, medium, large);
                }

                items.add(item);
            }
        }catch (IOException ex){
            ex.printStackTrace();;
        }
        return items;
    }
}
