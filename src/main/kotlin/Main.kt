import java.io.File
import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    val file = File("./src/main/inputs/Day7.txt")
    var lines: List<String> = file.readLines()


    val solution = Day7()
    println("\nDay 7")
    println("Puzzle 1: ${solution.solvePuzzle1(lines, 0)}")
    println("Puzzle 2: ${solution.solvePuzzle2(lines, 0)}")
}
