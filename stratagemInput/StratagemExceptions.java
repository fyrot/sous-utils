package org.firstinspires.ftc.teamcode.sousutils.stratagemInput;

class StratagemNotConnectedException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "";

    StratagemNotConnectedException(String message) {
        super(message);
    }

    StratagemNotConnectedException() {
        this(DEFAULT_MESSAGE);
    }
}
// turns out "industry standard" curly braces is what we used to do oops

class StratagemPrefixException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "";
    StratagemPrefixException(String message) {
        super(message);
    }

    StratagemPrefixException() {
        this(DEFAULT_MESSAGE);
    }
}

