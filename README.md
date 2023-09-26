

# Milestone - 1
# SR Catering Application Checklist

## Database Design

- [ ] Create an ER diagram of the database
- [ ] Write Create table scripts [script](/src/main/resources/db/migration/V1__create_users.sql)

![ER Diagram](https://iili.io/HtI76ga.png)

## Project Setup

- [ ] Create a new Java project
- [ ] Set up a MySQL database
- [ ] Add necessary libraries
	- [ ] JDBC, 
	- [ ] MySQL Connector, 
	- [ ] JUnit, 
	- [ ] Dotenv

## Module 1: User
#### Pre-requisites:
- [ ] Create User table
-  Attributes
		- int id
		- String name
		- String email
		- long phone_number
		- String password
		- boolean status
- [ ] User DAO
- [ ] User Service

		
### Feature 1: Create User
#### User story
User can create their profile.
#### Pre-requisites:
- Implements User Service(create)
- Implement User DAO(create)

#### Validation
- [ ] Form validation
		- user null
		- name (null or empty string)
		- email (null or empty string)
		- email pattern validation
		- password (null or empty string)
		- phone_number ( length, >= 600000001 && <= 9999999999 )
- [ ] Business validation
		- email alreadyExists 		
		
#### Messages
- [ ] User cannot be null
- [ ] Invalid Email Id
- [ ] Password doesn't match the required format
- [ ] name cannot be null or empty
- [ ] email cannot be null or empty
- [ ] password cannot be null or empty
- [ ] phone_number doesn't match the required format
- [ ] User already exists(Business validation)


#### Flow: 
```mermaid
graph TD;
  A["User Service: User Creation"] --> B["Form Validation"]
  B -- Valid--> C0["Business Validation"]
  C0 --> C["Unique Email Check"]
  B -- InValid--> C1["Validation Exception: Invalid input"]
  C -- Yes --> D["User DAO: Create User"]
  D --> E["Validation Exception: User Created (Response)"]
  C -- No --> F["User Service: Email Already Exists (Error Response)"]
```

### Feature 2: Update User
#### User story
User can update their profile.
#### Pre-requisites:
- Implements User Service(update(int id, User user))
- Implement User DAO(update(int id, User user))

#### Validation
- [ ] Form validation
		- id <=0
		- user null check
		- name (null or empty string)
		- email (null or empty string)
		- password (null or empty string)
		- phone_number ( length, >= 600000001 && <= 9999999999 )
- [ ] Business validation
		- Check whether id is in the table or not 

#### Messages
- [ ] User cannot be null
- [ ] name cannot be null or empty
- [ ] email cannot be null or empty
- [ ] password cannot be null or empty
- [ ] Invalid Email Id
- [ ] Password doesn't match the required format
- [ ] phone_number doesn't match the required format
- [ ] User Id not found(Business validation)		
		
#### Flow: 
```mermaid
graph TD;
  A["User Service: User Update "] --> 
  B["Form Validation"]
  B -- Valid --> C0["Business Validation"]
  C0 --> C["Whether the ID is in the database or not"]
  B -- InValid --> C1["Invalid input"]
  C -- Yes --> D["User DAO: Update User"]
  D --> E["User Service: User Updated(Response)"]
  C -- No --> F["Validation Exception: User not found for the given ID (Error Response)"]
```

### Feature 3: Delete User
#### User story
User can delete their profile.
#### Pre-requisites:
- Implements User Service(delete(int id))
- Implement User DAO(delete(int id))

#### Validation
- [ ] Form validation
		- id <=0
- [ ] Business validation
		- Check whether id is in the table or not

#### Messages
- [ ] Invalid Id
- [ ] User Id not found(Business validation)		

#### Flow: 
```mermaid
graph TD;
  A["User Service: User Delete"] --> B["Input Validation"]
  B -- Valid --> C0["Business Validation"]
  C0 --> C["Whether the ID is in the database or not"]
  B -- Invalid --> C1["Validation Exception: Invalid input"]
  C -- Yes --> D["User DAO: Delete User"]
  D --> E["User Service: User Deleted(Response)"]
  C -- No --> F["Validation Exception: User not found for the given ID (Error Response)"]
```

### Feature 4: FindAll
#### User story
User can see all User details.
#### Pre-requisites:
- Implements User Service(getAll( ))
- Implement User DAO(findAll( ))

#### Flow: 
```mermaid
graph TD;
  A["User Service: Findall User"] --> B["User DAO: Findall User"]
  B --> C["Retrive all users"]
```

## Module 2: Menu
#### Pre-requisites:
- [ ] Create Menu table
-  Attributes
		- int id
		- String menu_name
		- String menu_image
		- String menu_description
- [ ] Menu Service
- [ ] Menu DAO

### Feature 1: Create
#### Admin story
Admin can create Menu.
#### Pre-requisites:
- Implements Menu Service(createMenu( ))
- Implement Menu DAO(create( ))


#### Validation
- [ ] Form validation
		- menu null
		- menu_name (null or empty string)
		- menu_image (null or empty string)
		- menu_image pattern validation
		- menu_description (null or empty string)
- [ ] Business validation
		- menu_name alreadyExists 		
		
#### Messages
- [ ] Menu cannot be null
- [ ] menu_name cannot be null or empty
- [ ] menu_image cannot be null or empty
- [ ] menu_description cannot be null or empty
- [ ] menu_image doesn't match the required format
- [ ] Menu name already exists(Business validation

#### Flow: 
```mermaid
graph TD;
  A["Menu Service: Menu Creation"] --> B["Form Validation"]
  B -- Valid--> C0["Business Validation"]
  C0 --> C["Menu name already exists"]
  B -- InValid--> C1["Validation Exception: Invalid input"]
  C -- No --> D["Menu DAO: Create Menu"]
  D --> E["Menu Service: Menu Created (Response)"]
  C -- Yes --> F["Menu Service: Menu name Already Exists (Error Response)"]
```


### Feature 2: Update
#### Admin story
Admin can update Menu image and description.
#### Pre-requisites:
- Implements Menu Service(updateMenu( ))
- Implement Menu DAO(update( ))


#### Validation
- [ ] Form validation
		- menu null
		- menu_name (null or empty string)
		- menu_image (null or empty string)
		- menu_image pattern validation
		- menu_description (null or empty string)		
		
#### Messages
- [ ] Menu cannot be null
- [ ] menu_name cannot be null or empty
- [ ] menu_image cannot be null or empty
- [ ] menu_description cannot be null or empty
- [ ] menu_image doesn't match the required format

#### Flow: 
```mermaid
graph TD;
  A["Menu Service: Menu update"] --> B["Form Validation"]
  B -- Valid--> C["Menu Service: Menu updated(Response)"]
  B -- InValid--> C1["Validation Exception: Invalid input"]
```
	
### Feature 3: FindAll
#### Admin story
Admin can see all Menu details.
#### Pre-requisites:
- Implements Menu Service(getAll( ))
- Implement Menu DAO(findAll( ))

#### Flow: 
```mermaid
graph TD;
  A["Menu Service: Findall Menu"] --> B["Menu DAO: Findall Menu"]
  B --> C["Retrive all menus"]
```

### Feature 4: Find By MenuId
#### Admin story
Admin can find menu by menu_id.
#### Pre-requisites:
- Implements Menu Service(findByMenuId( ))
- Implement Menu DAO(findByMenuId( ))

#### Validation
- [ ] Form validation
		- menu_id (less than or equal to zero)	
- [ ] Business validaton
		- Check whether menu_id is in the table or not
		
#### Messages
- [ ] menu_id cannot be less than or equal to zero
- [ ] menu_id not found


#### Flow: 
```mermaid
graph TD;
  A["Menu Service: Menu update"] --> B["Form Validation"]
  B -- Valid --> C["Menu Service: Menu updated(Response)"]
  B -- InValid--> C1["Validation Exception: Invalid input"]
```

## Module 2: Category
#### Pre-requisites:
- [ ] Create Category table
-  Attributes
		- int id
		- String category_name
		- String category_image
		- String menu_id (foreign key)
- [ ] Category Service
- [ ] Category DAO

### Feature 1: Create
#### Admin story
Admin can create Category.
#### Pre-requisites:
- Implements CategoryService(createCategory( ))
- Implement CategoryDAO(create( ))


#### Validation
- [ ] Form validation
		-category null
		- category_name (null or empty string)
		- category _image (null or empty string)
		- category _image pattern validation
		- menu_id (less than or equal to zero)
- [ ] Business validation
		- menu_id valid or not
		- category_name alreadyExists 		
		
#### Messages
- [ ] Category cannot be null
- [ ] Category_name cannot be null or empty
- [ ] Category_image cannot be null or empty
- [ ] Category_image doesn't match the required format
- [ ] Category name already exists(Business validation)
- [ ] Invalid Menu_id

#### Flow: 
```mermaid
graph TD;
  A["Category Service: Category Creation"] --> B["Form Validation"]
  B -- Valid--> C0["Business Validation"]
  C0 --> C["Category name already exists for that menu"]
  C -- Yes --> F["Category Service: Category name Already Exists (Error Response)"]
  B -- InValid--> C1["Validation Exception: Invalid input"]
  C -- No --> D["menu_id is valid or not"]
  D -- valid --> E["Category DAO: Create Category"]
  D -- Invalid --> G["Validation Exception: Invalid menu_id"]
```

### Feature 2: Update
#### Admin story
Admin can update Category.
#### Pre-requisites:
- Implements CategoryService(updateCategory( ))
- Implement CategoryDAO(update( ))


#### Validation
- [ ] Form validation
		-category null
		- category_name (null or empty string)
		- category _image (null or empty string)
		- category _image pattern validation
		- menu_id (less than or equal to zero)
- [ ] Business validation
		- menu_id valid or not		
		
#### Messages
- [ ] Category cannot be null
- [ ] Category_name cannot be null or empty
- [ ] Category_image cannot be null or empty
- [ ] Category_image doesn't match the required format
- [ ] Invalid Menu_id

#### Flow: 
```mermaid
graph TD;
  A["Category Service: Category update"] --> B["Form Validation"]
  B -- Valid--> C0["Business Validation"]
  B -- InValid--> C1["Validation Exception: Invalid input"]
  C0 --> C["menu_id is valid or not"]
  C -- valid --> E["Category DAO: Update Category"]
  C -- Invalid --> G["Validation Exception: Invalid menu_id"]
```

### Feature 3: FindAll Category By Menu id
#### Admin story
Admin can see all Category details by menu_id.
#### Pre-requisites:
- Implements Category_Dish Service(findCategoryByMenuId( ))
- Implement Category_Dish DAO(findCategoryByMenuId( ))
- Implements Category Service(getAll( ))
- Implement Category DAO(findAll( ))

#### Validation
- [ ] Form Validation
		- menu_id <= 0
- [ ] Business Validation
		- check menu_id is in the table or not

#### Messages
- [ ] Invalid menu_id
- [ ] menu_id not found

#### Flow: 
```mermaid
graph TD;
  A["Category_Dish Service: findCategoryByMenuId(int menu_id)"] --> B["Form Validation"]
  B -- Valid --> C["Business Validation"]
  B -- Invalid --> C1["Validation Exception: Invalid input"]
  C --> D["Category_Dish Service: findCategoryByMenuId(int menu_id"]
  D --> E["Check the menu_id is in the table or not"]
  E -- Yes --> F["Category DAO:Get all categories in the menu_id"]
  E -- No --> F1["Validation Exception: menu_id not found"]
```

## Module 3: Dish
#### Pre-requisites:
- [ ] Complete Menu module Feature 1
- [ ] Complete Category module Feature 1
- [ ] Create Dish Table
- [ ] Create Dish Service
- [ ] Complete Dish DAO
- [ ] Create Dish_Price Table
- [ ] Complete Dish_Price Service
- [ ] Complete Dish_Price DAO
- [ ] Create Category_Dishes Table
- [ ] Complete Category_Dishes Service
- [ ] Complete Category_Dishes DAO
- [ ] Create Dish table
- [ ] Dish Entity model (abstract class)
-  Attributes
		- int id
		- String dish_name
		- int quantity
		- String quantity_unit
		- int price
		- int menu_id (foreign key)
		- int category_id (foreign key)
		- Date start_date
		- Date end_date
		- boolean status
- [ ] Dish extends Dish Entity model

### Feature 1: Create Dish
#### Admin story
Admin can create Dish.
#### Pre-requisites:
- Implements Dish Service(create)
- Implement Dish DAO(create)-String dish_name, int quantity,String quantity_unit
- Implement Category_Dish Service(create)
- Implement Category_Dish DAO(create)-menu_id(foreign key), category_id(foreign key), dish_id(foreign key), status
- Implement Dish_Price Service(create)
- Implement Dish_Price DAO(create)-int price, start_date, end_date, dish_id(foreign key)

#### Validation
- [ ] Form Validation
		- dish null check
		- quantity <0 && quantity >=10
		- dish_name (null or empty string)
		- quantity_unit (null or empty string)
		- menu_id <=0
		- category_id <=0
		- price <0
- [ ] Business Logic
		- Is the menu_id is in the Menu table?
		- Is the category_id is in the Category table?
		- dish name alreadyExists in the same Menu & Category in Category_Dish table

#### Messages
- dish cannot be null
- Invalid quantity
- dish_name cannot be null or empty
- quantity_unit cannot be null or empty
- Invalid menu_id
- Invalid category_id
- Invalid price
- menu_id not found (Business Validation)
- category_id not found (Business Validation)
- dish_name alreadyexists(Business Validation)


#### Flow: 
```mermaid
graph TD;
  A["Dish Service: Dish Creation"] --> B["Form validation"]
B -- Valid --> C0["Business Validation"]
C0 --> C["Category_Dish Service"]
B -- Invalid --> C1["Validation Exception: Invalid input"]
C --> D0["Check the menu_id is in the table or not"]
D0 -- Yes --> D["Check the category_id is in the table or not"]
D0 -- No --> D1["Validation Exception: menu_id not found"]
D -- Yes --> E["Check the dish_name already exists"]
D -- No --> E1["Validation Exception: category_id not found"]
E -- Yes --> F["dish_name already exists"]
E -- No --> F1["Dish DAO: Create Dish"]
F1 -- dish_id --> G["Category_Dish DAO: Category_Dish Creation"]
F1 -- dish_id --> H["Dish_Price DAO: Dish_Price Creation"]
  ```

### Feature 2: Update Dish
#### Admin story
Admin can upate Dish details.
#### Pre-requisites:
- Implements Dish Service(update)-int dish_id, Dish dish
- Implement Dish DAO(update)- quantity, quantity_unit, dish_id
- Implement Category_Dish Service(update)
- Implement Category_Dish DAO(update)- dish_id(foreign key), status
- Implement Dish_Price Service(update)
- Implement Dish_Price DAO(update)-price, dish_id(foreign key)

#### Validation
- [ ] Form Validation
		- dish null check
		- id<=0
		- quantity <0 && quantity >=10
		- quantity_unit (null or empty string)
		- price <0
- [ ] Business Logic
		- Is the dish_id is in the table or not?

#### Messages
- dish cannot be null
- Invalid quantity
- quantity_unit cannot be null or empty
- Invalid price
- dish_id not found (Business Validation)
		

#### Flow: 
```mermaid
graph TD;
  A["Dish Service: Dish Updation"] --> B["Form validation"]
B -- valid --> C0["Business Validation"]
C0 --> C["Category_Dish Service"]
B -- Invalid --> D["Validation Exception: Invalid input"]
C --> E["Check whether the dish_id is in the Category_Dish table or not"]
E -- Yes -->F["Dish DAO: Update Dish"]
E -- No -->F1["Validation Exception: dish_id not found"]
F --> G["Dish_Price: Update Dish_Price"]
G --> H["Set the end_date on current Price"]
H --> I["Dish_Price: Create Dish_Price"]
  ```

### Feature 3: Delete Dish
#### Admin story
Admin can delete Dish.
#### Pre-requisites:
- Implement Category_Dish Service(delete)- int dish_id
- Implement Category_Dish DAO(delete)- int dish_id

#### Validation
- [ ] Form Validation
		- id <=0
- [ ] Business Validation
		- Check dish_id is in the table or not

#### Messages
- Invalid dish_id
- dish_id not found (Business Validation)

		
#### Flow: 
```mermaid
graph TD;
  A["Dish Service: Dish deletion"] --> B["Form validation"]
B -- Valid --> C["Business Validation"]
B -- Invalid--> C1["Validation Exception: Invalid input"]
C --> D["Category_Dish Service: Delete Dish"]
D --> D1["Check whether the dish_id is in the Category_Dish table or not"]
D1 -- Yes --> E["Category_Dish DAO: Delete Dish"]
D1 -- No --> E1["Validation Exception: dish_id not found"]
  ```

### Feature 4: FindBy Dish Id
#### Admin story
Admin can find Dish by dish_id.
#### Pre-requisites:
- Implement Category_Dish Service(findById)- int dish_id
- Implement Category_Dish DAO(findById)- int dish_id
- Implement Dish Service(findById)- int dish_id
- Implement Dish DAO(findById)- int dish_id

#### Validation
- [ ] Form Validation
		- id <=0
- [ ] Business Validation
		- Check dish_id is in the table or not


#### Flow: 
```mermaid
graph TD;
  A["Dish Service: findByDishId(dish_id)"] --> 
  B["Input validation"]
  B -- Valid --> B1["Business Validation"]
  B -- Invalid --> B2["Validation Exception: Invalid Input"]
  B1 --> B3["Category_Dish Service: findByDishId(dish_id)"]
  B3 --> C["Check whether dish-id is in the table or not"]
  C -- Yes --> D["Dish DAO: findByDishId(dish_id)"]
  C -- No --> D1["Validation Exception: dish_id not found"]
```


### Feature 5: ListAll Dish
#### Admin story
Admin can find all Dishes.
#### Pre-requisites:
- Implement Category_Dish Service(getAll)
- Implement Category_Dish DAO(findAll)
- Complete Dish Service(findById)- int dish_id
- Complete Dish DAO(findById)- int dish_id

#### Flow: 
```mermaid
graph TD;
  A["Category_Dish Service: getAll dish_id"] -->   
  B["Category_Dish DAO: FindAll dish_id"]
  B --> C["dishIdList created"]
  C --> D["Store all dish_id in the dishIdList"]
  D --> E["Dish DAO: findByDishId(dish_id)"]
  E --> F["dishList created"]
  F --> G["Store all dish details in the dishList"]
```

### Feature 6: FindAll Dishes By menu_id & category_id
#### Admin story
Admin can find all Dishes by menu_id and category_id.
#### Pre-requisites:
- Implement Category_Dish Service findDishesByMenuIdCategoryId(int menu_id, int category_id)
- Implement Category_Dish findDishesByMenuIdCategoryId(int menu_id, int category_id)
- Complete Dish Service(findById)- int dish_id
- Complete Dish DAO(findById)- int dish_id

#### Validation
- [ ] Form Validation
		- menu_id <=0
		- category_id <=0
- [ ] Business Validation
		- Check menu_id is in the table or not
		- Check category_id is in the table or not

#### Messages
- Invalid menu_id
- Invalid category_id
- menu_id not found (Business Validation)
- category_id not found (Business Validation)

#### Flow: 
```mermaid
graph TD;
  A["Category_Dish Service: findBymenuIdAndCategoryId(int menu_id, int category_id)"] --> B["Input Validation"]
  B -- Valid --> B1["Business Validation"]
  B -- Invlaid--> B2["Validation Exception: Invalid Input"]
  B1 --> C["Check menu_id is in the table or not"]
  C -- Yes --> D["Check category_id is in the table or not"]
  C -- No --> D1["Validation Exception: menu_id not found"]
  D -- Yes --> E["Category_Dish DAO: findBymenuIdAndCategoryId(int menu_id, int category_id)"]
  D -- No --> E1["Validation Exception: category_id not found"]
  E --> F["Store all dish_id in the dishIdList"]
  F --> G["Dish DAO: findById(int dish_id)"]
  G --> H["dishList created"]
  H --> I["Store all dish details in the dishList"]
```

## Module 4: Cart
#### Pre-requisites:
- [ ] Complete Menu module Feature 1
- [ ] Complete Category module Feature 1
- [ ] Complete Dish module Feature 1
- [ ] Create cart Table
- [ ] Complete cart Entity
- [ ] Complete cart Service
- [ ] Complete cart DAO
-  Attributes
		- int id
		- int user_id
		- int menu_id
		- int category_id
		- int price
		- int no_of_guest
		- LocalDate delivery_date
		
### Feature 1: Create Cart
#### User story
User can create Cart.
#### Pre-requisites:
- Implements Cart Service(create)
- Implement Cart DAO(create)

#### Validation
- [ ] Form Validation
		- cart not null
		- user_id <= 0 check
		- menu_id <= 0 check
		- category_id <= 0 check
		- no_of_guest < 50 || no_of_guest > 1500
		- price < 0
- [ ] Business Logic
		- Is the user_id is in the User table?
		- Is the menu_id is in the Menu table?
		- Is the category_id is in the Category table?
		- Is the menu_id and category_id already exists in the cart for that user

#### Messages
- cart cannot be null
- user_id cannot be less than or equal to zero
- menu_id cannot be less than or equal to zero
- category_id cannot be less than or equal to zero
- no_of_guest cannot be less than 50 (or) greater than 1500
- price cannot be less than zero
- Invalid user_id (Business Validation)
- Invalid menu_id (Business Validation)
- Invalid category_id (Business Validation)
- This menu with category already exists



#### Flow: 
```mermaid
graph TD;
  A["Cart Service: Cart Creation"] --> B["Form validation"]
B -- Valid --> C["Business Validation"]
B -- Invalid --> C1["Validation Exception: Invalid input"]
C --> C2["Check the user_id is valid or not"]
C2 -- valid --> D["Check the menu_id is valid or not"]
C2 -- Invalid --> C3["Validation Exception: user_id not found"]
D -- valid --> E["Check the category_id is valid or not"]
D -- Invalid --> E0[Validation Exception: menu_id not found""]
E -- valid --> E1["Is the menu_id and category_id already exists for that user"]
E -- Invalid --> D2["Validation Exception: category_id not found"]
E1 -- Yes --> E2["This menu with category is already exists in the cart"]
E1 -- No --> F["Cart DAO: Cart create"]
  ```


### Feature 2: Update Cart
#### User story
User can update Cart.
#### Pre-requisites:
- Implements Cart Service(update)
- Implement Cart DAO(update)

#### Validation
- [ ] Form Validation
		- cart_id <= 0
		- no_of_guest < 50 || no_of_guest > 1500
		- price < 0
		- delivery_date < 7 days from today (or) > 2 months from today
- [ ] Business Logic
		- Is the cart_id is in the Category table?

#### Messages
- cart cannot be null
- cart_id cannot be less than or equal to zero
- no_of_guest cannot be less than 50 (or) greater than 1500
- price cannot be less than zero
- Invalid cart_id (Business Validation)

#### Flow: 
```mermaid
graph TD;
  A["Cart Service: Cart Update"] --> B["Form validation"]
B -- Valid --> C["Business Validation"]
B -- Invalid --> C1["Validation Exception: Invalid input"]
C --> C2["Check the cart_id is valid or not"]
C2 -- valid --> D["Cart DAO: Update Cart"]
C2 -- Invalid --> C3["Validation Exception: cart_id not found"]
  ```


### Feature 3: Remove Cart
#### User story
User can remove Cart.
#### Pre-requisites:
- Implements Cart Service(remove)
- Implement Cart DAO(remove)

#### Validation
- [ ] Form Validation
		- cart_id <= 0
- [ ] Business Logic
		- Is the cart_id is in the Category table?

#### Messages
- cart_id cannot be less than or equal to zero
- Invalid cart_id (Business Validation)

#### Flow: 
```mermaid
graph TD;
  A["Cart Service: Cart Remove"] --> B["Form validation"]
B -- Valid --> C["Business Validation"]
B -- Invalid --> C1["Validation Exception: Invalid input"]
C --> C2["Check the cart_id is valid or not"]
C2 -- valid --> D["Cart DAO: Remove Cart"]
C2 -- Invalid --> C3["Validation Exception: cart_id not found"]
  ```


## Module 5: Order
#### Pre-requisites:
- [ ] Complete Menu module Feature 1
- [ ] Complete Category module Feature 1
- [ ] Complete Dish module Feature 1
- [ ] Create order Table
- [ ] Complete order  Entity
- [ ] Complete order  Service
- [ ] Complete order  DAO
-  Attributes
		- int id
		- int user_id
		- int address_id
		- int total_cost
		- LocalDateTime order_date
		- String event_name

### Feature 1: Create Order
#### User story
User can create Order.
#### Pre-requisites:
- Implements Order Service(create)
- Implement Order DAO(create)

#### Validation
- [ ] Form Validation
		- user_id <= 0 check
		- address_id <= 0 check
		- total_cost <= 0 check
		- event-name (null or empty string)
- [ ] Business Logic
		- Is the user_id is in the User table?
		- Is the address_id is in the Menu table?

#### Messages
- order cannot be null
- user_id cannot be less than or equal to zero
- address_id cannot be less than or equal to zero
- total_cost cannot be less than or equal to zero
- Invalid user_id (Business Validation)
- Invalid address_id (Business Validation)


#### Flow: 
```mermaid
graph TD;
  A["Order Service: Order Creation"] --> B["Form validation"]
B -- Valid --> C["Business Validation"]
B -- Invalid --> C1["Validation Exception: Invalid input"]
C --> C2["Check the user_id is valid or not"]
C2 -- valid --> D["Check the address_id is valid or not"]
C2 -- Invalid --> C3["Validation Exception: user_id not found"]
D -- valid --> E["Order DAO: Create Order"]
D -- Invalid --> E0[Validation Exception: address_id not found""]
  ```


## Module 5: OrderProduct
#### Pre-requisites:
- [ ] Complete Menu module Feature 1
- [ ] Complete Category module Feature 1
- [ ] Complete Dish module Feature 1
- [ ] Create order_products Table
- [ ] Complete order_products Entity
- [ ] Complete order_products Service
- [ ] Complete order_products DAO
-  Attributes
		- int id
		- int order_id
		- int menu_id
		- int category_id
		- int dish_id
		- int price_id
		- int no_of_guest
		- LocalDate delivery_date
		- String order_status
		- String cancel_date
		- String cancel_reason
		- String cater_approval
		- String reject_reason


### Feature 1: Create Order
#### User story
User can create OrderProduct.
#### Pre-requisites:
- Implements OrderProduct Service(create)
- Implement OrderProduct DAO(create)

#### Validation
- [ ] Form Validation
		- order_id <= 0 check
		- menu_id <=0 check
		- category_id <=0 check
		- dish_id <= 0 check
		- price_id <= 0 check
		- no_of_guest <= 0 check
		- delivery_date < 7 days from today (or) > 2 months from today
- [ ] Business Logic
		- Is the order_id is in the Order table?
		- Is the menu_id is in the Menu table?
		- Is the category_id is in the Category table?
		- Is the dish_id is in the Dish table?
		- Is the price_id is in the DishPrice table?

#### Messages
- orderProduct cannot be null
- order_id cannot be less than or equal to zero
- menu_id cannot be less than or equal to zero
- category_id cannot be less than or equal to zero
- dish_id cannot be less than or equal to zero
- price_id cannot be less than or equal to zero
- no_of_guest cannot be less than or equal to zero
- delivery_date cannot be less than 7 days from today or more than 2 months from today
- Invalid order_id (Business Validation)
- Invalid menu_id (Business Validation)
- Invalid category_id (Business Validation)
- Invalid dish_id (Business Validation)
- Invalid price_id (Business Validation)


#### Flow: 
```mermaid
graph TD;
  A["Order Service: Order Creation"] --> B["Form validation"]
B -- Valid --> C["Business Validation"]
B -- Invalid --> C1["Validation Exception: Invalid input"]
C --> C2["Check the user_id is valid or not"]
C2 -- valid --> D["Check the address_id is valid or not"]
C2 -- Invalid --> C3["Validation Exception: user_id not found"]
D -- valid --> E["Order DAO: Create Order"]
D -- Invalid --> E0[Validation Exception: address_id not found""]
  ```
