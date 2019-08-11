# Bookify - Library Management System in Java Swing & SQLite
## Installation
- You can install Bookify By Downloading the project files and run from Eclipse IDE 
From the "Main Class"
- You Can Download Only the Jar File and run from the CMD by the Command :
	`java -jar Minecraft.jar`
## About The Project
I made this App with Eclipse combined with UI of Swing. Extra libraries: 
- *sqlite-jdbc*
- *rs2xml*
- *Junit4.6*
- *Jcalendar*
- *Jgoodies-Looks*
### Features
- There are two roles:
*Librarian interface-manage all the books , book copys , borrow books , ad new Subscriber.
*Subscriber interface-manage his Info , borrowing preferable book copy , could look his borrow card and buy books permanently throught online shiping .
- The ability to save and load the last changes.
- Very beautiful and easy to use **modern user interface**


### Code description
- **MVC** pattern is the basis of this app.
- Used singelton pattern for the sqliteConnection.
- Used Adapter Pattern for the different Listeners.
- Used Observer Pattern for the listeners of keyboard, mouse klicks events
- There is an opportunity to expand the project easily.

## App guide
### Login
In the login panel you can login eather as Librarian : eli:123456 | eather as subscriber ilan:123456
![Bookify Login](https://live.staticflickr.com/65535/48514426106_7605bb29a8_z.jpg)In `New Game Menu` you can choose how many players will play, select name and badge color.
You need also mark the connection youwant to establish (subscriber or librarian user interface)



### Subscriber Interface
you can choose between 
-*Borrowing* - new borrow 
-*My Borrowings* - all borrows that i got
-*Shop* - Internet shop that calculates how match money you spent
-*My info* - showing info about your self

in the borrowing section you need to choose by the arrows what books you want to borrow
![Bookify Borrowing](https://live.staticflickr.com/65535/48514425771_60b8167162_z.jpg)
after you choose you need to choose the date of the boorrowing
![Bookify Borrowing](https://live.staticflickr.com/65535/48514425871_05bddb20b1_z.jpg)
Then you just click borrow to finish

now to check what books in your borrow list you need to click "MyBorrowings" 
![Bookify MyBorrowings](https://live.staticflickr.com/65535/48514607742_4c742ff2e2_z.jpg)

if You Want to Buy books permanently you go to "Shop"
![Bookify Shop](https://live.staticflickr.com/65535/48514425486_bcd4fb3c0d_z.jpg)

and now if you want to check your info and books borrowed go to "Myinfo"
![Bookify Myinfo](https://c1.staticflickr.com/5/4223/35082742452_e374a97941_o.png)

---

### Librarian Interface(admin)
now you enter the management systemm of the library:

You can manage type of books by Editing/Deleting/Inserting Books
![Bookify Borrowing](https://live.staticflickr.com/65535/48514607522_83c9a67a1e_z.jpg)
