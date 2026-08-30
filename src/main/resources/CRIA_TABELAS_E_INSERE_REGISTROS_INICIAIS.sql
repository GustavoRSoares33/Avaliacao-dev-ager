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
	rowid_funcionario bigint NOT NULL,
	rowid_agenda bigint NOT NULL,
	data_compromisso DATE NOT NULL,
	hora_compromisso TIME NOT NULL,
	FOREIGN KEY (rowid_funcionario) REFERENCES funcionario(rowid),
	FOREIGN KEY (rowid_agenda) REFERENCES agenda(rowid)
);