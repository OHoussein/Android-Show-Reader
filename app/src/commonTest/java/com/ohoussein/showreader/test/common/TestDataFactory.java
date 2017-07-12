package com.ohoussein.showreader.test.common;

import com.ohoussein.showreader.entity.Image;
import com.ohoussein.showreader.entity.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    private static AtomicLong idIndex = new AtomicLong();

    private static Long makeId() {
        return idIndex.getAndIncrement();
    }

    private static String makeName(String uniqueSuffix) {
        return "Name " + uniqueSuffix;
    }

    private static String makeSummary(String uniqueSuffix) {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit " + uniqueSuffix;
    }

    private static String makeImageUri(long id) {
        return "http://lorempixel.com/400/800/abstract/" + id;
    }

    private static String makeThumbnailImageUri(long id) {
        return "http://lorempixel.com/100/200/abstract/" + id;
    }

    public static Show makeShow(String uniqueSuffix) {
        Show show = new Show();
        show.setId(makeId());
        show.setName(makeName(uniqueSuffix));
        show.setSummary(makeSummary(uniqueSuffix));
        show.setImage(new Image(makeThumbnailImageUri(show.getId()), makeImageUri(show.getId())));
        return show;
    }

    public static List<Show> makeCities(int number) {
        List<Show> shows = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            shows.add(makeShow(String.valueOf(i)));
        }
        return shows;
    }
}