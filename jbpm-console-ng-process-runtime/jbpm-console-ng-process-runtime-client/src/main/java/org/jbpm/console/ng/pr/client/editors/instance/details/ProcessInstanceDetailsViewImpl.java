/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.console.ng.pr.client.editors.instance.details;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jbpm.console.ng.pr.client.i18n.Constants;
import org.jbpm.console.ng.pr.client.resources.ProcessRuntimeImages;
import org.jbpm.console.ng.pr.model.NodeInstanceSummary;
import org.jbpm.console.ng.pr.model.ProcessInstanceSummary;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.security.Identity;
import org.uberfire.workbench.events.NotificationEvent;

@Dependent
@Templated(value = "ProcessInstanceDetailsViewImpl.html")
public class ProcessInstanceDetailsViewImpl extends Composite implements
                                                              ProcessInstanceDetailsPresenter.ProcessInstanceDetailsView {

    private ProcessInstanceDetailsPresenter presenter;

    @Inject
    @DataField
    public HTML processDefinitionIdText;
    
    
    @Inject
    @DataField
    public Label processInstanceIdLabel;
    
    @Inject
    @DataField
    public HTML processInstanceIdText;

    @Inject
    @DataField
    public HTML processNameText;

    @Inject
    @DataField
    public HTML processDeploymentText;

    @Inject
    @DataField
    public HTML processVersionText;

    @Inject
    @DataField
    public HTML stateText;

    @Inject
    @DataField
    public HTML currentActivitiesListBox;

    @Inject
    @DataField
    public HTML logTextArea;

    @Inject
    @DataField
    public Label processDefinitionIdLabel;

    @Inject
    @DataField
    public Label processNameLabel;

    @Inject
    @DataField
    public Label processDeploymentLabel;

    @Inject
    @DataField
    public Label processVersionLabel;

    @Inject
    @DataField
    public Label stateLabel;

    @Inject
    @DataField
    public Label currentActivitiesListLabel;

    @Inject
    @DataField
    public Label logTextLabel;

    @Inject
    private Identity identity;

    @Inject
    private PlaceManager placeManager;


    @Inject
    private Event<NotificationEvent> notification;

    private Constants constants = GWT.create( Constants.class );
    private ProcessRuntimeImages images = GWT.create( ProcessRuntimeImages.class );
    private ProcessInstanceSummary processInstance;
    private Path processAssetPath;
    private String encodedProcessSource;
    private List<NodeInstanceSummary> activeNodes;
    private List<NodeInstanceSummary> completedNodes;

  
    
    @Override
    public void init( final ProcessInstanceDetailsPresenter presenter ) {
        this.presenter = presenter;
        
        

        processNameLabel.setText( constants.Process_Definition_Name() );
        processDefinitionIdLabel.setText( constants.Process_Definition_Id() );
        processInstanceIdLabel.setText(constants.Process_Instance_ID());
        processDeploymentLabel.setText( constants.Deployment_Name() );
        processVersionLabel.setText( constants.Process_Definition_Version() );
        stateLabel.setText( constants.Process_Instance_State() );
        currentActivitiesListLabel.setText( constants.Current_Activities() );
        logTextLabel.setText( constants.Process_Instance_Log() );


    }


    @Override
    public HTML getProcessDefinitionIdText() {
        return processDefinitionIdText;
    }

    @Override
    public HTML getCurrentActivitiesListBox() {
        return currentActivitiesListBox;
    }

    @Override
    public HTML getLogTextArea() {
        return logTextArea;
    }

    @Override
    public void displayNotification( String text ) {
        notification.fire( new NotificationEvent( text ) );
    }

    @Override
    public HTML getProcessNameText() {
        return processNameText;
    }

    

    @Override
    public HTML getProcessInstanceIdText() {
        return this.processInstanceIdText;
    }


    @Override
    public void setProcessInstance( ProcessInstanceSummary processInstance ) {
        this.processInstance = processInstance;
    }

    @Override
    public HTML getStateText() {
        return this.stateText;
    }

    @Override
    public HTML getProcessDeploymentText() {
        return processDeploymentText;
    }

    @Override
    public HTML getProcessVersionText() {
        return processVersionText;
    }

    @Override
    public void setProcessAssetPath( Path processAssetPath ) {
        this.processAssetPath = processAssetPath;
    }

    @Override
    public void setCurrentActiveNodes( List<NodeInstanceSummary> activeNodes ) {
        this.activeNodes = activeNodes;

    }

    @Override
    public void setCurrentCompletedNodes( List<NodeInstanceSummary> completedNodes ) {
        this.completedNodes = completedNodes;
    }

    @Override
    public void setEncodedProcessSource( String encodedProcessSource ) {
        this.encodedProcessSource = encodedProcessSource;
    }

    public List<NodeInstanceSummary> getActiveNodes() {
        return activeNodes;
    }

    public void setActiveNodes(List<NodeInstanceSummary> activeNodes) {
        this.activeNodes = activeNodes;
    }

    public List<NodeInstanceSummary> getCompletedNodes() {
        return completedNodes;
    }

    public void setCompletedNodes(List<NodeInstanceSummary> completedNodes) {
        this.completedNodes = completedNodes;
    }

    public Path getProcessAssetPath() {
        return processAssetPath;
    }

    public String getEncodedProcessSource() {
        return encodedProcessSource;
    }

    
    
}
