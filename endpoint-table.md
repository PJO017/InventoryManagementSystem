| Controller | Method | Path | Params | Description |
|------------|--------|------|--------|-------------|
| Products | GET | `/api/products` | - | Retrieves all products. |
| Products | POST | `/api/products` | - | Creates a new product. |
| Products | GET | `/api/products/{id}` | id (path, required) | Retrieves a product by its ID. |
| Products | PUT | `/api/products/{id}` | id (path, required) | Updates an existing product by its ID. |
| Products | DELETE | `/api/products/{id}` | id (path, required) | Deletes a product by its ID. |
| Purchase-Orders | GET | `/api/purchase-orders` | - | Retrieves all purchase orders. |
| Purchase-Orders | POST | `/api/purchase-orders` | - | Creates a new purchase order. |
| Purchase-Orders | GET | `/api/purchase-orders/{id}` | id (path, required) | Retrieves a purchase order by its ID. |
| Purchase-Orders | PUT | `/api/purchase-orders/{id}` | id (path, required) | Updates an existing purchase order by its ID. |
| Purchase-Orders | DELETE | `/api/purchase-orders/{id}` | id (path, required) | Deletes a purchase order by its ID. |
| Purchase-Orders | GET | `/api/purchase-orders/{id}/items` | id (path, required) | Retrieves all items for a specific purchase order. |
