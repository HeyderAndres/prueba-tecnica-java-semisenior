from pydantic import BaseModel
from typing import Optional

class Store:
    store_id: Optional[int]
    address: str