package org.popkit.leap.elpa.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aborn Jiang
 * Mail aborn.jiang@gmail.com
 * 2016-05-14:14:35
 */
public class TimeVersionUtils {
    private TimeVersionUtils() {}

    public static List<Integer> toArr(long time) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(time)));
        list.add(Integer.parseInt(new SimpleDateFormat("HHmm").format(time)));
        return list;//list.toArray(new Integer[list.size()]);
    }

    public static String toVersionString(long time) {
        return StringUtils.join(toArr(time), ".");
    }
}
