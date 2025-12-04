# Arkanoid — Java OOP Arkanoid Game

Small Arkanoid-style game implemented as an OOP assignment in Java.

## Quick start

- Build: ant compile (uses [build.xml](build.xml))  
- Run: ant run (runs `Ass5Game`)  
  - Entry point: [`Ass5Game.main`](src/Ass5Game.java)

## Project layout

Files:
- [build.xml](build.xml)
- [README.md](README.md)
- [src/Ass5Game.java](src/Ass5Game.java)

Core packages:
- arkanoid
  - [`arkanoid.Game`](src/arkanoid/Game.java) — initialization and main loop (see [`arkanoid.Game.initialize`](src/arkanoid/Game.java), [`arkanoid.Game.run`](src/arkanoid/Game.java))
  - [`arkanoid.GameEnvironment`](src/arkanoid/GameEnvironment.java) — collision detection (see [`arkanoid.GameEnvironment.getClosestCollision`](src/arkanoid/GameEnvironment.java))
  - [`arkanoid.Sprite`](src/arkanoid/Sprite.java)
  - [`arkanoid.SpriteCollection`](src/arkanoid/SpriteCollection.java)
- geometry
  - [`geometry.Ball`](src/geometry/Ball.java)
  - [`geometry.Block`](src/geometry/Block.java)
  - [`geometry.Paddle`](src/geometry/Paddle.java)
  - [`geometry.Point`](src/geometry/Point.java)
  - [`geometry.Line`](src/geometry/Line.java)
  - [`geometry.Rectangle`](src/geometry/Rectangle.java)
  - [`geometry.Velocity`](src/geometry/Velocity.java)
  - [`geometry.Collidable`](src/geometry/Collidable.java)
  - [`geometry.CollisionInfo`](src/geometry/CollisionInfo.java)
- listener
  - [`listener.Counter`](src/listener/Counter.java)
  - [`listener.HitListener`](src/listener/HitListener.java)
  - [`listener.HitNotifier`](src/listener/HitNotifier.java)
  - [`listener.BallRemover`](src/listener/BallRemover.java)
  - [`listener.BlockRemover`](src/listener/BlockRemover.java)
  - [`listener.ScoreTrackingListener`](src/listener/ScoreTrackingListener.java)
  - [`listener.ScoreIndicator`](src/listener/ScoreIndicator.java)

(See the files above for exact implementation and comments.)

## How it works — brief

- Start: [`Ass5Game.main`](src/Ass5Game.java) creates a [`arkanoid.Game`](src/arkanoid/Game.java), calls [`arkanoid.Game.initialize`](src/arkanoid/Game.java) and then [`arkanoid.Game.run`](src/arkanoid/Game.java).
- Initialization sets up walls, a paddle (`geometry.Paddle`), several `geometry.Ball` instances, and a grid of `geometry.Block` objects.
- Movement & collisions: `geometry.Ball.moveOneStep()` builds a `geometry.Line` trajectory and asks [`arkanoid.GameEnvironment.getClosestCollision`](src/arkanoid/GameEnvironment.java) for collisions. Collidables implement [`geometry.Collidable`](src/geometry/Collidable.java) and return a new `geometry.Velocity` on hit.
- Scoring & removal: blocks notify listeners (`listener.HitListener`) and are removed via [`listener.BlockRemover`](src/listener/BlockRemover.java). Score is tracked with [`listener.ScoreTrackingListener`](src/listener/ScoreTrackingListener.java) and shown by [`listener.ScoreIndicator`](src/listener/ScoreIndicator.java).

## Build / Run

- Requires biuoop-1.4.jar on the classpath (configured in [build.xml](build.xml)).
- To compile: `ant compile`
- To run: `ant run`

## Notes & suggestions

- Tests could be added for geometry (`geometry.Line`, `geometry.Rectangle.intersectionPoints`, `geometry.Point.isOnLine`).
- Consider extracting constants (screen size, paddle speed) from `arkanoid.Game` for easier tuning.
- Improve paddle collision math and add menus / levels.

---

Referenced files & symbols (quick links):
- [src/Ass5Game.java](src/Ass5Game.java) — [`Ass5Game.main`](src/Ass5Game.java)  
- [src/arkanoid/Game.java](src/arkanoid/Game.java) — [`arkanoid.Game.initialize`](src/arkanoid/Game.java), [`arkanoid.Game.run`](src/arkanoid/Game.java)  
- [src/arkanoid/GameEnvironment.java](src/arkanoid/GameEnvironment.java) — [`arkanoid.GameEnvironment.getClosestCollision`](src/arkanoid/GameEnvironment.java)  
- [src/arkanoid/Sprite.java](src/arkanoid/Sprite.java)  
- [src/arkanoid/SpriteCollection.java](src/arkanoid/SpriteCollection.java)  
- [src/geometry/Ball.java](src/geometry/Ball.java)  
- [src/geometry/Block.java](src/geometry/Block.java)  
- [src/geometry/Paddle.java](src/geometry/Paddle.java)  
- [src/geometry/Point.java](src/geometry/Point.java)  
- [src/geometry/Line.java](src/geometry/Line.java)  
- [src/geometry/Rectangle.java](src/geometry/Rectangle.java)  
- [src/geometry/Velocity.java](src/geometry/Velocity.java)  
- [src/geometry/Collidable.java](src/geometry/Collidable.java)  
- [src/geometry/CollisionInfo.java](src/geometry/CollisionInfo.java)  
- [src/listener/Counter.java](src/listener/Counter.java)  
- [src/listener/HitListener.java](src/listener/HitListener.java)  
- [src/listener/HitNotifier.java](src/listener/HitNotifier.java)  
- [src/listener/BallRemover.java](src/listener/BallRemover.java)  
- [src/listener/BlockRemover.java](src/listener/BlockRemover.java)  
- [src/listener/ScoreTrackingListener.java](src/listener/ScoreTrackingListener.java)  
- [src/listener/ScoreIndicator.java](src/listener/ScoreIndicator.java)
