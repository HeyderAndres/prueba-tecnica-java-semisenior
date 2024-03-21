from fastapi import APIRouter
from config.database import conn
from models.store import store

store_routes = APIRouter(prefix="/store")

@store_routes.get("/")
async def get_stores():
    return conn.execute(store.select()).fetchall
