package warvale.core.plugin.utils;

import java.util.Random;

/**
 * Created by Draem on 5/8/2017.
 */
public class NumberUtils {

    public static Integer random(Integer max, Integer min) {
        return new Random().nextInt((max - min) + 1) + min;
    }

}
