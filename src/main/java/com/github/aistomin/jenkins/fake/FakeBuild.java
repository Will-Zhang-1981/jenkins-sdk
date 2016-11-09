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

import com.github.aistomin.jenkins.Build;
import com.github.aistomin.jenkins.BuildDetails;
import com.github.aistomin.jenkins.BuildResult;
import com.github.aistomin.jenkins.real.RealBuildDetails;
import com.github.aistomin.xml.Xml;
import com.github.aistomin.xml.XmlResource;
import com.github.aistomin.xml.XmlString;
import java.util.Date;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake Jenkins' job build.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FakeBuild implements Build {

    /**
     * XML content of the build.
     */
    private final transient Xml content;

    /**
     * OnDelete action.
     */
    private final transient Runnable cbdelete;

    /**
     * OnCancel action.
     */
    private final transient Runnable cbcancel;

    /**
     * Default ctor.
     */
    public FakeBuild() {
        this(
            new XmlResource("build.xml"), new DoNothing(),
            new DoNothing()
        );
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @param ondelete OnDelete action.
     * @param oncancel OnCancel action.
     */
    public FakeBuild(
        final Xml xml, final Runnable ondelete, final Runnable oncancel
    ) {
        this.content = xml;
        this.cbdelete = ondelete;
        this.cbcancel = oncancel;
    }

    @Override
    public String number() throws Exception {
        return this.content.field("//build/displayName/text()");
    }

    @Override
    public BuildResult result() throws Exception {
        return BuildResult.valueOf(this.content.field("//build/result/text()"));
    }

    @Override
    public Date date() throws Exception {
        return new Date(
            Long.parseLong(this.content.field("//build/timestamp/text()"))
        );
    }

    @Override
    public String url() throws Exception {
        return this.content.field("//build/url/text()");
    }

    @Override
    public BuildDetails details() throws Exception {
        return new RealBuildDetails(new XmlString(this.xml()));
    }

    @Override
    public void delete() throws Exception {
        this.cbdelete.run();
    }

    @Override
    public void cancel() throws Exception {
        this.cbcancel.run();
    }

    /**
     * Set/unset flag that allows to keep build logs forever.
     *
     * @throws Exception If error occurred.
     * @todo: Let's implement this method in issue #316
     */
    public void toggleLogKeep() throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.toggleLogKeep() is not implemented.", this.getClass()
            )
        );
    }

    @Override
    public String xml() throws Exception {
        return this.content.content();
    }
}
