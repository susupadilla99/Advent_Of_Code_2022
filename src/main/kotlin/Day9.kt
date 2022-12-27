import java.io.File
import kotlin.math.abs

// Variables
val map: HashMap<Int, Boolean> = HashMap<Int, Boolean>()
val rope = ArrayList<Int>()


fun main() {

    // Get & Transform input file
    val path = "./src/main/inputs/test.txt"
    val file = File(path)
    val inputLines = file.readLines()

    // Puzzle 1: Rope with 2 knots
    rope.add(0)
    rope.add(1)
    for (line in inputLines) {
        simulateInstruction(line[0], line.split(" ")[1].toInt())
    }
    println("Puzzle 1: Tail has traveled through ${map.size} positions")

}

/** Move the head of the rope based on the instruction provided
 *  Simulate movement of the rest of the rope accordingly
 *  Param:
 *      direction - which direction to move
 *      steps - how many steps
 */
fun simulateInstruction(direction: Char, steps: Int) {
    println("$direction $steps")
    for (i in 1..steps) {
        // decode head position
        var hX = rope[0]%10000
        var hY = rope[0]/10000
        // move head
        when (direction) {
            'U' -> hY++
            'D' -> hY--
            'L' -> hX--
            'R' -> hX++
        }
        rope[0] = hY*10000 + hX
        // simulate rope based on head new location
        for (i in 1..rope.lastIndex)
            rope[i] = moveKnot(rope[i-1], rope[i])
        println("    Tail( ${rope.last()%10000} , ${rope.last()/10000} )")
        // add tail position to hashmap
        map[rope.last()] = true
    }
}

/** Given a head knot position & tail knot position, calculate tail knot position after movement
 *  Param:
 *      head - encoded head knot position
 *      tail - encoded tail knot position
 *  Return:
 *      encoded new tail know position
 */
fun moveKnot(head: Int, tail: Int): Int {
    // convert int to position
    var hX = head%10000
    var hY = head/10000
    var tX = tail%10000
    var tY = tail/10000
    // check if head & tail is close together
    val samePosition = (hX == tX) && (hY == tY)
    val closeBy = abs(hX - tX) <= 1 && abs(hY-tY) <= 1
    if (samePosition || closeBy) return tail
    // move tail if head & tail are vertical or horizontal
    if (hX == tX || hY == tY) {
        if ((hX - tX) >= 2) tX++   // move right
        if ((hX - tX) <= -2) tX--  // move left
        if ((hY - tY) >= 2) tY++   // move up
        if ((hY - tY) <= -2) tY--  // move down
    }
    // move tail if head & tail are diagonal
    else {
        if (hX > tX && hY > tY) { tX++; tY++ } // up - right
        if (hX > tX && hY < tY) { tX++; tY-- } // down - right
        if (hX < tX && hY > tY) { tX--; tY++ } // up - left
        if (hX < tX && hY < tY) { tX--; tY--} // down - left
    }
    return tY*10000+tX
}