INSERT INTO school.course (id, name, code) VALUES
(1, 'Engenharia de Software', 'ENG-SW'),
(2, 'Ciência da Computação', 'CCOMP'),
(3, 'Sistemas de Informação', 'SIS-INF');

INSERT INTO school.subject (id, code, id_course) VALUES
(1, 'POO-001', 1),
(2, 'BD-002', 2),
(3, 'REDES-003', 3);

INSERT INTO school.teatcher (id, name, registration) VALUES
(1, 'Carlos Alberto', 1001),
(2, 'Fernanda Souza', 1002),
(3, 'João Lima', 1003);

INSERT INTO school.student (id, name, registration, id_course) VALUES
(1, 'Lucas Nogueira', 20230001, 1),
(2, 'Mariana Rocha', 20230002, 2),
(3, 'Pedro Silva', 20230003, 3);

INSERT INTO school.student_subject (student_id, subject_id) VALUES
-- Lucas Nogueira
(1, 1),
(1, 2),
-- Mariana Rocha
(2, 2),
(2, 3),
-- Pedro Silva
(3, 1),
(3, 3);

INSERT INTO school.subject_teatcher (subject_id, teatcher_id) VALUES
-- Programação Orientada a Objetos
(1, 1),
(1, 2),
-- Banco de Dados
(2, 2),
(2, 3),
-- Redes de Computadores
(3, 1),
(3, 3);

