# CS365Assignment2

There is a problem with the program that does not allow two User Windows to be open at the same time. If two windows are open at the same time the component data of all of the open User Windows will be converted to the data of the User Window that was opened last. I realize that this is a problem with the Swing components that I used and the inplementation of these components. I have never taken a class that used Swing and am not sure how to fix this problem. 

To avoid the bug with multiple User Windows only have one User Window open at a time. The program functions properly when only one User Window is open at a time. The program allows for one User Window to be open and then closed to allow for another User Window to then be opened. 

It should also be noted that multiple User Windows can be oppened at the same time, but the bug only appears when the "Follow User" or the "Post Tweet" buttons is clicked.

This may be a problem with showing the use of the Observer pattern that we were supposed to implement. Since I could not show each User Window updating in real time when a new message is posted I inserted print statements in the Update method that is called by notifyObservers(). The print statements show the message that was posted and the new news feeds all of the users that were following the user that posted the message.
