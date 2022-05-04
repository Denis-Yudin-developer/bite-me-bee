CREATE TABLE IF NOT EXISTS generator_families (
	id int8 NOT NULL,
	is_deleted bool NOT NULL,
	activity float8 NOT NULL,
	delta float8 NOT NULL,
	disease_resistance float8 NOT NULL,
	drone_population int8 NOT NULL,
	egg_productivity float8 NOT NULL,
	honey_productivity float8 NOT NULL,
	is_infected bool NOT NULL,
	mood float8 NOT NULL,
	population int8 NOT NULL,
	queen_population int8 NOT NULL,
	worker_population int8 NOT NULL,
	CONSTRAINT pk_generator_families_id PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS generator_families
    OWNER TO bitemebee;