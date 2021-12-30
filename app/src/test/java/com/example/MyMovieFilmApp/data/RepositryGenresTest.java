package com.example.MyMovieFilmApp.data;

import com.example.MyMovieFilmApp.data.watchnow.Repositry;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepositryGenresTest {

    @Spy
    Repositry repositry = Repositry.getINSTANCE();

    @Before
    public void setUp() throws Exception {
    }

//    @Test
//    public void getPosts() {
//        doReturn(repositry.getPosts()).when(repositry).getPosts();
//        System.out.println(repositry.getPosts());
//    }
}