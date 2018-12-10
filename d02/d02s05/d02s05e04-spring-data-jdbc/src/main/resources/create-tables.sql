CREATE TABLE IF NOT EXISTS Section (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS Product (
  id INTEGER IDENTITY PRIMARY KEY,
  sectionId INTEGER NOT NULL REFERENCES Section(id),
  name VARCHAR(30),
  price DOUBLE NOT NULL,
);

CREATE TABLE IF NOT EXISTS Discount (
  id INTEGER IDENTITY PRIMARY KEY,
  sectionId INTEGER NOT NULL REFERENCES Section(id),
  name VARCHAR(30),
  price DOUBLE NOT NULL,
);