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
package com.github.aistomin.jenkins.fake;

import com.github.aistomin.jenkins.Jenkins;
import com.github.aistomin.jenkins.Job;
import com.github.aistomin.jenkins.Users;
import com.github.aistomin.xml.XmlString;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Test for FakeJenkins class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeJenkinsTest {

    /**
     * Can create fake instances using default constructor.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testDefaultConstructor() throws Exception {
        final Jenkins jenkins = new FakeJenkins();
        MatcherAssert.assertThat(
            jenkins.jobs(), new IsInstanceOf(FakeJobs.class)
        );
        MatcherAssert.assertThat(
            jenkins.users(), new IsInstanceOf(FakeUsers.class)
        );
        MatcherAssert.assertThat(jenkins.xml(), new IsInstanceOf(String.class));
        MatcherAssert.assertThat(
            jenkins.xml().startsWith("<hudson>"), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            jenkins.xml().contains(
                "https://cisdk-istomin.rhcloud.com/job/test-disabled-job/"
            ), new IsEqual<>(true)
        );
        MatcherAssert.assertThat(
            jenkins.xml().endsWith("</hudson>"), new IsEqual<>(true)
        );
    }

    /**
     * Can create fake instances providing only Users.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testConstructorWithUsers() throws Exception {
        final Users users = new FakeUsers();
        final Jenkins jenkins = new FakeJenkins(users);
        MatcherAssert.assertThat(
            jenkins.jobs(), new IsInstanceOf(FakeJobs.class)
        );
        MatcherAssert.assertThat(jenkins.xml(), new IsInstanceOf(String.class));
        MatcherAssert.assertThat(jenkins.users(), new IsEqual<>(users));
    }

    /**
     * Can list Jenkins' jobs.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListJobs() throws Exception {
        MatcherAssert.assertThat(
            new FakeJenkins().jobs().iterator().next(),
            new IsInstanceOf(Job.class)
        );
    }

    /**
     * Can list Jenkins' users.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanListUsers() throws Exception {
        final Users users = new FakeUsers();
        MatcherAssert.assertThat(
            new FakeJenkins(users).users(), new IsEqual<>(users)
        );
    }

    /**
     * Can read Jenkins' XML.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = "<hudson></hudson>";
        MatcherAssert.assertThat(
            new FakeJenkins(new XmlString(xml)).xml(), new IsEqual<>(xml)
        );
    }

    /**
     * Can read Jenkins' version.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadVersion() throws Exception {
        MatcherAssert.assertThat(
            new FakeJenkins().version(), new IsEqual<>("1.642.2")
        );
    }

    /**
     * Can restart fake jenkins.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanRestart() throws Exception {
        final List<String> calls = new ArrayList<>(1);
        new FakeJenkins(
            new Runnable() {
                public void run() {
                    calls.add("called!!!");
                }
            }
        ).restart();
        MatcherAssert.assertThat(calls.size(), new IsEqual<>(1));
    }
}
