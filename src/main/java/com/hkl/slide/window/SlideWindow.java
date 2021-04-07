package com.hkl.slide.window;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SlideWindow
 * @Description 滑动窗口算法
 * @Author jacker425
 * @Date 2021/4/7 上午10:01
 **/
@Component
@Slf4j
public class SlideWindow {

    //一个方法（接口）对应一个队列，targetName.methodName为key
    private volatile static Map<String, List<Long>> MAP = new ConcurrentHashMap<>();

    @Value("${window.limiter.timeWindow}")
    private long timeWindow;   //时间窗口大小

    @Value("${window.limiter.count}")
    private int count;     //限制次数

    /**
     * @Author jacker425
     * @Description //此版本为统一锁，后续可优化锁粒度，按listId进行上锁，提高速度
     * @Date 下午3:29 2021/4/7
     * @Param [listId] 方法名
     * @return boolean
     **/
    public synchronized boolean isGo(String listId) {
        long nowTime = System.currentTimeMillis();
        List<Long> list = MAP.computeIfAbsent(listId, k -> new ArrayList<>());
        if (list.size() < count) {
            list.add(0, nowTime);
            return true;
        }
        Long farTime = list.get(count - 1);
        if (nowTime - farTime <= timeWindow) {
            return false;
        } else {
            list.remove(count - 1);
            list.add(0, nowTime);
            return true;
        }
    }
}
