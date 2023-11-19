-- liquibase formatted sql

-- changeset aleksey:1

CREATE INDEX student_name on student (name);
CREATE INDEX faculty_name_color on faculty (name,color);