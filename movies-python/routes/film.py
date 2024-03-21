from fastapi import APIRouter
from config.database import conn
from models.film import film

film_routes = APIRouter(prefix="/film")

@film_routes.get("/")
async def get_films():
    return conn.execute(film.select()).fetchall()