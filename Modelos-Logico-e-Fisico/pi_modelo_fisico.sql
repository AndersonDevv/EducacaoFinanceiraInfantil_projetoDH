create database db_plataforma_financeira;
use db_plataforma_financeira;
-- -----------------------------------------------------
-- Table `cartao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cartao` (
  `car_numero` INT(11) NOT NULL,
  `car_data_vencimento` DATE NOT NULL,
  `car_limite` DOUBLE NOT NULL,
  `car_ativo` CHAR(1) NOT NULL,
  PRIMARY KEY (`car_numero`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `categoria` (
  `cat_codigo` INT(11) NOT NULL,
  `cat_nome` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`cat_codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `usuario` (
  `usu_cpf` VARCHAR(14) NOT NULL,
  `usu_nome` VARCHAR(50) NOT NULL,
  `usu_nome_end` VARCHAR(60) NULL DEFAULT NULL,
  `usu_numero` VARCHAR(6) NULL DEFAULT NULL,
  `usu_bairro` VARCHAR(40) NULL DEFAULT NULL,
  `usu_cidade` VARCHAR(50) NULL DEFAULT NULL,
  `usu_uf` CHAR(2) NULL DEFAULT NULL,
  `usu_tel_celular` VARCHAR(14) NULL DEFAULT NULL,
  `usu_tel_fixo` VARCHAR(14) NULL DEFAULT NULL,
  `usu_email` VARCHAR(30) NULL DEFAULT NULL,
  `usu_senha` VARCHAR(512) NULL DEFAULT NULL,
  `usu_status` CHAR(1) NULL DEFAULT NULL,
  `usu_complemento` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`usu_cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `dependente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dependente` (
  `dep_num_sep` INT(11) NOT NULL,
  `dep_nome` VARCHAR(50) NOT NULL,
  `dep_usuario` VARCHAR(30) NOT NULL,
  `dep_senha` VARCHAR(512) NOT NULL,
  `usuario_usu_cpf` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`dep_num_sep`, `usuario_usu_cpf`),  
  CONSTRAINT `fk_dependente_usuario`
    FOREIGN KEY (`usuario_usu_cpf`)
    REFERENCES `usuario` (`usu_cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `conta_corrente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conta_corrente` (
  `cc_num_conta` INT(11) NOT NULL,
  `cc_num_agencia` INT(11) NOT NULL,
  `cc_saldo` DOUBLE NOT NULL,
  `cc_ativa` CHAR(1) NULL DEFAULT NULL,
  `cc_tarifa` DOUBLE NULL DEFAULT NULL,
  `dependente_dep_num_sep` INT(11) NOT NULL,
  `dependente_usuario_usu_cpf` VARCHAR(14) NOT NULL,
  `cartao_car_numero` INT(11) NOT NULL,
 
  PRIMARY KEY (`cc_num_conta`),
 
  CONSTRAINT `fk_conta_corrente_dependente1`
    FOREIGN KEY (`dependente_dep_num_sep` , `dependente_usuario_usu_cpf`)
    REFERENCES `dependente` (`dep_num_sep` , `usuario_usu_cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_conta_corrente_cartao1`
    FOREIGN KEY (`cartao_car_numero`)
    REFERENCES `cartao` (`car_numero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `conta_poupanca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `conta_poupanca` (
  `cp_num_conta` INT(11) NOT NULL,
  `cp_num_agencia` INT(11) NOT NULL,
  `cp_saldo` DOUBLE NOT NULL,
  `cp_ativa` CHAR(1) NOT NULL,
  `cp_rendimento` DOUBLE NOT NULL,
  `cp_data_aniversario` DATE NOT NULL,
  `dependente_dep_num_sep` INT(11) NOT NULL,
  `dependente_usuario_usu_cpf` VARCHAR(14) NOT NULL,
 
  PRIMARY KEY (`cp_num_conta`),
  CONSTRAINT `fk_conta_poupanca_dependente1`
    FOREIGN KEY (`dependente_dep_num_sep` , `dependente_usuario_usu_cpf`)
    REFERENCES `dependente` (`dep_num_sep` , `usuario_usu_cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `dias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dias` (
  `dia_nome` VARCHAR(13) NULL DEFAULT NULL,
  `dia_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`dia_codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `renda_fixa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `renda_fixa` (
  `rf_codigo` INT(11) NOT NULL,
  `rf_data_imvestimento` DATE NOT NULL,
  `rf_valor_investido` DOUBLE NOT NULL,
  `rf_iof` FLOAT NOT NULL,
  `rf_rendimento` DOUBLE NOT NULL,
  `rf_data_resgate` DATE NOT NULL,
  `dependente_dep_num_sep` INT(11) NOT NULL,
  `dependente_usuario_usu_cpf` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`rf_codigo`),
  
  CONSTRAINT `fk_renda_fixa_dependente1`
    FOREIGN KEY (`dependente_dep_num_sep` , `dependente_usuario_usu_cpf`)
    REFERENCES `dependente` (`dep_num_sep` , `usuario_usu_cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `renda_variavel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `renda_variavel` (
  `rv_codigo` INT(11) NOT NULL,
  `rv_data_imvestimento` DATE NOT NULL,
  `rv_valor_investido` DOUBLE NOT NULL,
  `rv_porcentagem` FLOAT NOT NULL,
  `dependente_dep_num_sep` INT(11) NOT NULL,
  `dependente_usuario_usu_cpf` VARCHAR(14) NOT NULL,
 
  PRIMARY KEY (`rv_codigo`),
  CONSTRAINT `fk_renda_variavel_dependente1`
    FOREIGN KEY (`dependente_dep_num_sep` , `dependente_usuario_usu_cpf`)
    REFERENCES `dependente` (`dep_num_sep` , `usuario_usu_cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tarefa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tarefa` (
  `tar_titulo` VARCHAR(30) NOT NULL,
  `tar_data_inicio` DATE NOT NULL,
  `tar_status` CHAR(1) NULL DEFAULT NULL,
  `tar_objetivo` VARCHAR(50) NOT NULL,
  `tar_pontos` SMALLINT(6) NOT NULL,
  `tar_codigo` INT(11) NOT NULL,
  `tar_data_fim` DATE NOT NULL,
  `categoria_cat_codigo` INT(11) NOT NULL,
  `dependente_dep_num_sep` INT(11) NOT NULL,
  `dependente_usuario_usu_cpf` VARCHAR(14) NOT NULL,
  PRIMARY KEY (`tar_titulo`),
 
  CONSTRAINT `fk_tarefa_categoria1`
    FOREIGN KEY (`categoria_cat_codigo`)
    REFERENCES `categoria` (`cat_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarefa_dependente1`
    FOREIGN KEY (`dependente_dep_num_sep` , `dependente_usuario_usu_cpf`)
    REFERENCES `dependente` (`dep_num_sep` , `usuario_usu_cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `uf`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `uf` (
  `uf_codigo` INT(11) NOT NULL,
  `uf_unidade` VARCHAR(20) NULL DEFAULT NULL,
  `uf_sigla` CHAR(2) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `tarefa_has_dias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tarefa_has_dias` (
  `tarefa_tar_titulo` VARCHAR(30) NOT NULL,
  `dias_dia_codigo` INT(11) NOT NULL,
  PRIMARY KEY (`tarefa_tar_titulo`, `dias_dia_codigo`),
 
  CONSTRAINT `fk_tarefa_has_dias_tarefa1`
    FOREIGN KEY (`tarefa_tar_titulo`)
    REFERENCES `tarefa` (`tar_titulo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tarefa_has_dias_dias1`
    FOREIGN KEY (`dias_dia_codigo`)
    REFERENCES `dias` (`dia_codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


