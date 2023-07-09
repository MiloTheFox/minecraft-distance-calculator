# Minecraft Distance Calculator

## Overview
This repository contains a Minecraft distance calculator that allows the user to calculate the distance between two points in the game.

## Installation
To install the Minecraft distance calculator, follow these steps:

1. Clone this repository to your local machine (Install [Git](https://git-scm.com/downloads) and choose your Operating System if it's not on your System already):
```shell
git clone https://github.com/Sweet-Tooth11/minecraft-distance-calculator.git
```

2. Install and include the following library into the project:
- [JavaFX](https://gluonhq.com/products/javafx/): A library made for GUI Creation
- After that is done, extract the library and put the files into a folder called "libs" (or whatever you prefer to call it)
- The result should look like this:

[![Result](https://i.postimg.cc/0jpNf9kk/Screenshot-2023-07-09-131500.png)](https://postimg.cc/GTmr3wTV)

3. Navigate to the repository directory and run `./compile.bat` to compile the code.

4. After that, run `./execute.bat` to start the program.

5. Put in Minecraft coordinates of your choice and choose the Calculation Method you want to use. [Here is an explanation of both Calculation Methods](https://github.com/Sweet-Tooth11/minecraft-distance-calculator/tree/main/README.md#explanation-of-the-different-calculation-methods)

## Disclaimer

This code is intended for users who have a solid understanding of Java. If you are new to Java or need to refresh your skills, please visit [this](https://www.w3schools.com/java/java_intro.asp) Website for a comprehensive tutorial. I may not be able to answer basic questions about Java syntax or concepts. Thank you for your understanding and cooperation.🙏

## Usage

To use the Minecraft distance calculator, enter the coordinates of two points in the game when prompted. The program will then calculate and display the distance between those points.

If you don't know how to get your coordinates, follow these steps:
1. Run any Minecraft Version from Alpha 1.2.3 or higher.
2. Press F3, and you will be met with a screen like this (Positioning may vary depending on the used Minecraft Version):

[![Example](https://i.postimg.cc/gcf8zpxF/2023-06-03-04-27-22.png)](https://postimg.cc/w3Q72S50)

3. Note these coordinates down (either through Notepad, noting these down using the Chat or directly copying them into the clipboard using Debugging Controls [F3 + C]).

## Examples
Euclidean:

[![Euclidean](https://i.postimg.cc/JhpKrBkX/Screenshot-2023-07-09-133720.png)](https://postimg.cc/wtsDFMFx)

Manhattan:

[![Screenshot-2023-07-09-133735.png](https://i.postimg.cc/RFqdNV7D/Screenshot-2023-07-09-133735.png)](https://postimg.cc/0MRD4vHY)

## Explanation of the different Calculation Methods

# **Euclidean:**

Do you remember learning about distance in geometry class? You probably learned that distance is the length of the line that connects two points. That is the idea behind the Euclidean distance method, which is named after Euclid, the famous Greek mathematician who studied geometry.

The Euclidean distance method is a way to measure how far apart two points are in any space that has more than one dimension. For example, you can use it to measure the distance between two points on a map, or two stars in the sky, or two pixels on a screen. It helps you compare how similar or different two points or objects are based on where they are located.

To find the Euclidean distance between two points, you need to know their coordinates. The coordinates tell you how far the points are from a fixed point called the origin, and from each other along different directions called axes. For example, in a two-dimensional space, you have an x-axis and a y-axis, and each point has an x-coordinate and a y-coordinate.

The formula for the Euclidean distance between two points (x1, y1) and (x2, y2) in a two-dimensional space is:

$$Euclidean Distance = \sqrt{((x1 - x2)^2 + (y1 - y2)^2)}$$

This formula tells you to subtract the coordinates of the points, square them, add them up, and take the square root. This gives you the length of the line that connects the points.

For example, suppose you have two points A(2, 3) and B(5, 7). To find the Euclidean distance between them, you plug in their coordinates into the formula:

$$\sqrt{((2 - 5)^2 + (3 - 7)^2) = √((-3)^2 + (-4)^2) = √(9 + 16) = √25 = 5}$$

So, the Euclidean distance between points A and B is 5.

The Euclidean distance method works not only in two dimensions, but also in higher dimensions. For example, if you have three coordinates for each point, such as (x1, y1, z1) and (x2, y2, z2), you can use the same formula but add one more term:

$$\sqrt{((x1 - x2)^2 + (y1 - y2)^2 + (z1 - z2)^2)}$$

This means that you also consider how far the points are along a third axis called the z-axis.

The Euclidean distance method is very popular because it makes sense geometrically and has some nice features. For instance, it is always positive, it is the same no matter which way you go, and it never underestimates the distance. These features make it a good way to measure how close or far away two points or objects are in math and computer science.


# **Manhattan:**

Imagine you are in Manhattan, where the streets are arranged like a grid. To get from one place to another, you can only walk along the streets, not diagonally or across blocks. The Manhattan distance method tells you how far you have to walk by adding up the number of blocks you have to go east or west, and the number of blocks you have to go north or south.

For example, suppose you are at point A(3, 5) and you want to go to point B(7, 9). You can’t just draw a straight line between them and measure it. You have to walk along the streets. One way to do that is to go 4 blocks east and then 4 blocks north. The total number of blocks you have to walk is 4 + 4 = 8. That is the Manhattan distance between points A and B.

You can also write this as a formula:

$$|x1 - x2| + |y1 - y2|$$

Here, x1 and x2 are the east-west coordinates of the points, and y1 and y2 are the north-south coordinates of the points. The vertical bars mean that you take the absolute value, which means that you ignore the sign and just look at how big the number is. This way, you always get a positive distance, no matter which direction you go.

The Manhattan distance method works not only in two dimensions, but also in higher dimensions. For example, if you have three coordinates for each point, such as (x1, y1, z1) and (x2, y2, z2), you can use the same formula but add one more term:

$$|x1 - x2| + |y1 - y2| + |z1 - z2|$$

This means that you also consider how far you have to go up or down in addition to east-west and north-south.

The Manhattan distance method has some nice features that make it useful for many purposes. For instance, it is always positive, it is the same no matter which way you go, and it never overestimates the distance.

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
