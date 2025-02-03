package org.epam.testing.common.guice;

import com.google.inject.Scope;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomScopes {
    public static final Scope THREAD_LOCAL = new ThreadLocalScope();
}

