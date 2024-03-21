from config.database import conn
from models.category import category
from sqlalchemy import insert
from schemas.category import CategoryRequest, CategoryResponse

class CategoryService:

    async def find_all(self):
        return conn.execute(category.select()).fetchall()
    
    async def find_by_id(self, id: int):
        return conn.execute(category
                            .select()
                            .where(category.c.category_id == id)
                            ).fetchone()
    
    async def create(self, category_dto: CategoryRequest) -> CategoryResponse:
        stmt = insert(category).values(name=category_dto.name, 
                                       description=category_dto.description)
        result = conn.execute(stmt)
        conn.commit()
        
        return conn.execute(category.select().where(category.c.category_id == result.lastrowid)).first()