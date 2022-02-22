USE CASE: 2 Produce a Report on the top 10 populated capital cities in the world, a continent and region
CHARACTERISTIC INFORMATION
Goal in Context
As an employee of the organisation I want to produce reports on the top N populated capital cities, sorted by world, continent and region, where N is provided by the user, to give to the World Health Organisation.

Scope
Company.

Level
Primary task.

Preconditions
Database containing details on populations of capital cities.

Success End Condition
A report is available for the employee to use, which contains population information for each capital city and the corresponding continent and region.

Failed End Condition
No report is produced. The report is incorrect.

Primary Actor
Dave the Employee

Trigger
A request for capital city population information from WHO

MAIN SUCCESS SCENARIO
WHO request top 10 capital city population information for the world, a continent and a region for their worldwide report.
Employee captures specified conditions, including the top 10 records and the required continent and region.
Employee runs specific query to satisfy WHOs request with restrictions on continent and region where applicable.
Employee provides report to WHO.
EXTENSIONS
None.
SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0