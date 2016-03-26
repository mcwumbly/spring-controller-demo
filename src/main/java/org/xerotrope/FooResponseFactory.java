package org.xerotrope;

import org.springframework.stereotype.Component;

@Component
public class FooResponseFactory {
    public FooResponse forVersion(Integer version) {
        if (version == null || version >= 2) {
            return new FooResponseV2();
        }

        return new FooResponseV1();
    }
}
