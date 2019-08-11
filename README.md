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
![Bookify Login](<a data-flickr-embed="true"  href="https://www.flickr.com/photos/183351930@N04/48514426106/in/dateposted-public/" title="Login"><img src="https://live.staticflickr.com/65535/48514426106_7605bb29a8_z.jpg" width="640" height="392" alt="Login"></a><script async src="//embedr.flickr.com/assets/client-code.js" charset="utf-8"></script>)
