CREATE TABLE Genres
(
    id         BIGSERIAL PRIMARY KEY,
    genre_name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE Directors
(
    director_id BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL
);

CREATE TABLE Actors
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


CREATE TABLE Movies
(
    id           BIGSERIAL PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    release_date DATE,
    genre_id     INT,
    director_id  INT,
    FOREIGN KEY (genre_id) REFERENCES Genres (id),
    FOREIGN KEY (director_id) REFERENCES Directors (director_id)
);

CREATE TABLE Users
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(50) UNIQUE  NOT NULL,
    email         VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255)        NOT NULL
);

CREATE TABLE Movie_Actors
(
    movie_id INT,
    actor_id INT,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES Movies (id),
    FOREIGN KEY (actor_id) REFERENCES Actors (id)
);


CREATE TABLE Reviews
(
    id          BIGSERIAL PRIMARY KEY,
    movie_id    INT  NOT NULL,
    user_id     INT  NOT NULL,
    rating      DECIMAL(3, 2) CHECK (rating >= 0 AND rating <= 5),
    comment     TEXT,
    review_date DATE NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES Movies (id),
    FOREIGN KEY (user_id) REFERENCES Users (id)
);


INSERT INTO Genres (genre_name)
VALUES ('Action'),
       ('Comedy'),
       ('Drama'),
       ('Fantasy'),
       ('Horror'),
       ('Romance'),
       ('Sci-Fi'),
       ('Thriller');


INSERT INTO Directors (name)
VALUES ('Steven Spielberg'),
       ('Martin Scorsese'),
       ('Christopher Nolan'),
       ('Quentin Tarantino');

INSERT INTO Actors (name)
VALUES ('Leonardo DiCaprio'),
       ('Brad Pitt'),
       ('Meryl Streep'),
       ('Tom Hanks');

INSERT INTO Movies (title, description, release_date, genre_id, director_id)
VALUES ('Inception',
        'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.',
        '2010-07-16', (SELECT id FROM Genres WHERE genre_name = 'Sci-Fi'),
        (SELECT director_id FROM Directors WHERE name = 'Christopher Nolan')),
       ('The Wolf of Wall Street',
        'Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.',
        '2013-12-25', (SELECT id FROM Genres WHERE genre_name = 'Drama'),
        (SELECT director_id FROM Directors WHERE name = 'Martin Scorsese')),
       ('Pulp Fiction',
        'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',
        '1994-10-14', (SELECT id FROM Genres WHERE genre_name = 'Thriller'),
        (SELECT director_id FROM Directors WHERE name = 'Quentin Tarantino'));
