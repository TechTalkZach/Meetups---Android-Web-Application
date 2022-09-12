USE sql5517727;

CREATE TABLE privateUser(
    idUser INT PRIMARY KEY AUTO_INCREMENT,
    courriel VARCHAR(40) UNIQUE,
    motDePasse VARCHAR(40) NOT NULL
);

CREATE TABLE publicUser(
    id INT PRIMARY KEY,
    nom VARCHAR(40) NOT NULL,
    sexe CHAR(1) NOT NULL,
    age INT NOT NULL,
    grandeur DOUBLE NOT NULL,
    education VARCHAR(40) NOT NULL,
    situationFamiliale VARCHAR(40) NOT NULL,
    religion VARCHAR(40) NOT NULL,
    recherche VARCHAR(40) NOT NULL,
    prenom VARCHAR(40) NOT NULL,
    FOREIGN KEY (id) REFERENCES privateUser(idUser)
);