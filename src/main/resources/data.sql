INSERT INTO perfil(id, nome) VALUES(1, 'ROLE_ADMINISTRADOR');

INSERT INTO usuario(id, usuario, email, senha) VALUES(1, 'atendimento', 'edson@trixti.com.br', '$2y$12$6ChsmUK2v4mr90HLzyGiHONls2yBoMeFWnvBlTKVLQuTr4ygluqO2');
INSERT INTO usuario(id, usuario, email, senha) VALUES(2, 'edson', 'edson2@trixti.com.br', '$2y$12$z1PNgH4mFdTO/Edvte2OxeE4JzobamR5VG.0Y7O/NTqYByJAZkPSu');

INSERT INTO usuario_perfils(usuario_id, perfils_id) values (1, 1);
INSERT INTO paciente(codigo, nome, telefone, data_nascimento, cpf, email, data_cadastro) values (1010, 'Edson Adriano Pereira Landim', '61982770004', CURRENT_TIMESTAMP, '73477486153', 'eap.landim@gmail.com', CURRENT_TIMESTAMP);
INSERT INTO paciente(codigo, nome, telefone, data_nascimento, cpf, email, data_cadastro) values (2020, 'Rachel Aurélio Bezerra', '61982770045', CURRENT_TIMESTAMP, '27030705009', 'rachel@gmail.com', CURRENT_TIMESTAMP);
INSERT INTO paciente(codigo, nome, telefone, data_nascimento, cpf, email, data_cadastro) values (3030, 'Luísa Aurélio Landim', '61982771104', CURRENT_TIMESTAMP, '33126963056', 'luisa@gmail.com', CURRENT_TIMESTAMP);
INSERT INTO paciente(codigo, nome, telefone, data_nascimento, cpf, email, data_cadastro) values (4040, 'Laura Pereira Bezerra', '61982770004', CURRENT_TIMESTAMP, '39872194084', 'laura.pereira@gmail.com', CURRENT_TIMESTAMP);
INSERT INTO paciente(codigo, nome, telefone, data_nascimento, cpf, email, data_cadastro) values (5050, 'Lucca Alone Forever', '61982775566', CURRENT_TIMESTAMP, '04415842097', 'eap.landim@gmail.com', CURRENT_TIMESTAMP);

INSERT INTO medico(crm, nome, cpf, email, data_cadastro) values (48915484, 'João Matos Souza', '04415842097', 'joao@gmail.com', CURRENT_TIMESTAMP);
INSERT INTO medico(crm, nome, cpf, email, data_cadastro) values (4534554,  'Luiz Junior santos', '33126963056', 'luiz@gmail.com', CURRENT_TIMESTAMP);
INSERT INTO medico(crm, nome, cpf, email, data_cadastro) values (48915484, 'Marcos Souza', '33126963056', 'marcos@gmail.com', CURRENT_TIMESTAMP);
INSERT INTO medico(crm, nome, cpf, email, data_cadastro) values (48915484, 'Joana Maria de Souza', '27030705009', 'joana@gmail.com', CURRENT_TIMESTAMP);

INSERT INTO procedimento(codigo, descricao, valor, data_cadastro) values (10101012, 'Consulta eletiva', 100, CURRENT_TIMESTAMP);
INSERT INTO procedimento(codigo, descricao, valor, data_cadastro) values (30548565, 'Glicose', 80, CURRENT_TIMESTAMP);
INSERT INTO procedimento(codigo, descricao, valor, data_cadastro) values (45582218, 'Exame do pezinho', 285, CURRENT_TIMESTAMP);
INSERT INTO procedimento(codigo, descricao, valor, data_cadastro) values (54848423, 'Ressonancia magnética', 845, CURRENT_TIMESTAMP);
INSERT INTO procedimento(codigo, descricao, valor, data_cadastro) values (88544521, 'Endoscopia', 785, CURRENT_TIMESTAMP);
INSERT INTO procedimento(codigo, descricao, valor, data_cadastro) values (48486142, 'Artroscopia', 179, CURRENT_TIMESTAMP);

INSERT INTO atendimento (id, data_cadastro, status, paciente_id, medico_id, tipo, tipo_atendimento, observacoes, valor) values(1,CURRENT_TIMESTAMP, 'NEGADO', 1,1, 'Particular', 'Exame', 'Favor autorizar com urgência', 300);
INSERT INTO  atendimento_procedimento ( atendimento_id, procedimento_id, quantidade) values (1, 1, 3);

INSERT INTO atendimento (id, data_cadastro, status, paciente_id, medico_id, tipo, tipo_atendimento, observacoes, valor) values(2,CURRENT_TIMESTAMP, 'AUTORIZADO', 2,3, 'Particular', 'Exame', 'Paciente no hospital', 80);
INSERT INTO  atendimento_procedimento ( atendimento_id, procedimento_id, quantidade) values (2, 2, 1);

INSERT INTO atendimento (id, data_cadastro, status, paciente_id, medico_id, tipo, tipo_atendimento, observacoes, valor) values(3,CURRENT_TIMESTAMP, 'AUTORIZADO', 3,2, 'Particular', 'Exame', 'Paciente pediu para agendar o atendimento', 845);
INSERT INTO  atendimento_procedimento ( atendimento_id, procedimento_id, quantidade) values (3, 4, 1);

INSERT INTO atendimento (id, data_cadastro, status, paciente_id, medico_id, tipo, tipo_atendimento, observacoes, valor) values(4,CURRENT_TIMESTAMP, 'ANALISE', 4,4, 'Particular', 'Exame', 'Paciente sem carteirinha', 785);
INSERT INTO  atendimento_procedimento ( atendimento_id, procedimento_id, quantidade) values (4, 5, 1);
 
 


