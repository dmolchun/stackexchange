# Spring Boot + ReactJS web application
Java web application with a form that allows a user to enter a search string, queries Stack Exchange 
to find questions with titles containing that string, and display the results in a tabular format 
showing the date of the question, its title and who posted it.
If the question has been answered, row background become green.
By clicking to row, you navigate to the original question on Stack Exchange.

# Application config
1. stackexchange.app.key - application key to access Stack Exchange API
2. stackexchange.site - site for search in (https://stackexchange.com/sites)