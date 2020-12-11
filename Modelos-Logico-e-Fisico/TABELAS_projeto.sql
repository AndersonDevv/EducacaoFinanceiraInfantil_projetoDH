USE plataforma_projeto;

CREATE TABLE IF NOT EXISTS dias_semana(
	dia_nome VARCHAR(13),
	 dia_codigo SMALLINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS UF (
uf_unidade  SMALLINT NOT NULL AUTO_INCREMENT,      /*  o que é isso??*/
uf_codigo SMALLINT ,    /*  NÃO FUNCIonou COM O NOT NULL*/
uf_sigla VARCHAR(7),  /*  NÃO FUNCIonou COM O NOT NULL*/
PRIMARY KEY (uf_unidade)
);

INSERT INTO UF (uf_sigla) VALUES 
("AC"),("AL"),("AP"),("AM"),("BA"),("CE"),("DF"),("ES"),("GO"),("MA"),("MT"),("MS"),("MG"),
("PA"),("PB"),("PR"),("PE"),("PI"),("RN"),("RS"),("RJ"),("RO"),("RR"),("SC"),("SP"),("SE"),("TO");


CREATE TABLE IF NOT EXISTS categoria (
cat_codigo SMALLINT NOT NULL PRIMARY KEY,
cat_nome VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS tarefa (
tar_titulo VARCHAR(200) NOT NULL,
tar_data_inicio DATE NOT NULL,
tar_status BOOLEAN,
tar_objetivo VARCHAR(200) NOT NULL,
tar_pontos SMALLINT  NOT NULL, 
tar_codigo SMALLINT AUTO_INCREMENT PRIMARY KEY,
tar_data_fim DATE NOT NULL,
FOREIGN KEY (tar_codigo) REFERENCES categoria (cat_codigo)
);

