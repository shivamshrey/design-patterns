/**
 * Prototype is a creational design pattern that lets you copy existing objects
 * without making your code dependent on their classes.
 *
 * https://youtu.be/MYe5NSmFU_c
 */

package Creational;

import java.util.ArrayList;
import java.util.List;

class Book implements Cloneable {
    private List<String> bookList;

    public Book() {
        this.bookList = new ArrayList<String>();
    }

    public Book(List<String> list) {
        this.bookList = list;
    }

    // Assume this a computationally expensive operation
    // like loading data from database
    public void loadData() {
        bookList.add("1984");
        bookList.add("Black Swan");
        bookList.add("The Psychology of Money");
        bookList.add("Thinking Fast and Slow");
        bookList.add("Dune");
    }

    public List<String> getBookList() {
        return this.bookList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<String> cloneBookList = new ArrayList<String>();

        for(String s : this.getBookList()) {
            cloneBookList.add(s);
        }

        return new Book(cloneBookList);
    }
}

public class PrototypeDemo {

    public static void main(String[] args) throws CloneNotSupportedException {
        Book book = new Book();
        book.loadData();

        Book clonedBook = (Book) book.clone();
        List<String> clonedBookBookList = clonedBook.getBookList();
        clonedBookBookList.add("The Shining");

        System.out.println("Original " + book.getBookList());
        System.out.println("Clone " + clonedBookBookList);

        System.out.println("Removing only from clone doesn't affect the original list");

        clonedBook.getBookList().remove("1984");
        System.out.println("Original " + book.getBookList());
        System.out.println("Clone " + clonedBookBookList);
    }

}
