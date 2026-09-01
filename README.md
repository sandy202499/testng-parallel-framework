# TestNG Parallel Selenium Practice Framework

## Scenario
Two SauceDemo login scenarios run at the same time:
1. Valid user login
2. Locked-out user login

Each test gets a separate browser through ThreadLocal<WebDriver>.

## Prerequisites
- Java 17+
- Eclipse
- Maven
- Chrome installed

Selenium Manager automatically resolves the browser driver in modern Selenium.

## Import into Eclipse
1. File -> Import
2. Maven -> Existing Maven Projects
3. Select this project folder
4. Finish
5. Right-click project -> Maven -> Update Project

## Run
Option A:
- Right-click testng.xml -> Run As -> TestNG Suite

Option B:
- Right-click project -> Run As -> Maven test

## Expected behavior
With `parallel="methods"` and `thread-count="2"`:
- Two browser windows open
- validLoginTest runs in one thread
- lockedOutUserTest runs in another thread
- each thread owns a different WebDriver
- both browsers close after their tests finish

## Why ThreadLocal?
A plain static WebDriver would be shared by all threads and tests could overwrite each other's browser reference.
ThreadLocal stores one WebDriver per thread.

## Try changing thread-count
In testng.xml:
thread-count="1" -> sequential
thread-count="2" -> two test methods can run simultaneously
Jenkins Poll SCM Test 
GitHub Webhook Test 
