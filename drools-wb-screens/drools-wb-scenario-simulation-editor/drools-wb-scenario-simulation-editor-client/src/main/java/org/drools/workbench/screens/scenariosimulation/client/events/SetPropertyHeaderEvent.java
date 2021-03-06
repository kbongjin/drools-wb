/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.scenariosimulation.client.events;

import com.google.gwt.event.shared.GwtEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.SetPropertyHeaderEventHandler;

/**
 * <code>GwtEvent</code> to set the <i>property</i> level header for a given column
 */
public class SetPropertyHeaderEvent extends GwtEvent<SetPropertyHeaderEventHandler> {

    public static Type<SetPropertyHeaderEventHandler> TYPE = new Type<>();

    private String fullPackage;
    private String value;
    private String valueClassName;

    /**
     * Use this constructor to modify the <i>property</i> level header
     *
     * @param fullPackage
     * @param value
     * @param valueClassName
     */
    public SetPropertyHeaderEvent(String fullPackage, String value, String valueClassName) {
        this.fullPackage = fullPackage;
        this.value = value;
        this.valueClassName = valueClassName;
    }

    @Override
    public Type<SetPropertyHeaderEventHandler> getAssociatedType() {
        return TYPE;
    }

    public String getFullPackage() {
        return fullPackage;
    }

    public String getValue() {
        return value;
    }

    public String getValueClassName() {
        return valueClassName;
    }

    @Override
    protected void dispatch(SetPropertyHeaderEventHandler handler) {
        handler.onEvent(this);
    }
}
