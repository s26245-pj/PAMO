/**
 * You have a list of words. Use for and if to print only the words that start with the letter l.
 */
fun main() {
    val words = listOf("banana", "layout", "apple", "language")
    for (w in words) {
        if (w.startsWith("l"))
            println(w)
    }
}