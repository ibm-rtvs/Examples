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
package com.ibm.greenhat.rtcp.stub.reporter.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Stub as viewed in the "Start Stub" dialog in RTCP, has child instances.
 */
public class StubDefinition {

    private final String component;
    private final String operation;
    private final String name;
    private final String version;
    private final List<StubInstance> instances;

    public StubDefinition(final String component, final String operation, final String name, final String version) {
        this.component = component;
        this.operation = operation;
        this.name = name;
        this.version = version;
        instances = new ArrayList<>();
    }

    public String getComponent() {
        return component;
    }

    public String getOperation() {
        return operation;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public List<StubInstance> getInstances() {
        return instances;
    }

    public void addInstance(final StubInstance instance) {
        instances.add(instance);
    }
}
