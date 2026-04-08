CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE usuarios (
                        id          UUID          PRIMARY KEY DEFAULT gen_random_uuid(),
                        nome        VARCHAR(200)  NOT NULL,
                        login       VARCHAR(200)  NOT NULL UNIQUE,
                        email       VARCHAR(200)  NOT NULL UNIQUE,
                        senha       VARCHAR(255)  NOT NULL,
                        cargo       VARCHAR(50)   NOT NULL
                            CHECK (cargo IN ('ADMIN', 'PERITO', 'AUDITOR')),
                        status      BOOLEAN       NOT NULL DEFAULT true
);

CREATE TABLE casos (
                       id                               UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
                       caso_code                        VARCHAR(120),
                       entrada_datetime                 TIMESTAMPTZ     NOT NULL,
                       numero_registro_iml              INT             NOT NULL,
                       numero_identificacao_cadaver     INT             NOT NULL UNIQUE,
                       procedencia                      VARCHAR(200)    NOT NULL,
                       data_hora_aproximada_morte       TIMESTAMPTZ,
                       status                           VARCHAR(30)     NOT NULL,
                       sexo                             VARCHAR(10)     DEFAULT 'UNKNOWN',
                       idade_min_estimada               INT,
                       idade_max_estimada               INT,
                       etnia                            VARCHAR(50),
                       cor_olhos                        VARCHAR(50),
                       cor_cabelo                       VARCHAR(50),
                       tatuagem                         BOOLEAN         DEFAULT false,
                       descricao                        TEXT,
                       is_deleted                       BOOLEAN         DEFAULT false,
                       created_by                       UUID            REFERENCES usuarios(id),
                       created_at                       TIMESTAMPTZ     DEFAULT now(),
                       data_atualizacao                 TIMESTAMPTZ
);

CREATE TABLE caso_imagens (
                              id                        UUID            PRIMARY KEY,
                              nome_arquivo              VARCHAR(255)    NOT NULL,
                              tipo_arquivo              VARCHAR(50)     NOT NULL,
                              sha256                    VARCHAR(64)     NOT NULL,
                              caminho_storage           TEXT            NOT NULL,
                              data_upload               TIMESTAMP       NOT NULL,
                              caso_id                   UUID            NOT NULL,
                              CONSTRAINT fk_caso
                                  FOREIGN KEY (caso_id)
                                      REFERENCES casos(id)
);
