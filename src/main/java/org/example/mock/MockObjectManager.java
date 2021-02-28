package org.example.mock;

import java.util.HashMap;
import java.util.Map;

public class MockObjectManager {

    private Map<String, Object> mocks;
    private Map<String, Class<?>> mockTypes;

    public MockObjectManager() {
        mocks = new HashMap<>();
        mockTypes = new HashMap<>();
    }

    public Object lookupMock(String key) {
        return mocks.get(key);
    }

    public void putMock(String key, Object value) {
        mocks.put(key, value);
    }

    public void recordMockType(String handle, Class<?> clazz) {
        mockTypes.put(handle, clazz);
    }

    public Class<?> lookupMockType(String handle) {
        return mockTypes.get(handle);
    }

    public void deleteMock(String handle) {
        mocks.remove(handle);
    }

}