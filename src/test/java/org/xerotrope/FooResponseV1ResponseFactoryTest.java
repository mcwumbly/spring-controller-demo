package org.xerotrope;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class FooResponseV1ResponseFactoryTest {

    FooResponseFactory subject = new FooResponseFactory();

    @Test
    public void forVersion_whenCalledWith1_returnsFooResponseV1() {
        FooResponse fooResponse = subject.forVersion(1);
        assertThat(fooResponse, instanceOf(FooResponseV1.class));
    }

    @Test
    public void forVersion_whenCalledWith2_returnsFooResponseV1() {
        FooResponse fooResponse = subject.forVersion(2);
        assertThat(fooResponse, instanceOf(FooResponseV2.class));
    }

    @Test
    public void forVersion_whenVersionNotSpecified_returnsLatestVersion() {
        FooResponse fooResponse  = subject.forVersion(null);
        assertThat(fooResponse, instanceOf(FooResponseV2.class));
    }
}