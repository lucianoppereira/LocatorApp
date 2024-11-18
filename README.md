---LOCATOR APP CHALLENGE---

For this challenge was requested a list of cities on a screen with navigation to a map where the selected location would be zoomed in, and the details of the location could be navigated to another screen.
In my solution, I propose, instead of a direct list screen, to use a search engine at the top of the screen, above the already visible map. This SearchField expands and let see the list of cities displayed there.

Also, since the information for each city was not enough to justify a new section, I decided to create a pop-up window that appears when a city is selected, minimizing the list in the process.

The architecture implemented is MVVM, and for the search, a request is made to the local database (managed with Room) that in case of not finding results, will make the request to retrieve the information from the gist provided in the challenge (Retrofit), and with that information populate the database.

I decided to do it in this order because to save locations in favorites, there would be less danger of overwriting the database boolean that sets the location as a favorite in each request to the remote.

When retrieving the information, wherever it comes from, a Flow is issued handling the SUCCESS, ERROR and LOADING cases to update the UI.

The possibility of rotating the screen or using a folding phone with a second alternative wide layout is also considered. Both layouts consume from the same viewmodel, which has the necessary states to keep the screen always updated.
