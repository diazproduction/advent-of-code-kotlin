import java.lang.Exception

enum class Result(val points: Int) { WIN(6), LOSE(0), DRAW(3) }

enum class Play(val points: Int) {
    ROCK(1) {
        override fun against(t: Play): Result {
            return when (t) {
                ROCK -> Result.DRAW
                PAPER -> Result.LOSE
                SCISSOR -> Result.WIN
            }
        }
    },
    PAPER(2) {
        override fun against(t: Play): Result {
            return when (t) {
                ROCK -> Result.WIN
                PAPER -> Result.DRAW
                SCISSOR -> Result.LOSE
            }
        }
    },
    SCISSOR(3) {
        override fun against(t: Play): Result {
            return when (t) {
                ROCK -> Result.LOSE
                PAPER -> Result.WIN
                SCISSOR -> Result.DRAW
            }
        }
    };

    abstract fun against(t: Play): Result
}

fun playToWin(enemy: Play): Play {
    return when (enemy) {
        Play.ROCK -> Play.PAPER
        Play.PAPER -> Play.SCISSOR
        Play.SCISSOR -> Play.ROCK
    }
}

fun playToLose(enemy: Play): Play {
    return when (enemy) {
        Play.ROCK -> Play.SCISSOR
        Play.PAPER -> Play.ROCK
        Play.SCISSOR -> Play.PAPER
    }
}

fun playToDraw(enemy: Play): Play = enemy

fun parsePlayPart1(rawPlay: String): Play {
    return when (rawPlay) {
        "A", "X" -> Play.ROCK
        "B", "Y" -> Play.PAPER
        "C", "Z" -> Play.SCISSOR
        else -> throw Exception("Not a valid input!")
    }
}

fun parsePlayPart2(enemyRawPlay: String, myRawPlay: String): Play {
    val enemyPlay = parsePlayPart1(enemyRawPlay)
    return when (myRawPlay) {
        "X" -> playToLose(enemyPlay)
        "Y" -> playToDraw(enemyPlay)
        "Z" -> playToWin(enemyPlay)
        else -> throw Exception("Not a valid input!")
    }
}

fun calculateScore(enemyPlay: Play, myPlay: Play): Int {
    return myPlay.points + myPlay.against(enemyPlay).points
}

fun main() {
    fun sumPoints(points: List<Int>): Int {
        return points.reduce { acc, point -> acc + point }
    }

    fun part1(input: List<String>): Int {
        val plays =
            input
                .map { it.split(" ") }
                .map { play -> listOf(parsePlayPart1(play[0]), parsePlayPart1(play[1])) }
        val points = plays.map { play -> calculateScore(play[0], play[1]) }

        return sumPoints(points)
    }

    fun part2(input: List<String>): Int {
        val plays =
            input
                .map { it.split(" ") }
                .map { play -> listOf(parsePlayPart1(play[0]), parsePlayPart2(play[0], play[1])) }
        val points = plays.map { play -> calculateScore(play[0], play[1]) }

        return sumPoints(points)
    }

    val input = readInput("Day02")

    println(part1(input))
    println(part2(input))
}