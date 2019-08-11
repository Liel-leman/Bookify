package Model;

import java.util.List;

public class Subscriber extends DBConnector {
	public List<BooksCopy> borowedBooks =null;
	public Person subscriber =null;

	
	public Subscriber(int subscID)
	{
        borowedBooks =  new BooksCopyService().getAllBooksCopysBypersonID(subscID);
        subscriber = new PersonService().getPersonByID(subscID); 
	}
	
	public int getNumOfBorrowedBooks()//geting all the books that the person is subscribed
	{
		int count=0;
		for( BooksCopy bookcopy: borowedBooks)
		{
			count++;
		}
		return count;
	}
	
}
