USE CASE: 4 Produce a Report on the top 10 populated cities in the world, a continent, a region, a country and a district.
CHARACTERISTIC INFORMATION
Goal in Context
As an employee of the organisation I want to produce reports on the top N populated cities by world, continent, region, country and district. Where N is provided by the user, to give to the World Health Organisation.

Scope
Company.

Level
Primary task.

Preconditions
Database containing details on the population of all cities.

Success End Condition
A report is available for the employee to use, which contains the top 10 most populated cities in the world, a continent, a region, a country and a district.

Failed End Condition
No report is produced. The report is incorrect.

Primary Actor
Employee

Trigger
A request for the top 10 populated cities in the world, continent, region, country and district by WHO.

MAIN SUCCESS SCENARIO
WHO request top 10 city population information for the world, continent, region, country and district for their worldwide report.
Employee captures specified conditions, including the number of records requested and the required continent, region, country and district.
Employee runs specific query to satisfy WHOs request with restrictions on location where applicable.
Employee provides report to WHO.
EXTENSIONS
None.
SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0