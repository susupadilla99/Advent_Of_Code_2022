import kotlin.system.exitProcess

class Day2 {

    enum class Choice(val score: Int) {
        Rock(1),
        Paper(2),
        Scissor(3)
    }

    fun solvePuzzle1(list: List<String>, idx:Int):Int {
        if (idx >= list.size) return 0
        if (list[idx] == "") return solvePuzzle1(list, idx+1)
        return calcScore(list[idx]) + solvePuzzle1(list, idx+1)
    }

    fun solvePuzzle2(list: List<String>, idx: Int): Int {
        if (idx >= list.size) return 0
        if (list[idx] == "") return solvePuzzle2(list, idx+1)
        return calcScore2(list[idx]) + solvePuzzle2(list, idx+1)
    }

    fun calcScore(line: String): Int {
        val enemyChoice = translate(line[0])
        val yourChoice = translate(line[2])
        return evaluateWinLoss(enemyChoice, yourChoice) + yourChoice.score
    }

    fun calcScore2(line: String): Int {
        val enemy = translate(line[0])
        val you = translate2(enemy, line[2])
        return evaluateWinLoss(enemy, you) + you.score
    }

    fun translate(c: Char): Choice {
        return when (c) {
            'A' -> Choice.Rock
            'B' -> Choice.Paper
            'C' -> Choice.Scissor
            'X' -> Choice.Rock
            'Y' -> Choice.Paper
            'Z' -> Choice.Scissor
            else -> {
                println("Choice Error found")
                exitProcess(1)
                Choice.Rock
            }
        }
    }

    fun translate2(enemy: Choice, c: Char): Choice {
         return when (enemy) {
            Choice.Rock -> when (c) {
                'X' -> Choice.Scissor
                'Y' -> Choice.Rock
                'Z' -> Choice.Paper
                else -> exitProcess(1)
            }
            Choice.Paper -> when (c) {
                'X' -> Choice.Rock
                'Y' -> Choice.Paper
                'Z' -> Choice. Scissor
                else -> exitProcess(1)

            }
            Choice.Scissor -> when (c) {
                'X' -> Choice.Paper
                'Y' -> Choice.Scissor
                'Z' -> Choice. Rock
                else -> exitProcess(1)
            }
        }
    }

    fun evaluateWinLoss(enemy: Choice, you: Choice): Int {
        when (enemy) {
            Choice.Rock -> when (you) {
                Choice.Rock -> return 3
                Choice.Paper -> return 6
                Choice.Scissor -> return 0
            }

            Choice.Paper -> when (you) {
                Choice.Rock -> return 0
                Choice.Paper -> return 3
                Choice.Scissor -> return 6
            }

            Choice.Scissor -> when (you) {
                Choice.Rock -> return 6
                Choice.Paper -> return 0
                Choice.Scissor -> return 3
            }
        }
    }
}