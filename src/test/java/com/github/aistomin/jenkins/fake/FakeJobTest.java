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

import com.github.aistomin.jenkins.Builds;
import com.github.aistomin.jenkins.JobDetails;
import com.github.aistomin.jenkins.real.RealJobDetails;
import com.github.aistomin.xml.XmlString;
import java.net.URL;
import java.util.UUID;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * Test for FakeJob class.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeJobTest {

    /**
     * Can create fake instances providing only job's name.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithName() throws Exception {
        final String name = UUID.randomUUID().toString();
        final FakeJob job = new FakeJob(name);
        MatcherAssert.assertThat(job.name(), new IsEqual<String>(name));
        MatcherAssert.assertThat(job.xml(), new IsInstanceOf(String.class));
    }

    /**
     * Can create fake instances providing only XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithXml() throws Exception {
        final String xml = "<job><id><displayName>test</displayName></job>";
        final FakeJob job = new FakeJob(new XmlString(xml));
        MatcherAssert.assertThat(job.xml(), new IsEqual<String>(xml));
        MatcherAssert.assertThat(job.name(), new IsInstanceOf(String.class));
    }

    /**
     * Can create with job details.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithDetails() throws Exception {
        final RealJobDetails details = new RealJobDetails(
            new XmlString("<job></job>")
        );
        final FakeJob job = new FakeJob(details);
        MatcherAssert.assertThat(
            job.details(), new IsEqual<JobDetails>(details)
        );
    }

    /**
     * Can create with URL.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithUrl() throws Exception {
        final URL url = new URL("http", "localhost", 8080, "/my-job");
        final FakeJob job = new FakeJob(url);
        MatcherAssert.assertThat(
            job.url(), new IsEqual<String>(url.toString())
        );
    }

    /**
     * Can create with builds.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanCreateWithBuilds() throws Exception {
        final FakeBuilds builds = new FakeBuilds();
        final FakeJob job = new FakeJob(builds);
        MatcherAssert.assertThat(
            job.builds(), new IsEqual<Builds>(builds)
        );
    }

    /**
     * Can read Fake job's XML.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadXml() throws Exception {
        final String xml = new FakeJob().xml();
        MatcherAssert.assertThat(
            xml.startsWith("<job>"), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.contains(
                "<displayName>test-different-builds-job</displayName>"
            ), new IsEqual<Boolean>(true)
        );
        MatcherAssert.assertThat(
            xml.endsWith("</job>"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can read Fake job's name.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadName() throws Exception {
        MatcherAssert.assertThat(
            new FakeJob().name().startsWith("job"), new IsEqual<Boolean>(true)
        );
    }

    /**
     * Can read Fake job's details.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadDetails() throws Exception {
        MatcherAssert.assertThat(
            new FakeJob().details().displayName(),
            new IsEqual<String>("test-different-builds-job")
        );
    }

    /**
     * Can read Fake job's URL.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadUrl() throws Exception {
        MatcherAssert.assertThat(
            new FakeJob().url(),
            new IsInstanceOf(String.class)
        );
    }

    /**
     * Can read Fake job's builds.
     *
     * @throws Exception If something goes wrong.
     */
    @Test
    public void testCanReadBuilds() throws Exception {
        MatcherAssert.assertThat(
            new FakeJob().builds(),
            new IsInstanceOf(Builds.class)
        );
    }
}
