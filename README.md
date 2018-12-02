This project allows a user to input a csv file and populates a sqlite database with valid csv entries.

To build and execute this project:
1. clone this repository.
2. run mvn clean package.
3. go to target directory.
4. copy the snapshot jar with dependencies to whatever directory you want.
5. run the jar file.

How it works:

This software iterates through a csv file, parsing it line by line. At each iteration, a new string array is created and investigated to determine whether or not the csv line is valid. Once validity is determined the string will be written in one of two files (badData file or valid file) and log statistics will be incremented. From there the program will create a database with a table named csvMapping. The program will then iterate through the valid csv file, parsing line by line, adding a parsed string array by index into the appropriate columns of the table.     

User Notes:
- When asked to input path of csv file you must specify the absolute path.
- When asked to input header rows skiped. Enter 0 to read the entire file otherwise enter x > 0 to skip x header rows.
- The jar will output 4 files 1 for valid csv enties, 1 that contains the database (database.db) file, 1 directory for invalid csv entry files, and 1 directory that contains the logfile with statistics. All of these files will be output in the same directory as the jar.
- Currently the database logic will create a database and store values in a table named csvMapping. On multipule iterations of the program, the database will drop this table and create another table with the same name. This means that data from the previous iteration will be overwritten with the data from the new file.
- This program assumes that the csv must have 10 columns anything more or less will be marked as invalid.
- The ten columns must also have values or they will be marked invalid. 
- This software was developed in a mac environment and has not been tested in a windows or linux environment. Unexpected results may occur if run in those enviornments.  
