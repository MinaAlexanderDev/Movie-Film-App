package com.example.MyMovieFilmApp.ui.fragment;

import com.example.MyMovieFilmApp.ui.fragment.watchnow.WatchNowFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class WatchNowFragmentTest {
    @Mock
    WatchNowFragment watchNowFragment;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void isNetworkConnected() {
        doReturn(watchNowFragment.isNetworkConnected()).when(watchNowFragment).isNetworkConnected();
        System.out.println(watchNowFragment.isNetworkConnected());
    }

    @Test
    public void isInternetAvailable() {
        doReturn(watchNowFragment.isInternetAvailable()).when(watchNowFragment).isInternetAvailable();
        System.out.println(watchNowFragment.isInternetAvailable());
    }

}