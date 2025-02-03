package org.epam.testing.common.guice;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalScope implements Scope {
    @Override
    public <T> Provider<T> scope(Key<T> key, final Provider<T> provider) {
        return () -> {
            ThreadLocalCache cache = ThreadLocalCache.getInstance();
            T value = cache.get(key);

            if (value == null) {
                value = provider.get();
                cache.add(key, value);
            }
            return value;
        };
    }

    private static final class ThreadLocalCache {
        private final Map<Key<?>, Object> cache = new HashMap<>();

        private static final ThreadLocal<ThreadLocalCache> THREAD_LOCAL =
                ThreadLocal.withInitial(ThreadLocalCache::new);

        @SuppressWarnings("unchecked")
        <T> T get(Key<T> key) {
            return (T) cache.get(key);
        }

        <T> void add(Key<T> key, T value) {
            cache.put(key, value);
        }

        static ThreadLocalCache getInstance() {
            return THREAD_LOCAL.get();
        }
    }
}

