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
    photoProfilURL VARCHAR(250),
    FOREIGN KEY (id) REFERENCES privateUser(idUser)
);

CREATE TABLE likes(
    fromIdUser INT NOT NULL,
    toIdUser INT NOT NULL,
    liked BOOLEAN NOT NULL,
    CONSTRAINT PK_Match PRIMARY KEY (fromIdUser, toIdUser),
    FOREIGN KEY (fromIdUser) REFERENCES publicUser(id),
    FOREIGN KEY (toIdUser) REFERENCES publicUser(id)
);

CREATE TABLE photo(
    idPhoto INT PRIMARY KEY AUTO_INCREMENT,
    photoURL VARCHAR(250) NOT NULL,
    publicUserId INT NOT NULL,
    FOREIGN KEY (publicUserId) REFERENCES publicUser(id)
);