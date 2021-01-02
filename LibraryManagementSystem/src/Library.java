import java.util.ArrayList;
import java.util.Scanner;

public class Library
{
    public static void main(String[] args)
    {
    	Library l = new Library();
        while(true) l.run();
    }
    
	Scanner sc = new Scanner(System.in);

    private ArrayList<AuthorList> catalog = new ArrayList<>('Z' - 'A' + 1);
    private ArrayList<PatronList> people = new ArrayList<>('Z' - 'A' + 1);
    
    public Library()
    {
        for(int i = 0; i <= ('Z' - 'A'); i++)
        {
            catalog.add(new AuthorList());
            people.add(new PatronList());
        }
    }
    private int getIndex(String s)
    {
      return s.toUpperCase().charAt(0) - 'A';
    }
    public void addBook()
    {
        System.out.print("Enter book's title: ");
        String title = sc.nextLine();
        System.out.print("Enter book's author: ");
        String name = sc.nextLine();
        AuthorList authorList = catalog.get(name.toUpperCase().charAt(0) - 'A');
        Author author = null;
        for(int i = 0; i < authorList.size(); i++)
        {
        	author = authorList.get(i);
        	if(author.name.equals(name)) break;
        	author = null;
        }
        if (author == null)
        {
            BookList<Book> bList = new BookList<>();
            bList.add(new Book(title));
            author = new Author(name, bList);
            catalog.get(name.toUpperCase().charAt(0) - 'A').add(author);
        }
        else
        {
            author.books.add(new Book(title));
        }
    }
    public void issueBook()
    {
        System.out.print("Enter the book to be issued: ");
        String bookTitle = sc.nextLine();
        System.out.print("Enter the author's name: ");
        String authorName = sc.nextLine();
//        System.out.println(authorName);
        Author author = null;
        AuthorList aList = catalog.get(authorName.toUpperCase().charAt(0) - 'A');
        for(int i = 0; i < aList.size(); i++)
        {
        	author = aList.get(i);
        	if(author.name.equals(authorName)) break;
        	author = null;
        }
        if(author == null){
            System.out.println("Author's name not found in the database!");
            return;
        }
        
        BookList<Book> bookList = author.books;
        Book b = null;
        for(int i = 0; i < bookList.size(); i++)
        {
        	b = bookList.get(i);
        	b = b.title.equals(bookTitle)? b: null;
        }
        if (b == null)
        {
            System.out.println("No such book exists in the database!");
            return;
        }
        System.out.println("Enter patron's name: ");

        String patronName = sc.nextLine();
        Patron patron = null;
        PatronList pList = people.get(patronName.toUpperCase().charAt(0) - 'A');
        for(int i = 0; i < pList.size(); i++)
        {
        	patron = pList.get(i);
        	if(patron.name.equals(patronName)) break;
        	patron = null;
        }
        if(patron == null)
        {
        	BookList<CheckedOutBook> cbList = new BookList<>();
        	patron = new Patron(patronName, cbList);
        	pList.add(patron);
        }
        patron.borrowedBooks.add(new CheckedOutBook(b, author));
    }        
    public void returnBook()
    {
        System.out.println("Enter patron's name: ");
        String patronName = sc.next();
        PatronList pList = people.get(getIndex(patronName));
        Patron patron = pList.getPatronByName(patronName);
        if(patron == null) {
        	System.out.println("No such patron!");
        	return;
        }
        
        System.out.println("Enter book's name:");
        String bookName = sc.next();
        
        BookList<CheckedOutBook> bookList = patron.borrowedBooks;
        CheckedOutBook b = null;
        for(int i = 0; i < bookList.size(); i++)
        {
        	b = bookList.get(i);
        	if(b.book.title.equals(bookName))
        		break;
        	b = null;
        }
        if(bookList.remove(b))
        	System.out.println("Book has been returned.\n");
        else
        	System.out.println("Failed to return the book.\n");
        sc.nextLine();
  	}
    public void status()
    {
        System.out.println("Library has the following books:\n ");
        for(AuthorList aList: catalog)
        {
        	if(aList.size() > 0)
        	{
        		aList.show();
        	}
        }

        System.out.println("The following people are using this library.");
        for(PatronList pList: people)
        {
        	if(pList.size() > 0) {
        		pList.show();
        	}
        }
    }
    public void run()
    {
        System.out.println("*********Library Management System***************");
        
        //I am obligated to concat with + operator in Java
        System.out.println( "1. Add a book\n" + 
                            "2. Issue a book\n" + 
                            "3. Return a book\n" + 
                            "4. Show status\n" +
                            "5. Exit\n");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice)
        {
            case 1:
                addBook();
                break;
            case 2:
                issueBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                status();
                break;
            case 5:
                System.exit(0);
        }
    }
}
              
