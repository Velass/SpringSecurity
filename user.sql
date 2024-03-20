CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL DEFAULT 'Utilisateur'
);

INSERT INTO user (first_name, last_name, email, password, role)
VALUES('testprenom','testnom','testnom@test.fr','test123','Utilisateur' )