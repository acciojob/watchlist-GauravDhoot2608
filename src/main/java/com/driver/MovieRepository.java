package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    private Map<String,Movie> movieMap = new HashMap<>();
    private Map<String,Director> directorMap = new HashMap<>();
    private Map<String,List<String>> movieDirectorPair = new HashMap<>();

    public void addMovie(Movie movie){
        movieMap.put(movie.getName() , movie);
    }

    public void addDirector(Director director){
        directorMap.put(director.getName() , director);
    }

    public void addMovieDirectorPair(String movieName , String directorName){
        List<String> movies = movieDirectorPair.getOrDefault(directorName ,new ArrayList<>());
        movies.add(movieName);
        movieDirectorPair.put(directorName , movies);
    }

    public Movie getMovieByName(String movieName){

        return movieMap.getOrDefault(movieName , null);
    }

    public Director getDirectorByName(String directorName){
        return directorMap.getOrDefault(directorName , null);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movies = movieDirectorPair.getOrDefault(directorName , new ArrayList<>());
        return movies;
    }

    public List<String> findAllMovies(){

        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirectorByName(String directorName){

        List<String> movies = movieDirectorPair.getOrDefault(directorName , new ArrayList<>());
        for(String movieName : movies){
            if(movieMap.containsKey(movieName)){
                movieMap.remove(movieName);
            }
        }
        movieDirectorPair.remove(directorName);
        directorMap.remove(directorName);
    }

    public void deleteAllDirectors(){
        for(String director : directorMap.keySet()){
            deleteDirectorByName(director);
        }
    }
}
