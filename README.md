# WeatherApp

## Requirements:
The `REST` service must expose the `/weather` endpoint, which allows for managing the collection of weather records in the following way:


POST request to `/weather`:

- creates a new weather data record
- expects a valid weather data object as its body payload, except that it does not have an id property; you can assume that the given object is always valid
- adds the given object to the collection and assigns a unique integer id to it
- the response code is 201 and the response body is the created record, including its unique id


GET request to `/weather`:

- the response code is 200
- the response body is an array of matching records, ordered by their ids in increasing order
- accepts an optional query string parameter, date, in the format YYYY-MM-DD, for example /weather/?date=2019-06-11. When this parameter is present, only the records with the matching date are returned.
- accepts an optional query string parameter, city, and when this parameter is present, only the records with the matching city are returned. Moreover, it might contain several values, separated by commas (e.g. city=london,Moscow), meaning that records with the city matching any of these values must be returned.

GET request to `/weather/<id>`:

- returns a record with the given id
- if the matching record exists, the response code is 200 and the response body is the matching object
- if there is no record in the collection with the given id, the response code is 404


## Data:
Example of a weather data JSON object:
```
{
   "id": 1,
   "date": "1985-01-01",
   "lat": 36.1189,
   "lon": -86.6892,
   "city": "Nashville",
   "state": "Tennessee",
   "temperatures": [17.3, 16.8, 16.4, 16.0, 15.6, 15.3, 15.0, 14.9, 15.8, 18.0, 20.2, 22.3, 23.8, 24.9, 25.5, 25.7, 24.9, 23.0, 21.7, 20.8, 29.9, 29.2, 28.6, 28.1]
}
```

Connecting to local postgres db using username and password from application.properties.
Enabled the ability to to create the schema, destroying previous data

Start:
mvn clean compile
