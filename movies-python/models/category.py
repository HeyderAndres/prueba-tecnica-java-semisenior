from sqlalchemy import Column, Table
from sqlalchemy.sql.sqltypes import Integer, String
from config.database import meta, engine

category = Table(
    "category",
    meta,
    Column("category_id", Integer, primary_key=True, autoincrement=True),
    Column("name", String(255), nullable=False),
    Column("description", String(255), nullable=False)
)

meta.create_all(engine)


