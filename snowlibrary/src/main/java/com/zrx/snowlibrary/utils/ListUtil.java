package com.zrx.snowlibrary.utils;

import java.util.List;

/**
 * Created by Schnee on 2017/3/22.
 */

public class ListUtil {

    private ListUtil() {
    }

    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }
}
