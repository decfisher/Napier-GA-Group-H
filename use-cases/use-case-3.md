USE CASE: 3 Produce a Report on the Salary of Employees in my department
CHARACTERISTIC INFORMATION
Goal in Context
As a department manager I want to produce a report on the salary of employees in my department so that I can support financial reporting for my department.

Scope
Company.

Level
Primary task.

Preconditions
We know the managers department. Database contains current employee salary data.

Success End Condition
A report is available for the department manager to provide to finance.

Failed End Condition
No report is produced.

Primary Actor
Department manager.

Trigger
A request for finance information is sent to a department manager.

MAIN SUCCESS SCENARIO
Finance request salary information for a department.
Department manager captures name of their department to get salary information for.
Department manager extracts current salary information of all employees of their department.
Department manager provides report to finance.
EXTENSIONS
Department does not exist:
Department manager informs finance no department exists.
SUB-VARIATIONS
None.

SCHEDULE
DUE DATE: Release 1.0