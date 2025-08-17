package model;

public class Book implements Comparable<Book> {
    private static int nextId = 1;
    private int id;
    private String title;
    private String author;
    private double price;

    public Book(String title, String author, double price) {
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        if (id >= nextId) {
            nextId = id + 1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s - %s ($%.2f)", title, author, price);
    }

    @Override
    public int compareTo(Book other) { 
        int titleComparison = this.title.compareToIgnoreCase(other.title);
        if (titleComparison != 0) {
            return titleComparison;
        }
        return this.author.compareToIgnoreCase(other.author);
    }
}

