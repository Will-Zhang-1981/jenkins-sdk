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
package com.github.aistomin.jenkins;

import java.util.Iterator;

/**
 * Jenkins' users.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Users extends ApiObject {

    /**
     * Build iterator to run through existing users.
     *
     * @return Users iterator.
     * @throws Exception If error occurred.
     */
    Iterator<User> iterator() throws Exception;

    /**
     * Find by Jenkins' user name.
     *
     * @param username Username(aka ID).
     * @return User.
     * @throws Exception If error occurred.
     */
    Iterator<User> findByUsername(String username) throws Exception;

    /**
     * Find by Jenkins' user email.
     *
     * @param email Username(aka ID).
     * @return Iterator of users who match the criteria.
     * @throws Exception If error occurred.
     */
    Iterator<User> findByEmail(String email) throws Exception;

    /**
     * Find by Jenkins' user full name.
     *
     * @param name Full name or part of it.
     * @return Iterator of users who match the criteria.
     * @throws Exception If error occurred.
     */
    Iterator<User> findByFullName(String name) throws Exception;
}
