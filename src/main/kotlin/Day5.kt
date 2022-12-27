import java.util.Stack

class Day5(val supplies: List<Stack<Char>>){

    fun solvePuzzle1(lines: List<String>, idx:Int): String {
        if (idx >= lines.size) {
            printStack(supplies)
            return getTop(supplies)
        }
        val instructions = lines[idx].split(" ")
        val amt = Integer.parseInt(instructions[1])
        val from = Integer.parseInt(instructions[3])-1
        val to = Integer.parseInt(instructions[5])-1

        println("-------------")
        println("${from+1}: ${supplies[from]} -- $amt --> ${to+1}: ${supplies[to]} ")

        val temp = supplies[from].subList(supplies[from].size-amt,supplies[from].size)
        println(temp)

        for (i in 0 until amt) {
            supplies[to].add(temp[i])
        }

        for (i in 0 until amt) {
            supplies[from].pop()
        }

        println("Result -> $from: ${supplies[from]} , $to: ${supplies[to]} ")

        return solvePuzzle1(lines, idx+1)
    }

    fun getTop(supplies: List<Stack<Char>>):String {
        if (supplies.isEmpty()) return ""
        return supplies[0].peek() + getTop(supplies.subList(1, supplies.size))
    }

    fun printStack(stack: List<Stack<Char>>) {
        println("=========================\nFinal Stack")
        var count = 1
        for (s in stack) {
            println("$count: $s")
            count++
        }
    }
}