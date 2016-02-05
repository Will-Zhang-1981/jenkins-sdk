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
import com.github.aistomin.jenkins.Job;
import com.github.aistomin.jenkins.JobDetails;
import com.github.aistomin.jenkins.JobParameter;
import com.github.aistomin.xml.XML;
import com.github.aistomin.xml.XMLResource;
import java.util.Iterator;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake jenkins' job.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class FakeJob implements Job {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "job.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient XML content;

    /**
     * Default ctor.
     *
     * @throws Exception If reading XML was not successful.
     */
    public FakeJob() throws Exception {
        this(new XMLResource(RESOURCE));
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeJob(final XML xml) {
        this.content = xml;
    }

    /**
     * Job name.
     *
     * @return Job name.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #113.
     */
    public String name() throws Exception {
        throw new NotImplementedException(
            String.format(
                "name() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job details.
     *
     * @return Job details.
     * @todo: Let's implement this method and solve Issue #54.
     */
    public JobDetails details() {
        throw new NotImplementedException(
            String.format(
                "details() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job URL.
     *
     * @return URL string.
     * @todo: Let's implement this method and solve Issue #55.
     */
    public String url() {
        throw new NotImplementedException(
            String.format(
                "url() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job builds.
     *
     * @return Builds.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and solve Issue #56.
     */
    public Builds builds() throws Exception {
        throw new NotImplementedException(
            String.format(
                "builds() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job parameters.
     *
     * @return Job's parameters.
     * @todo: Let's implement this method and solve Issue #57.
     */
    public Iterator<JobParameter> parameters() {
        throw new NotImplementedException(
            String.format(
                "parameters() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Fake job's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve Issue #58.
     */
    public String xml() throws Exception {
        return this.content.content();
    }
}
