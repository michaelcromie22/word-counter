# word-counter
Application to return a distinct list of words in a given sentence and the number of times they have occurred.

## Prerequisites
* Java 8
* Maven 3+

## Usage Guide:
* Open a terminal and navigate to the root folder   
`cd <your-path>/word-counter`
* Create an executable jar   
`mvn clean package`
* Excecute the jar   
`java -jar target/wordcounter-0.0.1-SNAPSHOT.jar`

## Assumptions:
* Sentences must start and end with inverted commas when provided as input e.g. "This is a statement, and so is this."
* Words are treated as the same regardless of case e.g. "This" is the same as "this"
* Numbers are ignored, as are all punctuation marks and symbols excluding apostrophes (e.g. can't) and hyphens (e.g. "ice-cream")
* No dictionary, spell check or grammar check is used i.e. all words containing 1 or more Aa-Zz characters are considered valid e.g. "thisthis" would be considered as one valid word rather than 2x "this"
* Words containing apostrophes are treated as discrete words e.g. "can't", "can", "not" and "cannot" are all distinct
* Sentences containing quotations are accepted e.g. "'This is a quotation!' said Michael." is considered valid
* Only Aa-Zz characters will be considered for sentences containing words with a mixture of punctuation, numbers and letters e.g. "123hello456" will output "hello — 1"