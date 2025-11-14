package com.pluralsight.data;

import com.pluralsight.drinks.*;
import java.io.*;
import java.util.*;

public class AddOnLoader {

    public static List<AddOn> loadAddOn(String _fileName){
        List<AddOn> extras = new ArrayList<>();

        // Reads Addon file
        try (BufferedReader reader = new BufferedReader(new FileReader(_fileName))){
            String header = reader.readLine(); // skips the first line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                int itemNumber = Integer.parseInt(parts[0].trim());
                String category = parts[1].trim();
                String name = parts[2].trim();
                boolean isFree = Boolean.parseBoolean(parts[3].trim());
                double price = Double.parseDouble(parts[4]);

                AddOn addOn = new AddOn(itemNumber, category, name, price, isFree);
                extras.add(addOn);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return extras;
    }
}
