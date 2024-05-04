## Explanation

First I created the `Problem` class taht will store one problem, its `hash` - a String value supplied by the BugBan program; and the `data` - a HashSet of Strings that explains the program.

The main program works by first asking for input for all file paths - 2 in and 3 out. Then i create two lists of problems from the 2 inputs using my function `decodeJSON()` and a library called `json.simple`  
Then i compare the hash values of each problems from the tho lists and find witch match and withc dont. If they do match i need to check all `data` of a problem to make sure they are the same.
The problems are then simply added to new lists depending on if they occur in only the first, only the second or both input files. The function `encodeJSON()` makes the JSON string and creats an output file.
