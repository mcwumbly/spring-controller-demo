package org.xerotrope;


public class FooResponseV1 implements FooResponse{

    public int getVersion() {
        return 1;
    }

    public String getFoo() {
        return "foo";
    }
}
