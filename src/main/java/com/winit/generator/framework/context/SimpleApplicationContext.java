package com.winit.generator.framework.context;

public class SimpleApplicationContext extends ApplicationContext {

    @Override
    public void setAttribute(String key, Object obj) {
        this.ctx.put(key, obj);
    }

    @Override
    public Object getAttribute(String key) {
        return this.ctx.get(key);
    }

}
