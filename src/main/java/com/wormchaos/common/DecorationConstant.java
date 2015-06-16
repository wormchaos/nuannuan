package com.wormchaos.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wormchaos on 2015/6/11.
 */
public class DecorationConstant {

    public static Map<String, Integer> levelToValMapping = new HashMap<String, Integer>() {{
        put(LEVEL_0, 0);
        put(LEVEL_1, 1);
        put(LEVEL_2, 2);
        put(LEVEL_3, 3);
        put(LEVEL_4, 4);
        put(LEVEL_5, 5);
        put(LEVEL_6, 6);
    }};

    public static Map<Integer, String> levelMapping = new HashMap<Integer, String>() {{
        put(0, LEVEL_0);
        put(1, LEVEL_1);
        put(2, LEVEL_2);
        put(3, LEVEL_3);
        put(4, LEVEL_4);
        put(5, LEVEL_5);
        put(6, LEVEL_6);
    }};

    public static Map<Integer, String> typeMapping = new HashMap<Integer, String>() {{
        put(1, TYPE_1);
        put(2, TYPE_2);
        put(3, TYPE_3);
        put(4, TYPE_4);
        put(5, TYPE_5);
        put(6, TYPE_6);
        put(7, TYPE_7);
        put(8, TYPE_8);
        put(9, TYPE_9);
    }};

    private static final String LEVEL_0 = "";

    private static final String LEVEL_1 = "D";

    private static final String LEVEL_2 = "C";

    private static final String LEVEL_3 = "B";

    private static final String LEVEL_4 = "A";

    private static final String LEVEL_5 = "S";

    private static final String LEVEL_6 = "SS";

    private static final String TYPE_1 = "发型";

    private static final String TYPE_2 = "连衣裙";

    private static final String TYPE_3 = "外套";

    private static final String TYPE_4 = "上衣";

    private static final String TYPE_5 = "下装";

    private static final String TYPE_6 = "袜子";

    private static final String TYPE_7 = "鞋子";

    private static final String TYPE_8 = "饰品";

    private static final String TYPE_9 = "妆容";

    private static final String TEST_JIANYUE = "简约";

    private static final String TEST_HUALI = "华丽";

    private static final String TEST_YOUYA = "优雅";

    private static final String TEST_HUOPO = "活泼";

    private static final String TEST_CHENGSHU = "成熟";

    private static final String TEST_KEAI = "可爱";

    private static final String TEST_XINGGAN = "性感";

    private static final String TEST_QINGCHUN = "清纯";

    private static final String TEST_QINGLIANG = "清凉";

    private static final String TEST_BAONUAN = "保暖";
}
