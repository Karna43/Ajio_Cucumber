Feature: Ajio Mens Shirts

Background: Ajio launching function
Given Go to Ajio website "https://www.ajio.com"
And Navigate to Mens menu and click on Shirts

Scenario: Sorting Mens Shirts by Lowest Price

When User clicks on sort dropdown and select option in a dropdown with one dim map
|sort1|highest|
|sort2|Discount|
|sort3|lowest|
|sort4|What|
|sort5|Ratings|
|sort6|Relevance|
Then Validate the results