class Day3 {

    fun solvePuzzle1(lines: List<String>, idx: Int): Int {
        if (idx >= lines.size) return 0
        val left = lines[idx].substring(0,lines[idx].length/2)
        val right = lines[idx].substring(lines[idx].length/2)
        val map = HashMap<Char, Int>()
        for (c in left) map[c] = idx
        for (c in right)
            if (map.containsKey(c)) return getPriority(c) + solvePuzzle1(lines, idx+1)
        return -999999
    }

    fun solvePuzzle2(lines: List<String>, idx: Int): Int {
        if (idx >= lines.size) return 0

        val first = lines[idx]
        val second = lines[idx+1]
        val third = lines[idx+2]
        val commonAll = HashMap<Char, Int>()
        var commonOne = HashMap<Char, Boolean>()

        for (c in first) if (!commonOne.containsKey(c)) {
            commonOne[c] = true
            if (!commonAll.containsKey(c)) commonAll[c] = 1
        }

        commonOne = HashMap<Char, Boolean>()

        for (c in second) if (!commonOne.containsKey(c)) {
            commonOne[c] = true
            if (commonAll.containsKey(c)) commonAll[c] = 2
        }

        commonOne = HashMap<Char, Boolean>()

        for (c in third) if (!commonOne.containsKey(c)) {
            commonOne[c] = true
            if (commonAll.containsKey(c) && commonAll[c] == 2) {
                return getPriority(c) + solvePuzzle2(lines, idx+3)
            }
        }

        return -999999
    }

    fun getPriority(c: Char): Int {
        return if (c.isLowerCase()) {
            c.code - 'a'.code + 1
        } else {
            c.code - 'A'.code + 27
        }
    }

}