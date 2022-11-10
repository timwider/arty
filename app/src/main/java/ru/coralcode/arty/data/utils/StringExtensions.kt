package ru.coralcode.arty.data.utils

private const val SPACES_DELIMITER = " "

fun String.splitBySpaces(): List<String> = this.split(SPACES_DELIMITER)

/**
 * Takes a text and returns its start and end index.
 * If the text occurs more than once, returns first match.
 * Returns Pair(0, 0) if text is not found.
 */
fun String.findTextBounds(text: String): Pair<Int, Int> {
    if (text !in this) return Pair(0, 0)
    val firstIndex = this.indexOf(text)
    val secondIndex = firstIndex + (text.length - 1)

    return if (firstIndex == -1) {
        Pair(0, 0)
    } else Pair(firstIndex, secondIndex)
}

/**
 * Like String.findTextBounds() but tries to get the position supplied in the arguments.
 * Example text: "Desired text occurs here and also here."
 * Output:
 */