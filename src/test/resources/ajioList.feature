Feature: Ajio Mens Shirts

Background: Ajio launching function
Given Launch the Ajio website "https://www.ajio.com"
And Navigate to Mens section and click on Shirts

Scenario: Sorting Mens Shirts by Lowest Price

When User clicks on sort dropdown and select option in a dropdown with one dim list
|highest|Discount|lowest|What|Ratings|Relevance|
Then Validate the outcome