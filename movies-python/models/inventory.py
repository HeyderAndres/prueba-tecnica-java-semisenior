from sqlalchemy import Table, Column, ForeignKey
from sqlalchemy.sql.sqltypes import String, Integer
from config.database import meta, engine

inventory = Table(
    "inventory",
    meta,
    Column("inventory_id", Integer, primary_key=True),
    Column("film_id", Integer, ForeignKey("film.film_id")),
    Column("store_id", Integer, ForeignKey("store.store_id")),
    Column("quantity", Integer, nullable=False)
)

meta.create_all(engine)