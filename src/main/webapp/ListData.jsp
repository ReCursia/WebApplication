<%@ page import="models.Song" %>
<%@ page import="repository.SongsRepository" %>
<%@ page import="repository.SongsRepositoryImpl" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    SongsRepository songsRepository = new SongsRepositoryImpl();
    List<Song> elements = songsRepository.getAllSongs();
    for (Song song : elements) {
        if (song.getArtist().equals(artist)) {
            out.write(song.toHtml());
        }
    }
%>