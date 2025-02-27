
# UI TEST PROBLEM 1
navigate to the path of the project C:\Users\xxx\xxxx\xx\xx in CMD
run mvn compile 
You should get a build success
run all test = run mvn test 
run UI test only = mvn test -Dtest=UiTestRunner
run API test only = mvn test -Dtest=ApiTestRunner
