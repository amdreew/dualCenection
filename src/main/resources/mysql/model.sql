create table if not exists facturas
(
    id      int auto_increment
    primary key,
    estado  varchar(10) not null,
    cliente varchar(15) not null,
    valor   decimal     not null
    );

INSERT INTO citizix_db.facturas (id, estado, cliente, valor) VALUES (1, 'facturado', '0000', 2000);

