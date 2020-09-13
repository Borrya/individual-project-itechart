create table if not exists book (id serial not null primary key, cover bytea, title varchar(150) not null,
                authors varchar(256) not null, genres varchar(256) not null, publisher varchar(256) not null, publish_date date not null, isbn bigint not null,
                pages integer not null, description text, total_amount integer, status varchar(50));