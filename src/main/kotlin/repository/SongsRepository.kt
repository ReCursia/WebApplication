package repository

import models.Song

interface SongsRepository {
    fun getAllSongs(): List<Song>
    fun addSong(song: Song)
}