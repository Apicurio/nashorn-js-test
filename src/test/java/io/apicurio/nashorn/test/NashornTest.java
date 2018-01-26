/*
 * Copyright 2018 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.nashorn.test;

import java.net.URL;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * @author eric.wittmann@gmail.com
 */
public class NashornTest {

    public static void debug(String message) {
        System.out.println("{DEBUG} :: " + message);
    }
    public static void trace(String message) {
        System.out.println("{TRACE} :: " + message);
    }
    public static void error(Object error) {
        System.err.println("{ERROR} :: " + error.toString());
    }

    @Test
    public void test() throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        URL consoleJsUrl = NashornTest.class.getClassLoader().getResource("js-lib/core-console.js");
        URL libraryJsUrl = NashornTest.class.getClassLoader().getResource("js-lib/core-library.js");

        debug("Nashorn JS engine created.");

        // Load the JS libraries into the engine
        engine.eval(IOUtils.toString(consoleJsUrl));
        engine.eval(IOUtils.toString(libraryJsUrl));

        debug("Nashorn JS engine initialized.");

        final Invocable invocable = (Invocable) engine;
        String inputDoc = IOUtils.toString(getClass().getResource("__begin.json"));
        
        debug("Input doc loaded: " + inputDoc);

        debug("======================================================");
        debug("Doing Test Now!");
        debug("======================================================");
        String mutatedDocument = invocable.invokeFunction("parseDocument", inputDoc).toString();
        
        debug("======================================================");
        debug("Test passed with output document:");
        debug("======================================================");
        debug(mutatedDocument);
    }

}
