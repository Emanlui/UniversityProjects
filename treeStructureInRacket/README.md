# Tree integration on racket

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Racket is a mainly "Functional" programming languaje. It can also be used with object oriented languaje with classes and heredity. In this paradigm the main focus is the recursion, everything has to be made with recursion (excluding some code) thats why the tree structure in racket is an awesome way to practice recursion.


# How to run it

  - First of all you need to download doctor racket from the main page.
  - The you open the file and you can do all the functions in the program. Some examples are shown down below.


| Function | Sintax | Explanation
| ------ | ------ | ------ |
| Node | The node has 4 parameters, the id, the father id, the data and also the tree variable | X 
| Insert | (insert-node 1 0 “datos del nodo” arbol) | The first time you have to set the father to 0
| Delete | (delete-node 1 arbol) | Delete a sub tree or all the tree if the index is 0
| List all the nodes | (list-all-nodes arbol) | Prints all the tree
| List some nodes depending on the father | (listsome-nodes 1 arbol) | It should return the nodes that has 1 as ancestor
| Find ancestor | (ancestor 4 arbol) |It return the father of a node |
| Find right sibling | (find-right-sibling 2 arbol) | It should return the right node
| Find left sibling | (find-left-sibling 2 arbol) | It should return the right node

