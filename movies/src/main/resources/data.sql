
-- Insertar películas
INSERT INTO film ( title, description, year_movie, rental_duration, rating, duration, rental_price)
VALUES
    ('Pelicula 1', 'Descripción de la Pelicula 1', '2022', 7, 5, 120, 2.99),
    ('Pelicula 2', 'Descripción de la Pelicula 2', '2021', 5, 4, 110, 1.99),
    ('Pelicula 3', 'Descripción de la Pelicula 3', '2023', 6, 3, 100, 3.49);

-- Insertar categorías
INSERT INTO category ( name, description)
VALUES
    ( 'Acción', 'Películas de acción'),
    ( 'Comedia', 'Películas de comedia'),
    ( 'Drama', 'Películas de drama');

-- Insertar relaciones entre películas y categorías
INSERT INTO film_category ( film_id, category_id)
VALUES
    ( 1, 1),
    ( 2, 2),
    ( 3, 3);

-- Insertar tiendas
INSERT INTO store ( address)
VALUES
    ( 'Calle 123, Ciudad 1'),
    ( 'Avenida XYZ, Ciudad 2');

-- Insertar inventario
INSERT INTO inventory ( film_id, store_id, quantity)
VALUES
    ( 1, 1, 10),
    ( 2, 2, 5),
    ( 3, 1, 8);
