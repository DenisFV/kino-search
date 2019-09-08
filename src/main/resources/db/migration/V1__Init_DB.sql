CREATE SEQUENCE hibernate_sequence
  START 1
  INCREMENT 1;

CREATE TABLE movie (
  movie_id      BIGSERIAL NOT NULL,
  filter_date   DATE      NOT NULL,
  movie_year    INT4      NOT NULL,
  original_name VARCHAR(255),
  rating        FLOAT8    NOT NULL,
  top_position  INT8      NOT NULL,
  voted         INT8      NOT NULL,
  PRIMARY KEY (movie_id)
)
