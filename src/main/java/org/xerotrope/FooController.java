package org.xerotrope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
public class FooController {

    private FooResponseFactory fooResponseFactory;

    @Autowired
    public FooController(FooResponseFactory fooResponseFactory) {
        this.fooResponseFactory = fooResponseFactory;
    }

    @RequestMapping(value = "/foo", method = RequestMethod.GET)
    public FooResponse getFoo(@RequestHeader(required = false) Integer version) {
        return fooResponseFactory.forVersion(version);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)  {
        return new ErrorResponse(
                "Invalid version",
                e.getMessage()
        );
    }
}
