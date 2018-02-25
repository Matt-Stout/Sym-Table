# Sym-Table
Create several classes to represent a symbol table object

This was a program assignment for CS 536: Intro to Languages and Compilers, Spring 2018

The SymTable class will be used by the compiler to be written later in the semester to represent a symbol table: a data structure that stores the identifiers declared in the program being compiled (e.g., function and variable names) and information about each identifier (e.g., its type, where it will be stored at runtime). The symbol table will be implemented as a List of HashMaps. Eventually, each HashMap will store the identifiers declared in one scope in the program being compiled.
