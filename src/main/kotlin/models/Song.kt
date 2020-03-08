package models

import common.Htmlable

data class Song(
    val title: String,
    val album: String = "Single",
    val year: Int,
    val artist: String = "Unknown",
    val genre: String = "Unknown",
    val filePath: String? = null
) : Htmlable {
    override fun toHtml() = "<tr>" +
            "<td>$title</td>" +
            "<td>$album</td>" +
            "<td>$year</td>" +
            "<td>$artist</td>" +
            "<td>$genre</td>" +
            "<td><audio controls id=\"audio\"><source src=\"audio/$filePath\"/></audio></td>" +
            "</tr>"
}