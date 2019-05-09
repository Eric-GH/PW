DROP TABLE IF EXISTS pasword_grage;
DROP TABLE IF EXISTS main_user;

CREATE TABLE main_user(
        id  SERIAL,
        user_name VARCHAR(100) NOT NULL,
        pass_word VARCHAR(100) NOT NULL,
        PRIMARY KEY (id)
);

CREATE TABLE pasword_grage(
        id SERIAL,
        user_manage_id INT NOT NULL REFERENCES main_user,
        password_name VARCHAR(100) NOT NULL,
        password_pass VARCHAR(100) NOT NULL,
        PRIMARY KEY (id)
);