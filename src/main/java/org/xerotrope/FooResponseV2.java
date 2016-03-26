package org.xerotrope;

public class FooResponseV2 implements FooResponse {

    public int getVersion() {
        return 2;
    }

    public String getFoo() {
        return "foo";
    }

    public String getBar() {
        return "bar";
    }
}
