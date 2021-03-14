CREATE TABLE IF NOT EXISTS COURSE (
	CID int NOT NULL PRIMARY KEY,
    NAME varchar(100),
    TIME_PLAN varchar(100),
    GRADE int,
    SUBJECT int,
    AF_AMOUNT int,
    STUDENT_TOTAL int,
    REAL_TOTAL_APPLY int,
    TEACHER_NAME varchar(100),
    TUTOR_NAME varchar(100)
);