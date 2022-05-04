CREATE TABLE IF NOT EXISTS user_roles (
	user_id int8 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role_id)
);

ALTER TABLE IF EXISTS user_roles
    OWNER to bitemebee;

ALTER TABLE IF EXISTS user_roles
    ADD CONSTRAINT fk_user_roles_on_role FOREIGN KEY (role_id) REFERENCES roles(id);

ALTER TABLE IF EXISTS user_roles
    ADD CONSTRAINT fk_user_roles_on_user FOREIGN KEY (user_id) REFERENCES users(id);

INSERT INTO user_roles (user_id, role_id) VALUES
    (1, 2);