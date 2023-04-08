# Kraken-Flex-springboot
Kraken Flex spring boot 

1. run Application.java file
2. we can get the response through swagger url http://localhost:8085/swagger-ui/index.html
3. attaching the postman collection as well for testingthe api
  . http://localhost:8085/outages :- Retrieves all outages from the `GET /outages` endpoint
  . http://localhost:8085/outages?filter=date :- Filters out any outages that began before `2022-01-01T00:00:00.000Z` 
  . http://localhost:8085/outages?filter=noid :- don't have an ID that is in the list of
   devices in the site information
  . http://localhost:8085/outages?filter=remainingoutages :- For the remaining outages, it should attach the display name of the device in the site information to each appropriate outage
  . http://localhost:8085/site-info/norwich-pear-tree :- Retrieves information from the `GET /site-info/{siteId}` endpoint for the site with the ID `norwich-pear-tree`
  . http://localhost:8085/site-outages/norwich-pear-tree :- Sends this list of outages to `POST /site-outages/{siteId}` for the site with the ID `norwich-pear-tree`
  
4. for running test cases run as test file a junit test cases
