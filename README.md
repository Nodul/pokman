# Pokman

a strictly educational project to learn Java+Swing by creating a game 

## TODO


- [ ] keep continous Pokman movement, but make wall collision detection discreet
    - ghosts and pickups keep continous as well, but put pickups at fixed positions/intervals
    while ghosts will behave same as Pokman
- [ ] allow only single Cell wide corridors (this is how the original Pacman was designed)
- [ ] add input buffering, only 1 level deep (original behavior)
- [ ] add collision detection (so that Enemies can detect Pokman AND nobody can run into walls)
	- [X] add basic collision detection for just 1 Cell (currently, it's [1,0])
    - [X] make Pokman stop on entering the forbidden Cell
    - [ ] make Pokman stop entering the forbidden Cell at all (this will fix the phasing into walls bug)
    - [ ] add collision detection for all walls
- [ ] Load SpriteSheet
- [ ] Split and organize SpriteSheet Sprites
- [ ] add Pokman animations (via cycling through imported Sprites)
- [ ] add basic placeholder UI
- [ ] add Enemy entity
- [ ] add varied Enemy behaviour
- [ ] add Pickup entity
- [ ] 5 point pickups + 3 upgrades
- [ ] main menu (new game with map selection, options, exit, credits)
- [ ] add the 4 different maps/sizes
- [ ] polish the game (high score, make resize not break graphics, etc)

## Success:

- [X] init project
- [X] add test walls
- [X] add very basic game loops (with most stuff being a placeholder for now)
- [X] create Map entity
- [X] create a basic debug map 
	- a 12 x 12 map, borders are walls, rest is a floor
- [X] render the map in Swing
- [X] manually load placeholder Pokman Sprite
- [X] add Pokman entity
- [X] add some test movement for Pokman (hardcoded)
- [X] add Pokman controls + actual movement
- 
## Credits

sprites taken from: https://www.spriters-resource.com/arcade/pacman/

