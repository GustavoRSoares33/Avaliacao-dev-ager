CREATE TABLE funcionario (rowid bigint auto_increment PRIMARY KEY, nm_funcionario VARCHAR(255) NOT NULL);
INSERT INTO funcionario (nm_funcionario) VALUES ('João'), ('Maria'), ('José'), ('Joana');

CREATE TABLE agenda (rowid bigint auto_increment PRIMARY KEY, 
	nm_agenda VARCHAR(255) NOT NULL, 
	periodo_disponivel TINYINT NOT NULL
);
INSERT INTO agenda (nm_agenda, periodo_disponivel) VALUES 
('Sala de Reuniões Principal', 3),
('Auditório', 1),
('Laboratório de Tecnologia', 2),
('Sala de Entrevistas', 3);

CREATE TABLE compromissos (
	rowid bigint auto_increment PRIMARY KEY,
	nm_compromisso VARCHAR(255) NOT NULL,
	rowid_funcionario bigint,
	rowid_agenda bigint,
	data_compromisso DATE NOT NULL,
	hora_compromisso TIME NOT NULL,
	FOREIGN KEY (rowid_funcionario) REFERENCES funcionario(rowid),
	FOREIGN KEY (rowid_agenda) REFERENCES agenda(rowid)
);
INSERT INTO compromissos (nm_compromisso, rowid_funcionario, rowid_agenda, data_compromisso, hora_compromisso) 
VALUES 
('Reunião de Alinhamento', 1, 1, '2026-09-05', '10:00:00'),
('Treinamento de Equipe', 2, 2, '2026-09-10', '08:30:00'),
('Manutenção dos Equipamentos', NULL, 3, '2026-09-12', '14:00:00'),
('Entrevista com Desenvolvedor', 4, NULL, '2026-09-15', '15:30:00'),
('Revisão de Metas do Semestre', 3, NULL, '2026-09-17', '18:30:00');