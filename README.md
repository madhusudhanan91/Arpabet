On my honor, I pledge that I have not violated the provisions of the NJIT Academic Honor Code. All work that I have submitted for this assignment is my own, unless clearly indicated otherwise with a citation to the source.

(1) Instructions:

- The files DictFile.txt (containing the CMU Dictionary words) and Input File (*.txt) should be placed in the same folder in the AFS directory.
- The name of the Input file should be specified during runtime to read the arpabets/characters from the file. 

(2) Description of Approach:

- The DictFile.txt contains the CMU dictionary which are mapped to LinkedHashMap (to maintain the insertion order of the elements) where the values contains the Arpabets Hash keys contains the corresponding alphabets.
- The Input file is provided by the user and values are stored in ArrayList and wordBreak() function is called and the corresponding keyset of the arphabetValue variables are passed.
- Position table (POS) is a table that is helpful to track the array structure
- This POS table is created as list using nested for loops iterating over i and j till the total length of arpabets (32)
- These contents containing the english words are stored in the position table which are subsequently added to an existing array list.
- If the position table array containing the arpabet count is null, then new arraylist is mapped to the list.
- Otherwise, dfs method is called and result is returned.
- Depth First Search can be used to obtain the character mappings to form strings and they are compared with the adjacent characters to form meaningful dictionary from CMU dictionary.
- This program extracts all the possible translations for specified input file but we print out one such possibility to avoid any OutOfMemory exceptions.

(3) Data Structures/Algorithms Used:

- LinkedHashMap - Values and keys are mapped based on the input and iteration order is maintained by Insertion order.
- ArrayList - Dynamic arrays for holding the elements from the input file.
- Dynamic Programming String Reconstruction and Word Break algorithm - Given a text file and a dictionary of words, we identify if subsequent characters form a string and are a part of the dictionary (Fundamental Techniques).

(4) Running time of Algorithm

- Time Efficiency: O(N*M) where N - Arpabet symbols and M - Entries in the Dictionary
- Space Efficiency: Memory: O(N)

(5) References:

- The lines 59 to 138 are inspired from the link http://www.programcreek.com/2014/03/leetcode-word-break-ii-java/ and significantly modified & customized to match the programming assignment requirements.
