from pydantic import BaseModel
from typing import Optional

class Film:
    film_id:  Optional[int]
    title: str
    description: str
    year_movie: str
    rental_duration: float
    rating: float
    duration: float
    rental_price: float
    category_id: Optional[int]

