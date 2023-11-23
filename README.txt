3005 Assignement 4
Carol Zhao
101179166

Set Up database:
	1. create a database in PostgreSQL, named Assignment 4
	2. create table and insert data
	3. download the .jar which is the newest version
	4. set up .jar and jdk in project structure, add .jar in the library.
	5. connect the database with password and username and host
Steps to compile and run your application:
	1. find the sample that provided by professor
	2. read through and understand the code
	3. wirte your own version base on the provided code
	4. There is some option when you run this JAVA file in ide (F5). You can choose any of them if you want. It will print out the whole table at the end
A brief explanation of each function in the application:
	getAllStudents(): Out print every students in order
	addStudent(first_name, last_name, email, enrollment_date): add a student with a format checking of email. Any type error will casue return
	updateStudentEmail(student_id, new_email): change one student's email. If fail in database will give error. Again,  changing with a format checking of email and any type error will casue return
	deleteStudent(student_id): delate the studnet with the id.

demo link:https://youtu.be/SPQBZ0tUBX4
sourse code and database are in different branches