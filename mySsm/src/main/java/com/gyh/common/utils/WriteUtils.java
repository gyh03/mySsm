package com.gyh.common.utils;

import com.gyh.common.pojo.MessageResult;
import com.gyh.utils.json.JacksonUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class WriteUtils {
    /**
     * 发送对象Json至客户端
     * @param result 消息对象
     * @param response 返回消息句柄
     * @return 是否成功
     */
    public static boolean renderObject(MessageResult result, HttpServletResponse response) {
        try {
            PrintWriter printer = response.getWriter();
            String message = JacksonUtils.toJson(result);
            printer.write(message);
            printer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
