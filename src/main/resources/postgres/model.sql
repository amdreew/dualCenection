create table if not exists clientes
(
    id     serial
    constraint clientes_pk
    primary key,
    codigo varchar(12) not null,
    nombre varchar     not null
    );

alter table clientes
    owner to admin_db;

INSERT INTO public.clientes (id, codigo, nombre) VALUES (1, '0000', 'client de test');
