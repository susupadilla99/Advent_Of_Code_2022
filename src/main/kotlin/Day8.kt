import java.io.File
import java.util.ArrayList

fun main() {
    // read file to matrix
    val file = File("./src/main/inputs/Day8.txt")
    val lines: List<String> = file.readLines()
    val matrix: ArrayList<ArrayList<Int>> = ArrayList<ArrayList<Int>>()

    for (line: String in lines) {
        val tmp: ArrayList<Int> = ArrayList<Int>()
        for(c: Char in line) {
            tmp.add( c.digitToInt() )
        }
        matrix.add(tmp)
    }

    printMatrix(matrix)

    var visibleTrees: Int = 0
    for (i in 0..matrix.lastIndex) {
        for (j in 0..matrix[0].lastIndex) {
            var blockedUp = false
            var blockedDown = false
            var blockedLeft = false
            var blockedRight = false
            //up
            for (k in i-1 downTo 0) {
                if (matrix[i][j]<=matrix[k][j]) blockedUp = true
            }
            //down
            for (k in i+1..matrix.lastIndex) {
                if (matrix[i][j]<=matrix[k][j]) blockedDown = true
            }
            //left
            for (k in j-1 downTo 0) {
                if (matrix[i][j]<=matrix[i][k]) blockedLeft = true
            }
            //right
            for (k in j+1 .. matrix[0].lastIndex) {
                if (matrix[i][j]<=matrix[i][k]) blockedRight = true
            }
            val isVisible = !(blockedUp && blockedDown && blockedLeft && blockedRight)
            if (isVisible) visibleTrees++
        }
    }

    println("There are $visibleTrees total visible trees")

    println("----------------------------------")

    var max = 0
    for (i in 1 until matrix.lastIndex) {
        for (j in 1 until matrix[0].lastIndex) {
            var left = 1
            var right = 1
            var up = 1
            var down = 1
            //up
            for (k in i-1 downTo 1) {
                if (matrix[i][j]>matrix[k][j]) up++
                else break
            }
            //down
            for (k in i+1 until matrix.lastIndex) {
                if (matrix[i][j]>matrix[k][j]) down++
                else break
            }
            //left
            for (k in j-1 downTo 1) {
                if (matrix[i][j]>matrix[i][k]) left++
                else break
            }
            //right
            for (k in j+1 until matrix[0].lastIndex) {
                if (matrix[i][j]>matrix[i][k]) right++
                else break
            }
            val temp = up*down*left*right
            if (max<temp) max = temp
            println(" ${matrix[i][j]} - $up - $down - $left - $right ")
        }
    }

    println("The highest scenic score is $max")

}

fun printMatrix(matrix: ArrayList<ArrayList<Int>>) {
    println("----------------")
    for (row in matrix) {
        println(row)
    }
    println("----------------")
}