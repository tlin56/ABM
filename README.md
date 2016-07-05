# ABM

This repository contains the most secret information, please edit or read the files with caution.

The road to hell is paved with good intentions. -- LOL

I am working on converting all the java files into python and adding tons and tons of comments. So please be patient and good will come.

All the codes contain in this repository so far is for Agent based model, either 2D or 3D, 1 type of substrae, 1 type of enzyme, and 1 type of product.
I use periodic boundary condition and there is no obstacles, each round all the particles in the grid are always moving. And each round there is a 
probability for substrate to interact with enzyme to produce compelx and also a probability for complex to either change back to subtrate enzyme or turn into enzyme and product. 

Here are some brief introduction to all the codes I have so far in this repositry:

1. ABM_k.java:

The program generates data for the accumulated amount of complex formation per round for certain rounds (a parameter you can change).

2. ABM_MSD.java:

The program calculates the mean square displacement of a single particle random walk in 3D grid with periodic boundary condition.

3. ABM_test_2D.java:

A 2D Agent-based model, each round the program monitors amount of substrate, enzyme, substrate-enzyme complex, and product in the grid.

4. ABM_test_3D.java:

A 3D Agent-based model, similar as 2D. 
