from pydantic import BaseModel
from typing import Optional

class Filmcategory:
    filmcategory_id: Optional[int]
    film_id: int
    category_id: int