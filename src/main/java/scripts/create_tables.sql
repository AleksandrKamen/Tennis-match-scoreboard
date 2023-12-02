create table players(
id INT primary key AUTO_INCREMENT,
name varchar(126) not null unique
);
create index players_name_idx on players(name);

create table matches(
id INT primary key AUTO_INCREMENT,
player1 INT not null references players(id),
player2 INT not null references players(id),
winner  INT  not null references players(id)
);