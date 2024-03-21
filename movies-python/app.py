from fastapi import FastAPI
from routes.category import category_routes
from routes.film import film_routes
from routes.store import store_routes
from routes.inventory import inventory_routes

app = FastAPI()


app.include_router(category_routes)
app.include_router(film_routes)
app.include_router(store_routes)
app.include_router(inventory_routes)


