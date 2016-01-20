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
package org.rising.jenkins.real;

import org.apache.commons.lang3.NotImplementedException;
import org.rising.http.PostRequest;
import org.rising.jenkins.Credentials;
import org.rising.jenkins.Jenkins;
import org.rising.jenkins.Jobs;
import org.rising.jenkins.Users;

/**
 * Jenkins instance.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RealJenkins implements Jenkins {

    /**
     * Base Jenkins URL.
     */
    private final transient String base;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * Ctor.
     *
     * @param url Base Jenkins URL.
     * @param credentials Jenkins credentials.
     */
    public RealJenkins(final String url, final Credentials credentials) {
        this.base = url;
        this.creds = credentials;
    }

    /**
     * All the jobs of this Jenkins instance.
     *
     * @return Jobs.
     * @throws Exception If reading jobs was not successful.
     */
    public Jobs jobs() throws Exception {
        return new RealJobs(this.base, this.creds);
    }

    /**
     * All the registered users of this Jenkins instance.
     *
     * @return Users.
     * @throws Exception If reading users was not successful.
     * @todo: Let's implement this method and solve Issue #13.
     */
    public Users users() throws Exception {
        throw new NotImplementedException(
            String.format(
                "users() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Root XML content of Jenkins.
     *
     * @return XML string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return new PostRequest(
            String.format("%s/api/xml?depth=1", this.base), this.creds.headers()
        ).execute();
    }
}