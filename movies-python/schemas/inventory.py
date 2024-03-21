from pydantic import BaseModel
from typing import Optional

class Inventory:
    inventory_id: Optional[int]
    film_id: int
    store_id: int
    quantity: int