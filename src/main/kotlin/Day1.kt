import java.io.File
import javax.print.attribute.IntegerSyntax

class Day1 {

    val file = File("./src/main/inputs/Day1.txt")

    fun solvePuzzle1(): Int {
        val lines = file.readLines()
        var max: Int = 0
        var sum: Int = 0
        for (line in lines) {
            if (line != "") {
                sum += Integer.parseInt(line)
            } else {
                if (max < sum) max = sum
                sum = 0
            }
        }
        return max
    }

    fun solvePuzzle2(): Int {
        val lines = file.readLines()
        val elfs: ArrayList<Int> = ArrayList<Int>()
        elfs.add(0)
        for (line in lines) {
            if (line != "") {
                val last = elfs.size-1
                elfs.set( last, elfs.get(last) + Integer.parseInt(line))
            } else {
                elfs.add(0)
            }
        }
        elfs.sort()
        return elfs.subList(elfs.size-3,elfs.size).sum()
    }

}