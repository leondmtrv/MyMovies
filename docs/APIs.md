# Home Page

## /movies?name="Title" : GET
GET request to retrieve information for a movie

## /movies/trending : GET
GET request to retrieive trending movies

## /movies/review : POST
JSON Format:
{
    ID: "Movie ID"
    Review: int (/5)
}
Posts a users review of a movie to the database

## /movies/watch : POST
JSON Format:
{
    ID: "Movie ID"
}
Posts a movie ID to a user's watch list

# Review Page

## /reviews : GET
GET request to retrieve all the movies a user has reviewed


# Watch List Page

## /watch : GET
GET request to retrieve all the movies on a user's watch list


# Stats Page
