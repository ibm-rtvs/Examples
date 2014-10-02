/**
 * Copyright 2014 IBM Corp.
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
package com.ibm.greenhat.examples.stubreporter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a domain in RTCP with child environments
 */
public class Domain {

    private final String name;
    private final List<Environment> environments;

    public Domain(final String name) {
        this.name = name;
        environments = new ArrayList<Environment>();
    }

    public String getName() {
        return name;
    }

    public List<Environment> getEnvironments() {
        return environments;
    }

    public Environment addEnvironment(final Environment environment) {
        final int indexOf = environments.indexOf(environment);
        if (indexOf != -1) {
            return environments.get(indexOf);
        }
        environments.add(environment);
        return environment;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domain other = (Domain) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}