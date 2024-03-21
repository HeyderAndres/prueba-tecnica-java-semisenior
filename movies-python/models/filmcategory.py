from sqlalchemy import Table, Column, ForeignKey
from sqlalchemy.sql.sqltypes import Integer
from config.database import meta, engine

filmcategory = Table(
    "filmcategory",
    meta,
    Column("filmcategory_id", Integer, primary_key=True),
    Column("film_id", Integer, ForeignKey("film.film_id")),
    Column("category_id", Integer, ForeignKey("category.category_id"))
)

meta.create_all(engine)