insert into STUD(ID, LASTNAME, FIRST_NAME, BIRTHDATE, GENDER, WANTSNEWSLETTER, ST_STREET, ST_NUMBER, ST_CITY) values(10, 'Doe', 'John', TO_DATE('2000-02-18', 'YYYY-MM-DD'), 'MALE', 'Y', 'Baker Street', '221B', 'London');
insert into STUD(ID, LASTNAME, FIRST_NAME, BIRTHDATE, GENDER, WANTSNEWSLETTER, ST_STREET, ST_NUMBER, ST_CITY) values(11, 'Doe', 'Will', TO_DATE('2001-04-04', 'YYYY-MM-DD'), 'MALE', 'N', 'Washington Avenue', '23', 'Oxford');

insert into TEACHER(ID, LASTNAME, FIRSTNAME) values(20, 'Doe', 'Jane');

insert into COURSE(ID, TEACHER_ID, TITLE) values(30, 20, 'Java 101');
insert into COURSE(ID, TEACHER_ID, TITLE) values(31, 20, 'SQL 101');
insert into COURSEMATERIAL(ID, COURSE_ID, URL) values(40, 31, 'https://example.com/courses/sql101/material');
insert into COURSE(ID, TEACHER_ID, TITLE) values(32, 20, 'JPA 101');

insert into STUDENTS_COURSES(STUDENT_ID, COURSE_ID) values(10, 30);
insert into STUDENTS_COURSES(STUDENT_ID, COURSE_ID) values(10, 31);
insert into STUDENTS_COURSES(STUDENT_ID, COURSE_ID) values(11, 30);