INSERT INTO user_table (id, user_role, email, password) VALUES (1, 'ADMIN', 'admin@admin', 'admin');
INSERT INTO user_table (id, user_role, email, password) VALUES (2, 'DOCTOR', 'doctor@doctor', 'doctor');

INSERT INTO doctor (id) VALUES (2);
INSERT INTO administrator (id) VALUES (1);


INSERT INTO drug_ingredient (DI_ID, INGREDIENT) VALUES (1, 'sastojak1');
INSERT INTO drug_ingredient (DI_ID, INGREDIENT) VALUES (2, 'sastojak2');

INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (1, 'Curenje iz nosa', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (2, 'Bol u grlu', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (3, 'Glavobolja', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (4, 'Kijanje', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (5, 'Kasalj', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (6, 'Drhtavica', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (7, 'Temperatura', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (8, 'Umor', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (9, 'Zuti sekret iz nosa', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (10, 'Gubitak apetita', 0.0);
INSERT INTO symptom (SYMPTOM_ID, DESCRIPTION, DOUBLE_VALUE) VALUES (11, 'Bol koji se siri do usiju', 0.0);

INSERT INTO patient (PATIENT_ID, FIRST_NAME, LAST_NAME) VALUES (1, 'IME1', 'PREZIME1');
INSERT INTO patient (PATIENT_ID, FIRST_NAME, LAST_NAME) VALUES (2, 'IME2', 'PREZIME2');

INSERT INTO drug (DRUG_ID, DRUG_TYPE, NAME) VALUES (1, 'Other', 'LEK1');
INSERT INTO drug (DRUG_ID, DRUG_TYPE, NAME) VALUES (2, 'Analgesic', 'Analgesic1');
INSERT INTO drug (DRUG_ID, DRUG_TYPE, NAME) VALUES (3, 'Antibiotic', 'Antibiotic1');

INSERT INTO drug_ingredients_list (DRUG_ID, DI_ID) VALUES (1, 1);
INSERT INTO drug_ingredients_list (DRUG_ID, DI_ID) VALUES (1, 2);
INSERT INTO drug_ingredients_list (DRUG_ID, DI_ID) VALUES (2, 1);

INSERT INTO disease (DISEASE_ID, NAME) VALUES (1, 'Prehlada');
INSERT INTO disease (DISEASE_ID, NAME) VALUES (2, 'Groznica');
