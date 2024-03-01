package org.example.training;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldTest {

    @Test
    @StdIo
    public void testMain(StdOut out) {
        // this file is generated at build time
        Path helloTxt = Path.of("target/hello.txt");

        // call main
        HelloWorld.main(new String[]{ helloTxt.toString() });

        // expect System output `hello $person`
        assertEquals("hello Arthur Dent", out.capturedLines()[0]);
    }
}
