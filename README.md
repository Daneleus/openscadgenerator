# openscadgenerator

A Tool for generating .scad Code with Java

## Features:

* Shapes
  * Cone
  * Cube
  * Cuboid
  * Cylinder
  * Polyhedron
  * Prism

* Operations
  * Difference
  * Intersection
  * MoveToPosition
  * Rotation
  * Union

## How to use

* create a shape using any class of the "shape"-package
* use ScadUtil for common scad functions rotate, union, etc.
* use FileUtil.writeScadStringToFile to get a .scad file to your filestorage