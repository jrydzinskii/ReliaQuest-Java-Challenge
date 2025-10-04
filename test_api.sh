#!/bin/bash
# Employee API Test Suite using curl
# Make sure your application is running: ./gradlew bootRun

BASE_URL="http://localhost:8080/api/v1/employee"

echo "=== Test 1: GET all employees (empty list) ==="
curl -X GET "$BASE_URL" \
  -H "Accept: application/json" | jq '.'
echo -e "\n"

echo "=== Test 2: POST - Create first employee ==="
RESPONSE=$(curl -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "salary": 75000,
    "age": 30,
    "jobTitle": "Software Engineer",
    "email": "john.doe@example.com",
    "contractHireDate": "2024-01-01T00:00:00Z",
    "contractTerminationDate": null
  }')
echo "$RESPONSE" | jq '.'
UUID1=$(echo "$RESPONSE" | jq -r '.uuid')
echo "Created employee with UUID: $UUID1"
echo -e "\n"

echo "=== Test 3: POST - Create second employee ==="
RESPONSE=$(curl -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "firstName": "Jane",
    "lastName": "Smith",
    "salary": 85000,
    "age": 28,
    "jobTitle": "Senior Developer",
    "email": "jane.smith@example.com",
    "contractHireDate": "2023-06-15T00:00:00Z",
    "contractTerminationDate": null
  }')
echo "$RESPONSE" | jq '.'
UUID2=$(echo "$RESPONSE" | jq -r '.uuid')
echo "Created employee with UUID: $UUID2"
echo -e "\n"

echo "=== Test 4: POST - Create employee with termination date ==="
curl -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "firstName": "Charlie",
    "lastName": "Brown",
    "salary": 70000,
    "age": 45,
    "jobTitle": "Consultant",
    "email": "charlie.brown@example.com",
    "contractHireDate": "2020-01-01T00:00:00Z",
    "contractTerminationDate": "2023-12-31T00:00:00Z"
  }' | jq '.'
echo -e "\n"

echo "=== Test 5: GET all employees (should have 3) ==="
curl -X GET "$BASE_URL" \
  -H "Accept: application/json" | jq '.'
echo -e "\n"

echo "=== Test 6: GET employee by UUID (first employee) ==="
curl -X GET "$BASE_URL/$UUID1" \
  -H "Accept: application/json" | jq '.'
echo -e "\n"

echo "=== Test 7: GET employee by UUID (second employee) ==="
curl -X GET "$BASE_URL/$UUID2" \
  -H "Accept: application/json" | jq '.'
echo -e "\n"

echo "=== Test 8: GET employee by non-existent UUID ==="
FAKE_UUID="00000000-0000-0000-0000-000000000000"
curl -X GET "$BASE_URL/$FAKE_UUID" \
  -H "Accept: application/json" | jq '.'
echo -e "\n"

echo "=== Test 9: POST - Create employee without optional fields ==="
curl -X POST "$BASE_URL" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d '{
    "firstName": "Bob",
    "lastName": "Johnson",
    "salary": 60000,
    "age": 35,
    "jobTitle": "Developer",
    "email": "bob@example.com",
    "contractHireDate": "2024-10-01T00:00:00Z"
  }' | jq '.'
echo -e "\n"

echo "=== All tests complete ==="