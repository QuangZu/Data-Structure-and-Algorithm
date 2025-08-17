package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Book;

public class CSVReader {
    public static Book[] readBooks(String filepath) {
        List<Book> bookList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = br.readLine();
            
            while ((line = br.readLine()) != null) {
                String[] parts = parseCSVLine(line);
                if (parts.length >= 4) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        String author = parts[2].trim();
                        double price = Double.parseDouble(parts[3].trim());
                        
                        Book book = new Book(id, title, author, price);
                        bookList.add(book);
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        
        return bookList.toArray(new Book[0]);
    }
    
    private static String[] parseCSVLine(String line) {
        
        int firstComma = line.indexOf(',');
        int lastComma = line.lastIndexOf(',');
        
        int secondLastComma = line.lastIndexOf(',', lastComma - 1);
        
        String id = line.substring(0, firstComma);
        String title = line.substring(firstComma + 1, secondLastComma);
        String author = line.substring(secondLastComma + 1, lastComma);
        String price = line.substring(lastComma + 1);
        
        return new String[]{id, title, author, price};
    }
}