create table usuario (
  id int Primary Key Auto_Increment,
  username varchar(100) Not Null unique,
  email varchar(100) NOT NULL Unique,
  senha varchar(255) NOT NULL,
  displayname varchar(255) NOT NULL,
  criado DATETIME DEFAULT CURRENT_TIMESTAMP,
  atualizado DATETIME ON UPDATE CURRENT_TIMESTAMP,
  ativado tinyint(1) DEFAULT 0
);

create table favorito (
id int Primary Key Auto_Increment,
id_usuario int not null,
id_historia int not null,
Foreign Key (id_usuario) references usuario(id),
Foreign Key (id_historia) references historia(id)
);

create table comentario(
  id int Primary Key Auto_Increment,
  texto text,
  criado DATETIME DEFAULT CURRENT_TIMESTAMP,
  id_usuario int not null,
  id_capitulo int not null,
  Foreign Key(id_usuario) references usuario(id),
  Foreign Key(id_capitulo) references capitulo(id)
);

create table historia(
  id int Primary Key Auto_Increment,
  autor int not null,
  terminada tinyint(1) DEFAULT 0  not null,
  data datetime DEFAULT CURRENT_TIMESTAMP,
  titulo varchar(100) not null,
  sinopse varchar(255),
  Foreign Key(autor) references usuario(id)
);

create table capitulo(
  id int Primary Key Auto_Increment,
  historia int not null,
  observacoes text,
  ordem int,
  texto text not null,
  titulo varchar(100) not null,
  Foreign Key(historia) references historia(id)
);