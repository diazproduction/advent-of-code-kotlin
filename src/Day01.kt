fun main() {
    fun caloriesByElf(input: List<String>): List<Int> {
        val result: MutableList<MutableList<Int>> = mutableListOf()
        var smallResult: MutableList<Int> = mutableListOf()

        for ((index, calorie) in input.withIndex()) {
            if (calorie == "") {
                result.add(smallResult)
                smallResult = mutableListOf()
            } else {
                smallResult.add(calorie.toInt())
            }

            if (index == input.lastIndex) {
                result.add(smallResult)
            }
        }

        return result.map { list -> list.reduce { acc, number -> acc + number } }
    }

    fun part1(input: List<String>): Int {
        val calories = caloriesByElf(input).sorted().reversed()
        return calories[0]
    }

    fun part2(input: List<String>): Int {
        val calories = caloriesByElf(input).sorted().reversed()
        return calories[0] + calories[1] + calories[2]
    }

    val input = readInput("Day01")

    println(part1(input))
    println(part2(input))
    check(part1(input) == 70369)
    check(part2(input) == 203002)
}