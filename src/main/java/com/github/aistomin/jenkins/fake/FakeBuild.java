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
import java.net.URL;
import java.util.Date;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Fake Jenkins' job build.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class FakeBuild implements Build {

    /**
     * XML resource file name.
     */
    private static final String RESOURCE = "build.xml";

    /**
     * XML content that should be returned in xml() method.
     */
    private final transient Xml content;

    /**
     * Build's number that will be returned in number() method.
     */
    private final transient String identifier;

    /**
     * Fake build's result.
     */
    private final transient BuildResult status;

    /**
     * Fake build's start date.
     */
    private final transient Date start;

    /**
     * Fake build's details.
     */
    private final transient BuildDetails detailed;

    /**
     * Fake build's URL.
     */
    private final transient String uri;

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
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            BuildResult.SUCCESS, new Date(),
            new RealBuildDetails(new XmlResource(FakeBuild.RESOURCE)),
            FakeBuild.defaultUrl(), new DoNothing(), new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     */
    public FakeBuild(final Xml xml) {
        this(
            xml, FakeBuild.defaultNumber(), BuildResult.SUCCESS, new Date(),
            new RealBuildDetails(new XmlResource(FakeBuild.RESOURCE)),
            FakeBuild.defaultUrl(), new DoNothing(), new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param result Fake build's result.
     */
    public FakeBuild(final BuildResult result) {
        this(
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            result, new Date(),
            new RealBuildDetails(new XmlResource(FakeBuild.RESOURCE)),
            FakeBuild.defaultUrl(), new DoNothing(), new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param number Build's number that will be returned in number() method.
     */
    public FakeBuild(final String number) {
        this(
            new XmlResource(FakeBuild.RESOURCE), number, BuildResult.SUCCESS,
            new Date(),
            new RealBuildDetails(new XmlResource(FakeBuild.RESOURCE)),
            FakeBuild.defaultUrl(), new DoNothing(), new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param date Fake build's start date.
     */
    public FakeBuild(final Date date) {
        this(
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            BuildResult.SUCCESS, date,
            new RealBuildDetails(new XmlResource(FakeBuild.RESOURCE)),
            FakeBuild.defaultUrl(), new DoNothing(), new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param details Fake build's details.
     */
    public FakeBuild(final BuildDetails details) {
        this(
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            BuildResult.SUCCESS, new Date(), details, FakeBuild.defaultUrl(),
            new DoNothing(), new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param url Fake build's URL.
     */
    public FakeBuild(final URL url) {
        this(
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            BuildResult.SUCCESS, new Date(),
            new RealBuildDetails(new XmlResource(FakeBuild.RESOURCE)),
            url.toString(), new DoNothing(), new DoNothing()
        );
    }

    /**
     * Secondary ctor.
     *
     * @param ondelete OnDelete action.
     * @param oncancel OnCancel action.
     */
    public FakeBuild(final Runnable ondelete, final Runnable oncancel) {
        this(
            new XmlResource(FakeBuild.RESOURCE), FakeBuild.defaultNumber(),
            BuildResult.SUCCESS, new Date(),
            new RealBuildDetails(new XmlResource(FakeBuild.RESOURCE)),
            FakeBuild.defaultUrl(), ondelete, oncancel
        );
    }

    /**
     * Primary ctor.
     *
     * @param xml XML content that should be returned in xml() method.
     * @param number Build's number that will be returned in number() method.
     * @param result Fake build's result.
     * @param date Fake build's start date.
     * @param details Fake build's details.
     * @param url Fake build's URL.
     * @param ondelete OnDelete action.
     * @param oncancel OnCancel action.
     * @checkstyle ParameterNumberCheck (500 lines)
     * @todo: Let's fix this multi-parameter constructors and solve issue #284.
     */
    public FakeBuild(
        final Xml xml, final String number, final BuildResult result,
        final Date date, final BuildDetails details, final String url,
        final Runnable ondelete, final Runnable oncancel
    ) {
        this.content = xml;
        this.identifier = number;
        this.status = result;
        this.start = date;
        this.detailed = details;
        this.uri = url;
        this.cbdelete = ondelete;
        this.cbcancel = oncancel;
    }

    /**
     * Fake build's number.
     *
     * @return Build number.
     * @throws Exception If error occurred.
     */
    public String number() throws Exception {
        return this.identifier;
    }

    /**
     * Fake build's result.
     *
     * @return Build's result.
     * @throws Exception If error occurred.
     */
    public BuildResult result() throws Exception {
        return this.status;
    }

    /**
     * Fake build's date.
     *
     * @return Build's date.
     * @throws Exception If error occurred.
     */
    public Date date() throws Exception {
        return this.start;
    }

    /**
     * Fake build's URL.
     *
     * @return URL string.
     * @throws Exception If error occurred.
     */
    public String url() throws Exception {
        return this.uri;
    }

    /**
     * Fake build's details.
     *
     * @return Build's details.
     * @throws Exception If error occurred.
     */
    public BuildDetails details() throws Exception {
        return this.detailed;
    }

    /**
     * Delete build.
     *
     * @throws Exception If error occurred.
     */
    public void delete() throws Exception {
        this.cbdelete.run();
    }

    /**
     * Cancel build.
     *
     * @throws Exception If error occurred.
     */
    public void cancel() throws Exception {
        this.cbcancel.run();
    }

    /**
     * Git revision that is built in this build.
     *
     * @return Git revision hash.
     * @throws Exception If error occurred.
     * @todo: Let's implement this method and resolve issue #261.
     */
    public String gitRevision() throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.gitRevision() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Fake build's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return this.content.content();
    }

    /**
     * Generate random build's number.
     *
     * @return Build's number.
     */
    private static String defaultNumber() {
        return String.format("#%d", System.currentTimeMillis());
    }

    /**
     * Default build URL.
     *
     * @return Build's number.
     */
    private static String defaultUrl() {
        return String.format("http://localhost/%d", System.currentTimeMillis());
    }
}
