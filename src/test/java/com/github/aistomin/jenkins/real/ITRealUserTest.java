/**
 * Copyright (c) 2016, Istomin Andrei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.aistomin.jenkins.real;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Integration tests for RealUser class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ITRealUserTest {

    /**
     * First username in test users iterator.
     */
    private static final transient String USERNAME = "\"system_builder";

    /**
     * Can read user's username.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUsername() throws Exception {
        MatcherAssert.assertThat(
            new TestJenkins().users().iterator().next().username(),
            new IsEqual<String>(ITRealUserTest.USERNAME)
        );
    }

    /**
     * Can read Jenkins' user XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new TestJenkins().users().iterator().next().xml();
        MatcherAssert.assertThat(
            xml.startsWith("<user>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains(String.format("<id>%s</id>", ITRealUserTest.USERNAME)),
            new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</user>"), new IsEqual<Boolean>(true)
        );
    }
}
