# About this project 

This is a Sample Application created as an exercise of Uber Interview.

The main goal was to search for a term on flickr and display a list of images with a clean architecture code.

To achieve that, the app has different modules for each resposability: Ui, Domain and Network and use interfaces to handle boundaries and create tests. 

# What is missing?

* To escalate, the project could have some more layers (E.g. repository, analytics and cache). 
* In this version the View is the Activity. I think I could move the view logic to a fragment so we can use it in other screens.
* Ui tests
* Network and Http error handlers. I could use Rx transformers and change the network call response to `Single<Response<T>>`.

# Libraries used in this project

* Retrofit
* RxJava
* Dagger
* Glide
* Gson
* Mockito for tests

