


-- This script deletes everything in your database
\set QUIET true
SET client_min_messages TO WARNING; -- Less talk please.
-- Use this instead of drop schema if running on the Chalmers Postgres server
-- DROP OWNED BY TDA357_XXX CASCADE;
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
\set QUIET false

CREATE TABLE Classification
(
keyword TEXT,
category TEXT CHECK(category IN('Compost', 'Plastic', 'Glass', 'Hard Paper', 'Residual', 
'Recyclable', 'Electronics','Batteries','Recycle Center', 'database')),
db_version INTEGER DEFAULT NULL,
PRIMARY KEY(keyword)
);

--db version
INSERT INTO Classification VALUES ('database' , 'database', 2);

-- Compost
INSERT INTO Classification VALUES ('Banana' , 'Compost');
INSERT INTO Classification VALUES ('Apple' , 'Compost');
INSERT INTO Classification VALUES ('Pear' , 'Compost');
INSERT INTO Classification VALUES ('Fruit' , 'Compost');
INSERT INTO Classification VALUES ('Food' , 'Compost');
INSERT INTO Classification VALUES ('Plant' , 'Compost');
INSERT INTO Classification VALUES ('Salad' , 'Compost');
INSERT INTO Classification VALUES ('Vegetable' , 'Compost');
INSERT INTO Classification VALUES ('Dog' , 'Compost');

--Plastic 
INSERT INTO Classification VALUES ('Plastic Bag' , 'Plastic');
INSERT INTO Classification VALUES ('Plastic' , 'Plastic');
INSERT INTO Classification VALUES ('Fork' , 'Plastic');
INSERT INTO Classification VALUES ('Box' , 'Plastic');
INSERT INTO Classification VALUES ('Food Storage Containers' , 'Plastic');
INSERT INTO Classification VALUES ('Toy Block' , 'Plastic');

--Glass
INSERT INTO Classification VALUES ('Glass Bottle' , 'Glass');
INSERT INTO Classification VALUES ('Bottle' , 'Glass');
INSERT INTO Classification VALUES ('Green' , 'Glass');
INSERT INTO Classification VALUES ('Brown' , 'Glass');
INSERT INTO Classification VALUES ('Clear' , 'Glass');
INSERT INTO Classification VALUES ('Transparent Material' , 'Glass');
INSERT INTO Classification VALUES ('Beer Bottle' , 'Glass');
INSERT INTO Classification VALUES ('Wine Bottle' , 'Glass');

--Paper
INSERT INTO Classification VALUES ('Cardboard' , 'Hard Paper');
INSERT INTO Classification VALUES ('Carton' , 'Hard Paper');
INSERT INTO Classification VALUES ('Paper Product' , 'Hard Paper');
INSERT INTO Classification VALUES ('Shipping Box' , 'Hard Paper');
INSERT INTO Classification VALUES ('Packing Materials' , 'Hard Paper');
INSERT INTO Classification VALUES ('Construction Paper' , 'Hard Paper');


--Recyclable
INSERT INTO Classification VALUES ('Beverage Can' , 'Recyclable');
INSERT INTO Classification VALUES ('Aluminum Can' , 'Recyclable');
INSERT INTO Classification VALUES ('Plastic Bottle' , 'Recyclable');

--Residual
INSERT INTO Classification VALUES ('Paper' , 'Residual');
INSERT INTO Classification VALUES ('Paper Towel' , 'Residual');
INSERT INTO Classification VALUES ('Dishware' , 'Residual');
INSERT INTO Classification VALUES ('Household Supply' , 'Residual');
INSERT INTO Classification VALUES ('Pen' , 'Residual');
INSERT INTO Classification VALUES ('Pencil' , 'Residual');
INSERT INTO Classification VALUES ('Ball Pen' , 'Residual');
INSERT INTO Classification VALUES ('Writing Implement' , 'Residual');
INSERT INTO Classification VALUES ('Brush' , 'Residual');
INSERT INTO Classification VALUES ('Toy' , 'Residual');

--Electronics
INSERT INTO Classification VALUES ('Technology' , 'Electronics');
INSERT INTO Classification VALUES ('Computer' , 'Electronics');
INSERT INTO Classification VALUES ('Electronic Device' , 'Electronics');
INSERT INTO Classification VALUES ('Robot' , 'Electronics');

--Recycle Center
INSERT INTO Classification VALUES ('Wood' , 'Recycle Center');
INSERT INTO Classification VALUES ('Furniture' , 'Recycle Center');
INSERT INTO Classification VALUES ('Outdoor Furniture' , 'Recycle Center');
INSERT INTO Classification VALUES ('Table' , 'Recycle Center');
INSERT INTO Classification VALUES ('Chair' , 'Recycle Center');
INSERT INTO Classification VALUES ('Shelf' , 'Recycle Center');
INSERT INTO Classification VALUES ('Desk' , 'Recycle Center');

--Batteries
INSERT INTO Classification VALUES ('Battery' , 'Batteries');


--Facts table
CREATE TABLE Facts (
fact VARCHAR(100) PRIMARY KEY
);

INSERT INTO Facts VALUES('Did you know recycling is fun');
INSERT INTO Facts VALUES('Recycling cardboard only takes 75% of the energy required to make new cardboard');
INSERT INTO Facts VALUES('One ton of recycled cardboard saves 46 gallons of oil');
INSERT INTO Facts VALUES('Nearly half of the food in the U.S. goes to waste—approximately 3,000 pounds per second');
INSERT INTO Facts VALUES('The U.S. produces approximately 34 million tons of food waste each year');
INSERT INTO Facts VALUES('Recycling plastic takes 88% less energy than making it from raw materials');
INSERT INTO Facts VALUES('Plastic bags can take up to 1,000 years to decompose');
INSERT INTO Facts VALUES('Styrofoam never decomposes');
INSERT INTO Facts VALUES('A modern glass bottle would take 4,000 years or more to decompose');
INSERT INTO Facts VALUES('70% of the total waste in offices is paper waste');
INSERT INTO Facts VALUES('Each ton of recycled paper can save 17 mature trees');
INSERT INTO Facts VALUES('Aluminum can be recycled forever without any loss of quality');
INSERT INTO Facts VALUES('Recycling a single aluminum can saves enough energy to power a TV for 3 hours');
INSERT INTO Facts VALUES('The average person creates almost five pounds of trash per day');
INSERT INTO Facts VALUES('If you recycle one glass bottle, it saves enough energy to light a 100-watt bulb for four hours');
INSERT INTO Facts VALUES('Did you know that during the implementation of this app, "Dog" was added to 
be recycled in compost');

-- psql --host=ec2-54-170-90-26.eu-west-1.compute.amazonaws.com --port=5432 --username=nuqeqfranikqjz --password --dbname=dbre3aieuso0eh
-- heroku pg:psql --app climate-hero < db.sql