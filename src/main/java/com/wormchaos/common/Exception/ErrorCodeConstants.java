package com.wormchaos.common.Exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wormchaos on 2015/6/18.
 */
public class ErrorCodeConstants {

    public static Map<Integer, String> errorMapping = new HashMap<Integer, String>() {{
        put(2000, "用户未登录");
        put(3000, "参数不能为空");
        put(3001, "用户名密码错误");
    }};
}
