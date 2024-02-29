/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  heiderarellano
 * Created: 28/02/2024
 */

CREATE TABLE IF NOT EXISTS film(
    film_id INT IDENTITY PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL,
    year VARCHAR(10) NOT NULL,
    rental_duration BIGINT NOT NULL,
    rating BIGINT NOT NULL,
    duration BIGINT NOT NULL,
    rental_price BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS category(
    category_id INT IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS film_category(
    filmcategory_id INT IDENTITY PRIMARY KEY,
    film_id INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT film_fk FOREIGN KEY (film_id) REFERENCES film(film_id),
    CONSTRAINT category_fk FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE IF NOT EXISTS store(
    store_id INT IDENTITY PRIMARY KEY,
    address VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS inventory(
    inventory_id INT IDENTITY PRIMARY KEY,
    film_id INT NOT NULL,
    store_id INT NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT film_fk FOREIGN KEY (film_id) REFERENCES film(film_id),
    CONSTRAINT store_fk FOREIGN KEY (store_id) REFERENCES store(film_id)
);

-- Insertar películas
INSERT INTO film (title, description, year, rental_duration, rating, duration, rental_price)
VALUES 
    ('Pelicula 1', 'Descripción de la Pelicula 1', '2022', 7, 5, 120, 2.99),
    ('Pelicula 2', 'Descripción de la Pelicula 2', '2021', 5, 4, 110, 1.99),
    ('Pelicula 3', 'Descripción de la Pelicula 3', '2023', 6, 3, 100, 3.49);

-- Insertar categorías
INSERT INTO category (name, description)
VALUES 
    ('Acción', 'Películas de acción'),
    ('Comedia', 'Películas de comedia'),
    ('Drama', 'Películas de drama');

-- Insertar relaciones entre películas y categorías
INSERT INTO film_category (film_id, category_id)
VALUES 
    (1, 1),
    (2, 2),
    (3, 3);

-- Insertar tiendas
INSERT INTO store (address)
VALUES 
    ('Calle 123, Ciudad 1'),
    ('Avenida XYZ, Ciudad 2');

-- Insertar inventario
INSERT INTO inventory (film_id, store_id, quantity)
VALUES 
    (1, 1, 10),
    (2, 2, 5),
    (3, 1, 8);


