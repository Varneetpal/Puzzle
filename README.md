# Puzzle
You need to find a path through a puzzle room. It’s tiled, nine by thirteen; and you enter at the middle of the east side — one of the short sides. The escape to the next room is just north of the middle of the room, with four pillars blocking certain tiles
near the exit.

![image](https://github.com/Varneetpal/Puzzle/assets/91626610/85d1c01e-9e60-4502-a154-a4169106a6d1)

But, you can only move up, down, left, and right. But according to your log book, stepping off a tile activates a switch and you cannot step there again. You cannot jump over tiles, or move diagonally. Even worse, the trap door won’t unlock unless you step on every tile once.

This program performs search for path search. In the diagram above, you enter at the arrow, and must leave at the white tile. The dark tiles are the pillars. The program emits the path, as a sequence of space-separated moves. The search is substantial – it may take a few hours to run!
