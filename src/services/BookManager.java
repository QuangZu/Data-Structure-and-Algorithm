package services;

import model.Book;
import util.CSVReader;
import util.InputHelper;

public class BookManager {
    private Book[] books;

    public BookManager(String filepath) {
        this.books = CSVReader.readBooks(filepath);
    }

    public void displayAllBooks() {
        System.out.println("\n===== ALL BOOKS =====");
        if (books.length == 0) {
            System.out.println("No books available.");
            return;
        }

        for (int i = 0; i < books.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), books[i]);
        }
        System.out.println("=====================");
    }

    public void searchBooks() {
        System.out.println("\n--- Search Books ---");
        System.out.println("1. Search by title");
        System.out.println("2. Search by author");
        int choice = InputHelper.readInt("Select search type: ");
        
        String keyword = InputHelper.readString("Enter search keyword: ");
        boolean found = false;
        
        System.out.println("\n--- Search Results ---");
        for (Book book : books) {
            boolean matches = false;
            if (choice == 1) {
                matches = book.getTitle().toLowerCase().contains(keyword.toLowerCase());
            } else if (choice == 2) {
                matches = book.getAuthor().toLowerCase().contains(keyword.toLowerCase());
            }
            
            if (matches) {
                System.out.println(book);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("No books found matching '" + keyword + "'.");
        }
    }

    public Book findBookByTitleAndAuthor(String title, String author) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title.trim()) && 
                book.getAuthor().equalsIgnoreCase(author.trim())) {
                return book;
            }
        }
        return null;
    }

    public Book[] getBooks() {
        return books;
    }
}
