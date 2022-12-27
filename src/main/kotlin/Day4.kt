
class Day4 {

    fun solvePuzzle1(lines: List<String>, idx: Int): Int {
        if (idx >= lines.size) return 0
        val ranges = convertToRanges(lines[idx])

        val leftIncludeRight = ranges[0]<=ranges[2] && ranges[1]>=ranges[3]
        val rightIncludeLeft = ranges[0]>=ranges[2] && ranges[1]<=ranges[3]

        return ( if (leftIncludeRight || rightIncludeLeft) 1 else 0 ) + solvePuzzle1(lines, idx+1)
    }

    fun solvePuzzle2(lines: List<String>, idx: Int): Int {
        if (idx>= lines.size) return 0
        val ranges = convertToRanges(lines[idx])

        val overlap = ranges[0]<=ranges[3] && ranges[2]<=ranges[1]

        println(" ${lines[idx]} - $overlap ")

        return (if (overlap) 1 else 0) + solvePuzzle2(lines, idx+1)
    }

    fun convertToRanges(line: String): List<Int> {
        val ranges = ArrayList<Int>()
        var startIdx = 0
        for (i in line.indices) {
            if (line[i] == '-' || line[i] == ',') {
                ranges.add(Integer.parseInt(line.substring(startIdx, i)))
                startIdx = i + 1
            }
        }
        ranges.add(Integer.parseInt(line.substring(startIdx)))
        return ranges
    }

}