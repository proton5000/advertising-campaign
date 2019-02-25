create table campaings {
    id integer not null,
    name varchar(255) not null,
    status integer not null,
    start_date timestamp,
    end_date timestamp,
    ads integer
};

create table ads {
    id integer not null,
    name varchar(255) not null,
    status integer not null,
    platforms integer,
    asset_url varchar(255) not null
};