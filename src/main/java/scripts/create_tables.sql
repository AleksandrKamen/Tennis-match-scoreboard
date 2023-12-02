create table players(
id INT primary key AUTO_INCREMENT,
name varchar(126) unique
);
create index players_name_idx on players(name);

create table matches(
id INT primary key AUTO_INCREMENT,
player1 INT references players(id),
player2 INT references players(id),
Winner INT references players(id)
);