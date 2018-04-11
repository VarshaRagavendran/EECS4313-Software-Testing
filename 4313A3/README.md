# Assignment 3 - Data Flow Testing, Slice-Based Testing and Mutation Testing

The purpose of this assignment is to apply data-flow, slice-based, and mutation testing techniques on the BORG calendar maven project (
https://github.com/mikeberger/borg_calendar/). Additionally, perform load test on the performance of an e-commerce website, JPetStore using JMeter.

## Task 1:
1. Perform data flow analysis for one of the methods that you have tested. Calculate the value of the following coverage criteria:
** All-Defs
** All-Uses
** All-P-Uses / Some-C-Uses
** All-C-Uses / Some-P-Uses
If necessary, add test cases to bring these values as close to 100% as possible.
2. Derive the slices for all A-defs (defined by assignment) and P-uses (used in a decision predicate) in the method. Calculate the percentage of slices that your testing covers. If necessary, add test cases to bring the percentage as close to 100% as possible.
3. Install the PIT mutation testing tool. Evaluate the effectiveness of all your test cases (test cases from A2 and newly created test cases for this assignment) using PIT. Add or modify test cases, accordingly.

## Task 2:
1. Deploy the JPetStore application by dropping the war files at Tomcat’s “webapps\ROOT” folder and double check if you can access the JPetStore website (http://localhost/jpetstore/shop/index.shtml).
2. Explore the system a bit and define some realistic, non-trivial test scenarios.
3. Leverage the record-and-replay features to encode the test scenarios in JMeter.
4. Load test the system for a period of time (5 – 30 minutes).
5. During the test execution, make sure you also record the performance counters and the logs.
6. Analyze the results of the load test to see if there is anything abnormal:
* Did the system crash/restart/hang during the test?
* Did all the requests go through?
* Is there unexpected resource usage during the test?