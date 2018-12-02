This project allows a user to input a csv file and populates a sqlite database with valid csv entries.

To build and execute this project:
1. clone this repository.
2. run mvn clean package.
3. go to target directory.
4. copy the snapshot jar with dependencies to whatever directory you want.
5. run the jar file.

User Notes:
- When asked to input path of csv file you must specify the absolute path.
- When asked to input header rows skiped. Enter 0 to read the entire file otherwise enter x > 0 to skip x header rows.
- The jar will output 4 files 1 for valid csv enties, 1 for invalid csv entries, 1 that contains the database (database.db) file, and 1 directory that contains the logfile with statistics. All of these files will be output in the same directory as the jar.
- Currently the database does not account for multiple iterations of the program so user must delete the database.db file after each use to get an accurate table.
- This software was developed in a mac environment and has not been tested in a windows or linux environment. Unexpected results may occur if run in those enviornments.  
