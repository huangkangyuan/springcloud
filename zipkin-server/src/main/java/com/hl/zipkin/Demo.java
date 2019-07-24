package com.hl.zipkin;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author huangkangyuan
 * @date 2019/7/11
 */
public class Demo extends AbstractQueuedSynchronizer {

    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }
}

