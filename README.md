<h1>Problem Statement:</h1>
Build an Android app that allows users to view quotes and manage their favourites at FavQs
(https://favqs.com).
Use cases
- As a user, I want to see a random quote when I open the app.
- As a user, I want to be able to browse through a list of public quotes.
- As a user, I want to be able to search through public quotes.
- As a user, I want to see the contents and author of any displayed quote.
- As a user, I want to be able to log in with my existing FavQs account.
- Optional: As a user, I want to be able to tap on any displayed quote to see its details.
- Optional: As a user, I want to see the tags of any displayed quote.
- Optional: As a user, I want to be able to tap on a tag to see a list of matching quotes.
- Optional: As a user, I want to be able to sign up for a FavQs account.

<h1>Approach Selected</h1>
I have opted to follow below things to showcase my work:
I have used **Retrofit** to make the API call for fetching data.
I have used **Dagger** to demonstrate my knowledge on dependency injection though Hilt is simpler one.
I have used ViewBinding for finding views
I have used simple animations option for fragment navigation
I have chosen **MVVM with clean architecture** for this project because it
provides loose coupling of things and testing can also be performed with ease. 

As clean architecture Has mainly 3 layers
1. domain
2. data
3. Presentation

Our code is divided into above mentioned packages we can say. Where each folder has below things
1. **domain -- Models, Usecase & RepoInterface will be placed**
2. **data  -- API Service, Data models and RepoImpl are placed**
3. **ui   -- Activities, Fragments, ViewModels are placed**
4. **di  -- Dagger related stuff like  component and modules are placed**

<h3>Screens</h3>
The app screens implemented are
1. Random Quote 
2. Quotes List
3. Quote Details 
4. Login 
5. Signup 

<h3>Flow</h3>
On clicking the app icon it will open an Random Quote page where user can see random quote each time the app is opened. We fetch the quotes from API of FavQ's https://favqs.com/api
There are 3 possible navigation from Random quote page
1. User can browse list of quotes by clicking on Browse quotes button at bottom
2. If User clicks on account button on top right, user either can navigate to Login/Signup flow or perform logout action
3. When User taps on a quote the redirection will take him to quote details page

Coming to Quotes list screen we have search option by which user can search the quotes, pagination for loading more quotes and Quotes actions.
We have QuoteActions enum class which defines the actions we can perform on each quote
a. User clicks on a quote where the redirection will take him to quote details page
b. User clicks on a Share button it opens a share builder where user can share them
c. User clicks on a Copy button the quotes gets copied to clipboard

Quotes details screen displays an overview of quote in a text view.

Login/Signup flow where user can login with his existing account or register as a new user. We have few validations at app level for registration. If there are error in login/signup
we show them using a dialog to the user.

On Quote related screens we make an API call with internet check, if no internet then error screen with retry option will be displayed if there was no data to show. 
The app is completely online no database used for caching.

<h3>Tests</h3>
Coming to tests part I have implemented  Unit tests with Junit and Mockito, Also written integration or automation tests with
espresso. There are also few other testing dependencies which are added for different purposes.
I have covered to some extent of testing cases with in time frame including unit and integration on View Layer, ViewModel layer,
Repo Layer, Service Layer, etc. Due to time constraint and environment selected I have missed cases.

<h3>What could have been Better</h3>
Provided more time I could have better handled 
1. Improvising the User experience with better UX
2. Implementation of favourites functionality, tags based quotes list functionality
3. Better pagination management on Quotes list page
4. Better error handling based on error codes with localised error messages to support different languages
5. More tests at unit and UI level to ensure quality
6. Better code management with base classes(Though used it to some extent)



