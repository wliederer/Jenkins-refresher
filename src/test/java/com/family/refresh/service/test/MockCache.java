package com.family.refresh.service.test;

import java.util.concurrent.Callable;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

public class MockCache implements Cache {

    private final String name;
    private final Object value;

    public MockCache(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (value != null) {
            return new SimpleValueWrapper(value);
        }
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        // No-op for mocking purposes
    }

    @Override
    public void evict(Object key) {
        // No-op for mocking purposes
    }

    @Override
    public void clear() {
        // No-op for mocking purposes
    }

	@Override
	public <T> T get(Object key, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return null;
	}

}
