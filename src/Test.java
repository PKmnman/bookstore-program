import com.bookstore.common.Book;
import com.bookstore.common.ISBN;
import com.bookstore.inv.Inventory;

public class Test {
	
	public static void main(String[] args) {
		Book b = new Book("How to Code 101", "SmartyPants McGee", "nonfiction", 353, new ISBN("0-7645-2641-3"));
		b.setPublisher("Fake Publisher .Inc");
		
		Book c = new Book("Fake Book", "Blah", "sci-fi", 20, new ISBN("978-1-60309-322-4"));
		
		Inventory<Book> catalog = new Inventory<>();
		catalog.showAll();
		catalog.add(b,c);
		System.out.println("\n");
		catalog.showAll();
		catalog.add(new Book("Title", "writer", "fantasy", 250, new ISBN("978-1-60309-066-7")));
		catalog.showAll();
		
		Book match;
		match = catalog.fetch(new Book(new ISBN("978-1-60309-322-4")));
		System.out.println("Lookup: " + match);
	}
}
