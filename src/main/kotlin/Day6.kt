class Day6 {

    fun solvePuzzle1(stream: String, idx: Int ): Int {
        if (idx>stream.length) return 9999

        val last4 = stream.substring(idx-4, idx)

        for(c in last4) {
            var count = 0
            for (c2 in last4) {
                if (c==c2) count++
            }
            if (count > 1) return solvePuzzle1(stream, idx+1)
        }

        return idx
    }

    fun solvePuzzle2(stream: String, idx: Int): Int {
        if (idx>stream.length) return 9999

        val last14 = stream.substring(idx-14, idx)

        println(" $idx - $last14 ")

        for(c in last14) {
            var count = 0
            for (c2 in last14) {
                if (c==c2) count++
            }
            if (count > 1) return solvePuzzle2(stream, idx+1)
        }

        return idx
    }

}