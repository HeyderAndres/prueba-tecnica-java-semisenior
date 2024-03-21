from sqlalchemy import Table, Column
from sqlalchemy.sql.sqltypes import Integer, String
from config.database import meta, engine

store = Table(
    "store",
    meta,
    Column("store_id", Integer, primary_key=True),
    Column("address", String, nullable=False)
)

meta.create_all(engine)