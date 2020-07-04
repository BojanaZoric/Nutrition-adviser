INSERT INTO diet (id, diet_type) VALUES (1, "vegan");
INSERT INTO diet (id, diet_type) VALUES (2, "vegeterian");

INSERT INTO meal_type (id, meal_type) VALUES (1, "breakfast");
INSERT INTO meal_type (id, meal_type) VALUES (2, "lunch");
INSERT INTO meal_type (id, meal_type) VALUES (3, "dinner");
INSERT INTO meal_type (id, meal_type) VALUES (4, "dessert");

INSERT INTO allergen (id, name) VALUES (1, "milk");
INSERT INTO allergen (id, name) VALUES (2, "egg");
INSERT INTO allergen (id, name) VALUES (3, "gluten");

INSERT INTO grocerie (id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES (1, "sugar", 500, 50, 20, true);
INSERT INTO grocerie (id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES (2, "milk", 200, 20, 10, true);
INSERT INTO grocerie (id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES (3, "spagetti", 300, 15, 20, false);
INSERT INTO grocerie (id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES (4, "salt", 250, 25, 30, true);
INSERT INTO grocerie (id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES (5, "chocolate", 150, 20, 5, true);
INSERT INTO grocerie (id, name, calories, protein_amount, carbohydrate_amount, gluten_free) VALUES (6, "cheese", 150, 5, 40, true);


-- milk-milk
-- cheese-milk
-- spaggeti-egg
-- chocolate - milk
INSERT INTO grocerie_allergen(grocerie_id, allergen_id) VALUES (2, 1);
INSERT INTO grocerie_allergen(grocerie_id, allergen_id) VALUES (6, 1);
INSERT INTO grocerie_allergen(grocerie_id, allergen_id) VALUES (3, 2);
INSERT INTO grocerie_allergen(grocerie_id, allergen_id) VALUES (5, 1);

-- cake (200g sugar, 100g chocolate)
INSERT INTO meal (id, name, calories, meal_type_id) VALUES (1, "cake", 500, 4);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (1, 1, 200, 1);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (2, 5, 100, 1);

-- cake (200g spagetti, 100g cheese)
INSERT INTO meal (id, name, calories, meal_type_id) VALUES (2, "pasta", 500, 4);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (1, 3, 200, 1);
INSERT INTO grocerie_quantity (id, grocerie_id, quantity, meal_id) VALUES (2, 6, 100, 1);

--passwordi 123
INSERT INTO users (id, username, password, first_name, last_name, allowed_to_eat) VALUES (1, "user", "$2y$12$jsNdy6BLYxS80nLoUuMwDuIh9lyo8dgr8MRHLPleUT8TopbBHXsIq", "User", "User", true);
INSERT INTO users (id, username, password, first_name, last_name, allowed_to_eat) VALUES (2, "user2", "$2y$12$jsNdy6BLYxS80nLoUuMwDuIh9lyo8dgr8MRHLPleUT8TopbBHXsIq", "User2", "User2", true);

INSERT INTO user_data(id, user_id, height, weight, year_of_birth, gender, diet_id, diabetes, heart_disease, high_blood_pressure) VALUES (1,1, 190, 150, 1975, 0, null, false, false, true);

INSERT INTO user_allergy(user_data_id, allergen_id) VALUES(1, 1);

INSERT INTO meal_details(id, meal_id, date, user_data_id) values (1,1,'2020-05-30', 1);
INSERT INTO meal_details(id, meal_id, date, user_data_id) values (2,1,'2020-05-30', 1);