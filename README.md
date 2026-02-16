# Minesweeper CLI

A command-line Minesweeper game implemented in Java.

## Design

### Architecture

The application follows a layered architecture with clear separation of concerns:

- **`domain`** — Core model: `Board`, `Cell`, `Coordinate`, `GameStatus`. No dependencies on other packages.
- **`config`** — `GameConfiguration` (value object) and `GameConfigurationValidator` (validates grid/mine constraints).
- **`game`** — Game engine (`Game`), reveal logic (`RevealStrategy` interface, `RecursiveRevealStrategy`), and `RevealResult`.
- **`mines`** — Mine placement abstraction (`MinePlacer` interface, `RandomMinePlacer`).
- **`ui`** — Console I/O (`Console` interface, `SystemConsole`), `BoardRenderer`, `CoordinateParser`, and `GameRunner`.

### Key Design Decisions

- **Strategy Pattern**: `RevealStrategy` decouples the flood-fill algorithm from the `Game` class.
- **Dependency Injection**: `Game` accepts `MinePlacer` and `RevealStrategy` via constructor, enabling deterministic testing.
- **Interface-based testing**: `Console` and `MinePlacer` interfaces allow `FakeConsole` and `FixedMinePlacer` test doubles.

### Assumptions

- Grid is square (NxN), rows labeled A–Z (max 26×26).
- Mine count is capped at 35% of total cells.
- Coordinates are entered as `<Row><Col>` (e.g., `A1`, `D4`).
- Grid size must be at least 2.
- No zero mine game, needs atleast 1 mine.

## Environment

- **Java**: 17 or later
- **Build tool**: Maven 3.8+
- **OS**: Windows, macOS, or Linux

## Build

```bash
mvn clean compile
```

## Run

```bash
mvn -q exec:java
```

## Run Tests

```bash
mvn clean test
```

## Project Structure

```
src/
├── main/java/com/jithu/minesweeper/
│   ├── Main.java                    # Entry point
│   ├── config/
│   │   ├── GameConfiguration.java
│   │   ├── GameConfigurationValidator.java
│   │   └── ValidationResult.java
│   ├── domain/
│   │   ├── Board.java
│   │   ├── Cell.java
│   │   ├── Coordinate.java
│   │   └── GameStatus.java
│   ├── game/
│   │   ├── Game.java
│   │   ├── RecursiveRevealStrategy.java
│   │   ├── RevealResult.java
│   │   └── RevealStrategy.java
│   ├── mines/
│   │   ├── FixedMinePlacer.java
│   │   ├── MinePlacer.java
│   │   └── RandomMinePlacer.java
│   └── ui/
│       ├── BoardRenderer.java
│       ├── Console.java
│       ├── CoordinateParser.java
│       ├── GameRunner.java
│       └── SystemConsole.java
└── test/java/com/jithu/minesweeper/
    ├── GameRunnerE2ETest.java
    ├── config/
    │   ├── GameConfigurationTest.java
    │   └── GameConfigurationValidatorTest.java
    ├── domain/
    │   ├── BoardTest.java
    │   └── CellTest.java
    ├── game/
    │   ├── GameTest.java
    │   └── RecursiveRevealStrategyTest.java
    ├── mines/
    │   ├── FixedMinePlacerTest.java
    │   └── RandomMinePlacerTest.java
    └── ui/
        ├── BoardRendererTest.java
        ├── CoordinateParserTest.java
        └── FakeConsole.java
```
