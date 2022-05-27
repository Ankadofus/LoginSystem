# LoginSystem
This is a login system that was implemented using Java (Spring framework). This project used MySql to store data in a database, as well as interact with pre-existing 
data. 
(Current implementation resets pre-existing data upon execution - make changes according to desired implementation in application.properties)

This system allows users to create a new account (unless they already possess an enabled account affiliated with their email or their email input is invalid).
It also sends a confirmation link to the email address they seek to make an account with, that expires after 15 min (it can be resent if it expired).

The purpose of this repo is to serve as an easy access to such a system for future use if a similar concept needs to be implemented in a future project (to facilitate 
multiple usages in different contexts).

Depending on its intended usage some changes need to be made (such as the different types of users in UserRole, the different fields in the User class,
or a different form of encryption in the com.app.security.config package, etc.)


