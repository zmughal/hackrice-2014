CREATE TABLE incident_report (
	_id INTEGER PRIMARY KEY,
	full_name TEXT NOT NULL,
	address_1 TEXT NOT NULL,
	address_2 TEXT,
	city TEXT NOT NULL,
	state TEXT NOT NULL,
	zipcode TEXT NOT NULL,
	phone TEXT NOT NULL,
	email TEXT,
	vehicle_make TEXT NOT NULL,
	vehicle_model TEXT NOT NULL,
	vehicle_year TEXT NOT NULL,
	license_plate TEXT NOT NULL,
	insurance_carrier TEXT NOT NULL,
	policy_number TEXT NOT NULL,
	insurance_phone_number TEXT NOT NULL,
	date_created TIMESTAMP
);