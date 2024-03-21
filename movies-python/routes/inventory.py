from fastapi import APIRouter
from config.database import conn
from models.inventory import inventory

inventory_routes = APIRouter(prefix="/invetory")

@inventory_routes.get("/")
async def get_inventories():
    return conn.execute(inventory.select()).fetchall()