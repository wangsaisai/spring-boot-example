CREATE TABLE IF NOT EXISTS demo (
	id serial PRIMARY KEY,
	name character varying(255) DEFAULT NULL,
	type character varying(255) DEFAULT NULL,
	enable boolean NOT NULL DEFAULT TRUE,
	content text DEFAULT NULL,
	id_list text DEFAULT ""
);


CREATE TABLE IF NOT EXISTS one (
	id serial PRIMARY KEY,
	name character varying(255) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS one (
	id serial PRIMARY KEY,
	name character varying(255) DEFAULT NULL,
	one_id bigint NOT NULL
);