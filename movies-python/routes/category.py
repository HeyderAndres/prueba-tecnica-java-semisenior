from fastapi import APIRouter, status
from service.category import CategoryService
from schemas.category import CategoryRequest, CategoryResponse

category_routes = APIRouter(prefix="/category")

category_service = CategoryService()

@category_routes.get("/")
async def get_categories() -> list[CategoryResponse]:
    return await category_service.find_all()

@category_routes.get("/{id}")
async def get_category(id: int) -> CategoryResponse:
    return await category_service.find_by_id(id)

@category_routes.post("/", response_model=CategoryResponse, status_code=status.HTTP_201_CREATED)
async def create_category(category_dto: CategoryRequest):
    return await category_service.create(category_dto)