INSERT INTO diet (id, diet_type) VALUES (1, "vegan");
INSERT INTO diet (id, diet_type) VALUES (2, "vegeterian");

INSERT INTO meal_type (id, meal_type) VALUES (1, "breakfast");
INSERT INTO meal_type (id, meal_type) VALUES (2, "lunch");
INSERT INTO meal_type (id, meal_type) VALUES (3, "dinner");
INSERT INTO meal_type (id, meal_type) VALUES (4, "dessert");

INSERT INTO allergen (id, name) VALUES (1, "alcohol-free");
INSERT INTO allergen (id, name) VALUES (2, "celery-free");
INSERT INTO allergen (id, name) VALUES (3, "crustacean-free");
INSERT INTO allergen (id, name) VALUES (4, "dairy-free");
INSERT INTO allergen (id, name) VALUES (5, "egg-free");
INSERT INTO allergen (id, name) VALUES (6, "fish-free");
INSERT INTO allergen (id, name) VALUES (7, "fodmap-free");
INSERT INTO allergen (id, name) VALUES (8, "gluten-free");
INSERT INTO allergen (id, name) VALUES (9, "kosher");
INSERT INTO allergen (id, name) VALUES (10, "lupine-free");
INSERT INTO allergen (id, name) VALUES (11, "No-oil-added");
INSERT INTO allergen (id, name) VALUES (12, "peanut-free");
INSERT INTO allergen (id, name) VALUES (13, "pecatarian");
INSERT INTO allergen (id, name) VALUES (14, "pork-free");
INSERT INTO allergen (id, name) VALUES (15, "red-meat-free");
INSERT INTO allergen (id, name) VALUES (16, "sesame-free");
INSERT INTO allergen (id, name) VALUES (17, "shellfish-free");
INSERT INTO allergen (id, name) VALUES (18, "soy-free");


INSERT INTO grocerie (measure, id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES ("kg", 1, "sugar", 500, 50, 20, true);
INSERT INTO grocerie (measure, id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES ("l",2, "milk", 200, 20, 10, true);
INSERT INTO grocerie (measure, id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES ("kg", 3, "spagetti", 300, 15, 20, false);
INSERT INTO grocerie (measure, id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES ("gr",4, "salt", 250, 25, 30, true);
INSERT INTO grocerie (measure, id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES ("gr",5, "chocolate", 150, 20, 5, true);
INSERT INTO grocerie (measure, id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES ("gr",6, "cheese", 150, 5, 40, true);


-- milk-milk
-- cheese-milk
-- spaggeti-egg
-- chocolate - milk
INSERT INTO grocerie_allergen(grocerie_id, allergen_id) VALUES (2, 1);
INSERT INTO grocerie_allergen(grocerie_id, allergen_id) VALUES (6, 1);
INSERT INTO grocerie_allergen(grocerie_id, allergen_id) VALUES (3, 2);
INSERT INTO grocerie_allergen(grocerie_id, allergen_id) VALUES (5, 1);

-- cake (200g sugar, 100g chocolate)
INSERT INTO meal (id, name, calories, meal_type_id, protein_amount, description, preparation_time) VALUES (1, "Rich chocolate cake", 500, 4,15, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ", 15);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (1, 1, 200, 1);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (2, 5, 100, 1);

INSERT INTO meal (id, name, calories, meal_type_id, protein_amount, description, preparation_time) VALUES (3, "Biftek", 500, 4,15, "Lorem ipsum dolor sit amer", 45);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (7, 1, 200, 3);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (8, 5, 100, 3);

-- pasta (200g spagetti, 100g cheese)
INSERT INTO meal (id, name, calories, meal_type_id, protein_amount, description, preparation_time) VALUES (2, "Pasta sa skampima", 400, 4, 7, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ", 15);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (3, 3, 200, 2);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (4, 6, 100, 2);

--passwordi 123
INSERT INTO users (id, username, password, first_name, last_name, allowed_to_eat) VALUES (1, "user", "$2y$12$jsNdy6BLYxS80nLoUuMwDuIh9lyo8dgr8MRHLPleUT8TopbBHXsIq", "User", "User", true);
INSERT INTO users (id, username, password, first_name, last_name, allowed_to_eat) VALUES (2, "user2", "$2y$12$jsNdy6BLYxS80nLoUuMwDuIh9lyo8dgr8MRHLPleUT8TopbBHXsIq", "User2", "User2", true);

INSERT INTO authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO authority (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_data(id, user_id, height, weight, year_of_birth, gender, diet_id, diabetes, heart_disease, high_blood_pressure) VALUES (1,1, 190, 150, 1975, 0, null, false, false, true);

INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);

INSERT INTO user_allergy(user_data_id, allergen_id) VALUES(1, 1);

INSERT INTO meal_details(id, meal_id, date, user_data_id) values (1,1,'2020-05-30', 1);
INSERT INTO meal_details(id, meal_id, date, user_data_id) values (2,1,'2020-05-30', 1);