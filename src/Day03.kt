fun getPriority(char: Char): Int {
    val code = char.hashCode()
    return when (char) {
        in 'a'..'z' -> code - 'a'.hashCode() + 1
        in 'A'..'Z' -> code - 'A'.hashCode() + getPriority('z') + 1
        else -> 0
    }
}

fun findEqual(rucksack: String): Char {
    val halfway = rucksack.length / 2
    val first = rucksack.slice(0 until halfway).toCharArray()
    val second = rucksack.slice(halfway until rucksack.length).toCharArray()

    val common = first.intersect(second.toSet())
    return common.first()
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { findEqual(it) }.sumOf { getPriority(it) }
    }

    fun part2(input: List<String>): Int {
        return input.asSequence()
            .windowed(3, 3)
            .map { list -> list.map { it.split("") } }
            .map { (first, second, third) -> first.intersect(second.intersect(third.toSet())) }
            .map { it.last() }
            .sumOf { getPriority(it.toCharArray().first()) }
    }

    val input = readInput("Day03")

    println(input)
    
    println(part1(input))
    println(part2(input))
}