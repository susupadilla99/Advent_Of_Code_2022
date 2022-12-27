class Day7 {

    val root: Node = Node("/", null)
    var curr: Node = root
    val directories = ArrayList<Node>()

    fun solvePuzzle1(lines: List<String>, idx: Int): Int {
        if (idx >= lines.size) {
            var sum = 0
            for (node in directories) {
                val tmp = node.calculateSize()
                sum += if (tmp <= 100000) tmp else 0
            }
            return sum
        }
//        println(lines[idx])
        executeCommand(lines[idx])
        return solvePuzzle1(lines, idx+1)
    }

    fun solvePuzzle2(lines:List<String>, idx: Int): Int {
        val rootSize = root.calculateSize()
        println("Total space: ${70000000}, Used space: $rootSize")
        val clearSize = 30000000 - (70000000 - rootSize)
        println("Unused space: ${70000000 - rootSize}, Clear needed: $clearSize")
        var min = 70000000
        for (node in directories) {
            val tmp = node.calculateSize()
            if (tmp in clearSize until min) min = tmp
        }
        return min
    }

    fun executeCommand(cmd: String) {
        val instr = cmd.split(" ")
        when (instr[0]) {
            "$" -> when(instr[1]){
                "cd" -> curr = curr.findNode(instr[2])!!
            }
            "dir" -> {
                val newNode = Node(instr[1], curr)
                curr.addChild( newNode )
                directories.add( newNode)
            }
            else -> curr.addChild( FileNode(instr[1], instr[0].toInt(), curr) )
        }
    }

    open class Node(val data: String, val parent: Node?, val children: ArrayList<Node> = ArrayList<Node>()) {
        fun addChild(new: Node) {
            children.add(new)
        }
        fun findNode(name: String): Node? {
            if (name == "..") return parent
            for (temp in children) {
                if (temp.data == name) return temp
            }
            if (data == name) return this
            return null // failure
        }

        open fun calculateSize(): Int {
            var sum = 0;
            for(child in children) {
                sum += child.calculateSize()
            }
            return sum
        }
        fun printNode(lvl: Int) {
            println(data)
            for (child in children) {
                for(i in 0..lvl) print(" ")
                child.printNode(lvl+1)
            }
        }
    }

    class FileNode(val name: String, val size: Int, val p: Node) : Node(name, p) {
        override fun calculateSize(): Int = size
    }
}