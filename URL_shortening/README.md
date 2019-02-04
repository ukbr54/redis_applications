#### URL Shortner (TinyURL)
How to generate Tiny URL
Suppose we have a total of 62 characters, viz
a-z = 26
A-Z = 26
0-9 = 10
If my tiny URL is 7 characters long. I can have 62^7 combinations ~= 3.5 trillion
If we are generating 1000 tiny urls/sec it will take 110 years to exhaust this combination
Any number from 0 to 3.5 trillion can be represented by 43 bits
How to convert 43 bits to tinyURL
Convert binary 43 bits into decimal. Suppose that number is 1,324,231
Convert (above) decimal number into base 62. Suppose that number is 60,25,27
Map above numbers to characters, e.g. (a-z => 0-25) (A-Z => 26-51) (0-9 => 51-61). Above number will map to 8zB
