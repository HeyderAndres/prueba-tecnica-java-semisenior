from sqlalchemy import Column, Table, ForeignKey
from sqlalchemy.sql.sqltypes import Integer, String, Float
from config.database import meta, engine

film = Table(
    "film",
    meta,
    Column("film_id", Integer, primary_key=True),
    Column("title", String, nullable=False),
    Column("description", String, nullable=False),
    Column("year", String, nullable= False),
    Column("rental_duration", Float, nullable=False),
    Column("rating", Float, nullable=False),
    Column("duration", Float, nullable=False),
    Column("rental_price", Float, nullable=False),
    Column("category_id", Integer, ForeignKey("category.category_id"))
)

meta.create_all(engine)