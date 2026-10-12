#!/usr/bin/env bash
# Three test cases. Start the app first:  mvn spring-boot:run
URL=http://localhost:8080/outpass
H='Content-Type: application/json'

echo "1) Auto-approved (day out, nights = 0)"
curl -s -X POST $URL -H "$H" -d '{"studentName":"Priya","regNo":"RA2311003010001","hostelBlock":"Block A","outDate":"2026-10-20","returnDate":"2026-10-20","parentPhone":"9876543210","purpose":"Hospital visit"}'
echo -e "\n"

echo "2) Needs warden approval (2 nights)"
curl -s -X POST $URL -H "$H" -d '{"studentName":"Arun","regNo":"RA2311003010002","hostelBlock":"Block B","outDate":"2026-10-20","returnDate":"2026-10-22","parentPhone":"9876500000","purpose":"Family function"}'
echo -e "\n"

echo "3) Rejected (return date before out date)"
curl -s -X POST $URL -H "$H" -d '{"studentName":"Meena","regNo":"RA2311003010003","hostelBlock":"Block C","outDate":"2026-10-22","returnDate":"2026-10-20","parentPhone":"9876511111","purpose":"Home visit"}'
echo
