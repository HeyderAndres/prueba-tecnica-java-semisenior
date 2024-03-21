from pydantic import BaseModel

class CategoryRequest(BaseModel):
    name: str
    description: str


class CategoryResponse(CategoryRequest):
    category_id: int
