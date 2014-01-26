INSERT INTO incident_report (
	full_name,
	address_1,
	address_2,
	city,
	state,
	zipcode,
	phone,
	email,
	vehicle_make,
	vehicle_model,
	vehicle_year,
	license_plate,
	insurance_carrier,
	policy_number,
	insurance_phone_number,
	date_created
) VALUES (
		?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, datetime('now')
	);