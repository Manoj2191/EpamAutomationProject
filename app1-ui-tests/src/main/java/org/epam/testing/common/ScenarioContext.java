package org.epam.testing.common;

import io.cucumber.guice.ScenarioScoped;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ScenarioScoped
public class ScenarioContext {
    private Map<String, Object> data = new ConcurrentHashMap<>();

    public void setData(String key, Object value) {
        data.put(key, value);
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public void clear() {
        data.clear();
    }
}
