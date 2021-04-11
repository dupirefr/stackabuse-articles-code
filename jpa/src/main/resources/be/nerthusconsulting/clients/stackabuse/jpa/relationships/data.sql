insert into STUD(ID, LASTNAME, FIRST_NAME, BIRTHDATE, GENDER, WANTSNEWSLETTER, ST_STREET, ST_NUMBER, ST_CITY) values(10, 'Doe', 'John', TO_DATE('2000-02-18', 'YYYY-MM-DD'), 'MALE', 'Y', 'Baker Street', '221B', 'London');
insert into STUD(ID, LASTNAME, FIRST_NAME, BIRTHDATE, GENDER, WANTSNEWSLETTER, ST_STREET, ST_NUMBER, ST_CITY) values(11, 'Doe', 'Will', TO_DATE('2001-04-04', 'YYYY-MM-DD'), 'MALE', 'N', 'Washington Avenue', '23', 'Oxford');

insert into TEACHER(ID, LASTNAME, FIRSTNAME) values(10, 'Doe', 'Jane');

insert into COURSE(ID, TEACHER_ID, TITLE) values(10, 10, 'Java 101');
insert into COURSE(ID, TEACHER_ID, TITLE) values(11, 10, 'SQL 101');
insert into COURSEMATERIAL(ID, COURSE_ID, URL) values(10, 11, 'https://example.com/courses/sql101/material');
insert into COURSE(ID, TEACHER_ID, TITLE) values(12, 10, 'JPA 101');

insert into STUDENTS_COURSES(STUDENT_ID, COURSE_ID) values(10, 10);
insert into STUDENTS_COURSES(STUDENT_ID, COURSE_ID) values(10, 11);
insert into STUDENTS_COURSES(STUDENT_ID, COURSE_ID) values(11, 10);