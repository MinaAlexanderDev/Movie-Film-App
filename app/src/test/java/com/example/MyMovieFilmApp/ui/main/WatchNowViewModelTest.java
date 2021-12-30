package com.example.MyMovieFilmApp.ui.main;

import com.example.MyMovieFilmApp.ui.fragment.watchnow.WatchNowViewModel;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WatchNowViewModelTest {
    @Spy
    WatchNowViewModel watchNowViewModel;

    @Before
    public void setUp() throws Exception {
    }

//    @Test
//    public void getPosts() {
//        doReturn(watchNowViewModel.fillData()).when(watchNowViewModel).fillData();
//        System.out.println(watchNowViewModel.fillData());
//    }

}