package repository

import com.google.gson.Gson
import models.Song
import java.io.FileReader
import java.io.FileWriter

class SongsRepositoryImpl : SongsRepository {
    override fun getAllSongs(): ArrayList<Song> {
        val reader = FileReader(FILE_PATH)
        val songs = Gson()
            .fromJson(reader, Array<Song>::class.java)
            .toCollection(ArrayList())
        reader.close()
        return songs
    }

    override fun addSong(song: Song) {
        val songs = getAllSongs()
        songs.add(song)
        val writer = FileWriter(FILE_PATH)
        Gson().toJson(songs, writer)
        writer.close()
    }

    companion object {
        private const val FILE_PATH = "D:\\Projects\\WebApplication\\songs.json"
    }
}