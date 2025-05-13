### Account

_Description_: Represents a user account in the system.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | no | Unique identifier for the account. |
| username | String | no | Unique username for login. |
| password | String | no | Hashed password for authentication. |
| role | String | no | Role of the user (e.g., ADMIN, USER). |
| createdAt | LocalDateTime | no | Timestamp when the account was created. |
| updatedAt | LocalDateTime | no | Timestamp when the account was last updated. |

### ApiError

_Description_: DTO representing an API error response.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| status | int | no | HTTP status code. |
| error | String | no | Error type or summary. |
| message | String | no | Detailed error message. |
| path | String | no | Request path where the error occurred. |
| timestamp | LocalDateTime | no | Time when the error occurred. |

### ApiResponse

_Description_: Generic DTO for API responses.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| status | String | no | Status of the response (e.g., "success", "error"). |
| data | T | no | Response data payload. |
| message | String | no | Additional message. |
| timestamp | LocalDateTime | no | Time when the response was generated. |

### GlobalExceptionHandler

_Description_: -

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| logger | Logger | no | - |

### Product

_Description_: Represents a product in the inventory.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | no | Unique identifier for the product. |
| name | String | no | Name of the product. |
| description | String | no | Description of the product. |
| price | Double | no | Price per unit. |
| stockQuantity | Integer | no | Number of units in stock. |
| createdAt | LocalDateTime | no | Timestamp when the product was created. |
| updatedAt | LocalDateTime | no | Timestamp when the product was last updated. |

### ProductDTO

_Description_: DTO for transferring product data.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| name | String | no | Name of the product. |
| description | String | no | Description of the product. |
| price | Double | no | Price per unit. |
| stockQuantity | Integer | no | Number of units in stock. |

### PurchaseOrder

_Description_: Represents a purchase order in the system.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | no | Unique identifier for the purchase order. |
| status | PurchaseOrderStatus | no | Status of the purchase order (see PurchaseOrderStatus). |
| totalAmount | Double | no | Total amount for the order. |
| orderDate | LocalDateTime | no | Date/time when the order was placed. |
| createdAt | LocalDateTime | no | Timestamp when the order was created. |
| updatedAt | LocalDateTime | no | Timestamp when the order was last updated. |

### PurchaseOrderDTO

_Description_: DTO for transferring purchase order data.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| status | PurchaseOrderStatus | no | Status of the purchase order. |
| items | List | no | List of purchase order items. |

### PurchaseOrderItem

_Description_: Represents an item within a purchase order.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | no | Unique identifier for the item. |
| purchaseOrder | PurchaseOrder | no | Reference to the parent purchase order. |
| product | Product | no | Reference to the product being ordered. |
| quantity | int | no | Quantity of the product ordered. |
| createdAt | LocalDateTime | no | Timestamp when the item was created. |
| updatedAt | LocalDateTime | no | Timestamp when the item was last updated. |

### PurchaseOrderItemDTO

_Description_: DTO for transferring purchase order item data.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | no | Item identifier. |
| purchaseOrderId | Long | no | ID of the parent purchase order. |
| productId | Long | no | ID of the product. |
| quantity | int | no | Quantity ordered. |

### PurchaseOrderStatus

_Description_: Enum representing the status of a purchase order.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| DRAFT | - | no | Draft state. |
| SUBMITTED | - | no | Submitted for review. |
| UNDER_REVIEW | - | no | Under review/validation. |
| APPROVED | - | no | Approved order. |
| REJECTED | - | no | Rejected order. |
| CANCELED | - | no | Canceled order. |

### Supplier

_Description_: Represents a supplier entity in the system.

| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | no | Unique identifier for the supplier. |
| name | String | no | Name of the supplier. |
| contactInfo | String | no | Contact information for the supplier. |
| createdAt | LocalDateTime | no | Timestamp when the supplier was created. |
| updatedAt | LocalDateTime | no | Timestamp when the supplier was last updated. |

