-- Remove a tabela se já existir
DROP TABLE IF EXISTS carro;

-- Cria a tabela com integridade
CREATE TABLE carro (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       modelo VARCHAR(100) NOT NULL,
                       ano INT NOT NULL,
                       preco DOUBLE NOT NULL,
                       CONSTRAINT chk_ano CHECK (ano >= 1886),
                       CONSTRAINT chk_preco CHECK (preco > 0)
);

-- Zera os registros e insere dados iniciais
DELETE FROM carro;

INSERT INTO carro (modelo, ano, preco) VALUES ('Uno', 2010, 15000);
INSERT INTO carro (modelo, ano, preco) VALUES ('Celta', 2008, 18000);
INSERT INTO carro (modelo, ano, preco) VALUES ('Fiesta', 2014, 22000);
