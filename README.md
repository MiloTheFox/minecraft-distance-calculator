# Minecraft Distance Calculator

## Overview
This repository contains a Minecraft distance calculator that allows players to calculate the distance between two points in the game.

## Installation
To install the Minecraft distance calculator, follow these steps:
1. Clone this repository to your local machine (Install [Git](https://git-scm.com/downloads) and choose your Operating System if you haven't already):
```shell
git clone https://github.com/Sweet-Tooth11/minecraft-distance-calculator.git
```
2. Install and include the following library into the project:
- [Apache Commons Math](https://commons.apache.org/proper/commons-math/download_math.cgi): A library providing mathematical and statistical components.
3. Navigate to the repository directory and run `javac -cp "./libraries/commons-math4-core-4.0-beta1.jar;./libraries/commons-math4-legacy-4.0-beta1.jar;./libraries/commons-math4-legacy-exception-4.0-beta1.jar" main.java` to compile the code.
4. Run `java -cp "./libraries/commons-math4-core-4.0-beta1.jar;./libraries/commons-math4-legacy-4.0-beta1.jar;./libraries/commons-math4-legacy-exception-4.0-beta1.jar" main.java` to start the program.
5. Follow the instructions and get the distance between the coordinates of provided points.

## Disclaimer

This code assumes that you have a solid understanding of Java. I will not help you with the most basic stuff that you don't understand. If you want to learn more about Java, please head to [this](https://docs.oracle.com/javase/tutorial/) Website.

## Usage

To use the Minecraft distance calculator, enter the coordinates of two points in the game when prompted. The program will then calculate and display the distance between those points.

If you don't know how to get your coordinates, follow these steps:
1. Run any Minecraft Version from Alpha 1.2.3 or higher.
2. Press F3, and you will be met with a screen like this (Positioning may vary depending on the specific Minecraft Version):

[![Example](https://i.postimg.cc/gcf8zpxF/2023-06-03-04-27-22.png)](https://postimg.cc/w3Q72S50)

3. Note these coordinates down (either through Notepad, noting these down using the Chat or directly copying them into the clipboard using Debugging Controls [F3 + B]).

## Examples
Euclidean:
[![Screenshot-2023-06-03-041524.png](https://i.postimg.cc/HW3CwWCX/Screenshot-2023-06-03-041524.png)](https://i.postimg.cc/HW3CwWCX/)

Manhattan:
[![Screenshot-2023-06-03-041504.png](https://i.postimg.cc/J4nCPw1g/Screenshot-2023-06-03-041504.png)](https://i.postimg.cc/J4nCPw1g/)

## Explanation of the different Calculation Methods

**Euclidean:**

The Euclidean distance method uses the Pythagorean theorem, which gives the shortest or minimum distance between two points. The Euclidean distance method is also called the L2 norm or L2 metric, while the Manhattan distance method is also called the L1 norm or L1 metric. For example, if you have two points A and B in a two-dimensional space (a plane), and their coordinates are (x1, y1, z1) and (x2, y2, z2), then the Euclidean distance between them is given by:

$$d = \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2 + (z_2 - z_1)^2}$$

**Manhattan:**

The Manhattan distance method uses the sum of the absolute differences between the coordinates of the points in each dimension, which gives the distance a car or a taxi would have to drive in a city with a grid layout.

Here's an example of the aforementioned two-dimensional space and the coordinates being (x1, y1, z1) and (x2, y2, z2):
$$d = |x_2 - x_1| + |y_2 - y_1| + | z_2 - z_1 |$$

## Issues

If you experience any Issues, feel free to open an Issue in the [Issues](https://github.com/Sweet-Tooth11/minecraft-distance-calculator/issues) section explaining:
1. What went wrong in the first place
2. What you were doing before having the Issue
3. What Error Message you've experienced

## Contributions

If you want to contribute to this project, then thank you, first of all! I welcome contributions to this project! If you would like to contribute, please follow these steps:

1. Fork this repository to your own GitHub account.
2. Create a new branch for your changes.
3. Make your changes and commit them to your branch.
4. Submit a pull request to the main branch of this repository.

**Please make sure to follow my coding standards and include tests for any new features or bug fixes.**
