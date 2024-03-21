from sqlalchemy import create_engine, MetaData

engine = create_engine("sqlite+pysqlite:///:memory:", echo=True)

meta = MetaData()

conn = engine.connect()