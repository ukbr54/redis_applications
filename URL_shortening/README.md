#### URL Shortner (TinyURL)
##### How to generate Tiny URL
- Suppose we have a total of 62 characters, viz
   * a-z = 26
   * A-Z = 26
   * 0-9 = 10
- If my tiny URL is 7 characters long. I can have 62^7 combinations ~= 3.5 trillion
- If we are generating 1000 tiny urls/sec it will take 110 years to exhaust this combination
- Any number from 0 to 3.5 trillion can be represented by 43 bits
##### How to convert 40 bits to tinyURL
- Convert binary 40 bits into decimal. Suppose that number is 1,324,231
- Convert (above) decimal number into base 62. Suppose that number is 60,25,27
- Map above numbers to characters, e.g. (a-z => 0-25) (A-Z => 26-51) (0-9 => 51-61). Above number will map to 8zB

##### Techniques to store Tiny URL
- Technique 1
   * Calculate MD5 of longURL
   * Take first/last 40 bits and use that to generate tinyURL
   * Now check the DB to verify that tinyURL is not used already
   * Advantages of MD5 approach: For two users using same long URL,
      * In random generation, we generate two random tinyURL, so 2 rows in DB
      * In MD5 technique, MD5 of longURL will be same for both the users and hence same first 43 bits of URL will be same and hence
        deduping will save some space as we will only create one row, saving space
