# Design Ideas

## Hospital
* Summary:
    * Represents a Hospital. Includes name, location, hospital statistics and reviews.

* Uses:
    * Symbol Table

* State Variables:
    * Name
    * Location: Address, zip code, state
        * Seperate or in a list?
    * Phone number
    * Reviews:
        * Symbol table with rating ID as key and actual rating as value


## Reviews Symbol Table
* Summary:
    * Uses question ID as a key and reviews as value

* Possible Designs:
    * One ID referring to all answers in a list
        * e.g: key = QUESTION_ID, value=[STAR RATING, PERCENT ALWAYS, PERCENT SOMETIMES, PERCENT NEVER]
    * Each answer has its own seperate ID
        * e.g: key = QUESTION_ID_STAR, value=STAR RATING
        * key = QUESTION_ID_ALWAYS, value=PERCENT ALWAYS
        * key = QUESTION_ID_SOMETIMES, value=PERCENT SOMETIMES
        * key = QUESTION_ID_NEVER, value=PERCENT NEVER
