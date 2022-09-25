# Nagarro

Change the detault month in application.properties file in the project it has been set as 30 since old records were there in db file

defaultmonth = 30
____________________________________________________________________________________________________
Generate bearer token using following request:

POST URL : localhost:8087/authenticate

For user:
{
	"userName":"user",
	"password":"user"
}

For admin

{
	"userName":"admin",
	"password":"admin"
}

____________________________________________________________________________________________________________________________________

Following url for admin with attributes in GET Method

fromDate

toDate

minAmount

maxAmount

GET URL : localhost:8087/admin/{accoutId}?toDate=08-09-2022&fromDate=08-09-2020&minAmount=120&maxAmount=75

Place Bearer token in the header with key and sample value as following ->

key : Authorization
value : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjY0MTY1MTIxLCJpYXQiOjE2NjQxMjkxMjF9.PfmLzPkyNbJf1q3nzGTaG49w2CwS25f2LjC9StSVdAn5B4swMdj6APiWrTuTlsjWO9peQ9txYs-vPzLHw2qUVw

__________________________________________________________________________________________________________________

Following url for user without search option
GET URL : localhost:8087/user/{accoutId}

Place Bearer token in the header with key and sample value as following -> 
key : Authorization
value : Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjY0MTY1MTIxLCJpYXQiOjE2NjQxMjkxMjF9.PfmLzPkyNbJf1q3nzGTaG49w2CwS25f2LjC9StSVdAn5B4swMdj6APiWrTuTlsjWO9peQ9txYs-vPzLHw2qUVw

_________________________________________________________________________________________________________________



