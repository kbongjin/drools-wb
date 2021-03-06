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
package org.drools.workbench.screens.scenariosimulation.client.handlers;

import java.util.Objects;

import com.ait.lienzo.client.core.event.AbstractNodeMouseEvent;
import com.ait.lienzo.client.core.types.Point2D;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationGridHeaderUtilities;
import org.uberfire.ext.wires.core.grids.client.model.GridCellEditAction;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.model.GridData;
import org.uberfire.ext.wires.core.grids.client.widget.context.GridBodyCellEditContext;
import org.uberfire.ext.wires.core.grids.client.widget.grid.GridWidget;
import org.uberfire.ext.wires.core.grids.client.widget.grid.impl.DefaultGridWidgetEditCellMouseEventHandler;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.grids.impl.BaseGridRendererHelper;

public class ScenarioSimulationGridWidgetMouseEventHandler extends DefaultGridWidgetEditCellMouseEventHandler {

    @Override
    public boolean handleHeaderCell(final GridWidget gridWidget,
                                    final Point2D relativeLocation,
                                    final int uiHeaderRowIndex,
                                    final int uiHeaderColumnIndex,
                                    final AbstractNodeMouseEvent event) {
        //Get column information
        final double cx = relativeLocation.getX();
        final BaseGridRendererHelper rendererHelper = gridWidget.getRendererHelper();
        final BaseGridRendererHelper.RenderingInformation ri = rendererHelper.getRenderingInformation();
        final BaseGridRendererHelper.ColumnInformation ci = rendererHelper.getColumnInformation(cx);
        final GridColumn<?> column = ci.getColumn();
        if (column == null) {
            return false;
        }
        if (!ScenarioSimulationGridHeaderUtilities.hasEditableHeader(column)) {
            return false;
        }

        if (!ScenarioSimulationGridHeaderUtilities.isEditableHeader(column,
                                                                    uiHeaderRowIndex)) {
            return false;
        }

        //Get rendering information
        final Point2D gridWidgetComputedLocation = gridWidget.getComputedLocation();
        final ScenarioHeaderMetaData headerMetaData = (ScenarioHeaderMetaData) column.getHeaderMetaData().get(uiHeaderRowIndex);
        final GridBodyCellEditContext context = ScenarioSimulationGridHeaderUtilities.makeRenderContext(gridWidget,
                                                                                                        ri,
                                                                                                        ci,
                                                                                                        relativeLocation.add(gridWidgetComputedLocation),
                                                                                                        uiHeaderRowIndex);

        final GridData gridData = gridWidget.getModel();
        if (gridData.getSelectedHeaderCells().size() == 1) {
            if (Objects.equals(headerMetaData.getSupportedEditAction(), GridCellEditAction.getSupportedEditAction(event))) {
                headerMetaData.edit(context);
                return true;
            }
        }

        return false;
    }
}
