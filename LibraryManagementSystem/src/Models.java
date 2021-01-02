import java.util.LinkedList;

class AuthorList extends LinkedList<Author>{
    static final long serialVersionUID = 100;

    public void show(){
        for(int i = 0; i < size(); i++)
        {
            this.get(i).show();
        }
    }
}
class BookList <T> extends LinkedList<T>{
    static final long serialVersionUID = 120;
    
    public void show(){
    	for(int i = 0; i < size(); i++)
    	{
    		System.out.println(this.get(i));
    	}
    }
}

// @SuppressWarnings("unchecked")
class PatronList extends LinkedList<Patron>{
    static final long serialVersionUID = 130;
    public void show(){
        for(int i = 0; i < size(); i++)
        {
        	get(i).show();
        }
    }
    public Patron getPatronByName(String patronName) {
		Patron p = null;
    	for(int i = 0; i < size(); i++)
		{
    		p = get(i);
			if(p.name.equals(patronName)) break;
			p = null;				
		}
		return p;
    }
}

class Author
{
    String name;
    BookList<Book> books;

    Author(String name)
    {
        this(name, null);
    }
    Author(String name, BookList<Book> list)
    {
        this.name = name;
        this.books = list;
    }
    public void show()
    {
        System.out.println(name);
        this.books.show();
    }
}

class Book
{
    String title;
    Book next;
    AuthorList authors;
    Patron patron;
    Book(String title)
    {
        this(title, null);
    }
    Book(String title, Book next)
    {
        this.title = title;
        this.next = next;
    }
    public void show()
    {
        System.out.print("  * " + title);
        System.out.println( patron != null? patron.name : "");
    }
    public String toString()
    {
        return "  * " + title + (patron != null? " - checked out to " + patron.name: "");
    }
}

class CheckedOutBook
{
    Author author = null;
    Book book = null;
    CheckedOutBook(Book b)
    {
        this(b, null);
    }
    CheckedOutBook(Book b, Author a)
    {
        this.book = b;
        this.author = a;
    }
    public void show()
    {
        System.out.println("\t* " + this.book.title + ", " + this.author.name);
    }
    public String toString()
    {
    	return "  *" + this.book.title + ", " + this.author.name;
    }
}
class Patron
{
    String name;
    BookList<CheckedOutBook> borrowedBooks;
    public Patron(String name)
    {
        this(name, null);
    }
    public Patron(String name, BookList<CheckedOutBook> books)
    {
        this.name = name;
        this.borrowedBooks = books;
    }
    public void show()
    {
        System.out.println(this.name + " has issued following books.");
        this.borrowedBooks.show();
    }
}
