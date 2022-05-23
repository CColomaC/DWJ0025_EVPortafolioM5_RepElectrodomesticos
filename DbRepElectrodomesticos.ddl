CREATE TABLE electrodomestico (
    id_electrodomestico   SERIAL NOT NULL,
    tipo                  VARCHAR(50),
    problema              VARCHAR(50),
    id_cliente            INTEGER NOT NULL
);

ALTER TABLE electrodomestico ADD CONSTRAINT electrodomestico_pk PRIMARY KEY ( id_electrodomestico );

CREATE TABLE ordendeservicio (
    id_ods                    SERIAL NOT NULL,
    estado                    VARCHAR(50),
    fechasolicitud            DATE,
    fechaactualizacionorden   DATE,
    id_electrodomestico       INTEGER NOT NULL
);

ALTER TABLE ordendeservicio ADD CONSTRAINT ordendeservicio_pk PRIMARY KEY ( id_ods );

ALTER TABLE ordendeservicio
    ADD CONSTRAINT electrodomestico_fk FOREIGN KEY ( id_electrodomestico )
        REFERENCES electrodomestico ( id_electrodomestico );

ALTER TABLE electrodomestico
    ADD CONSTRAINT electrodomestico_cliente_fk FOREIGN KEY ( id_cliente )
        REFERENCES cliente ( id_cliente );