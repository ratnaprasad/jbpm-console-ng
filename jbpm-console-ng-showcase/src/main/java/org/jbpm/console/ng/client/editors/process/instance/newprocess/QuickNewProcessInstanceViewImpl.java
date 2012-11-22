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
package org.jbpm.console.ng.client.editors.process.instance.newprocess;

import java.util.Collection;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.jbpm.console.ng.client.i18n.Constants;
import org.jbpm.console.ng.shared.model.ProcessSummary;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.widgets.events.NotificationEvent;
import org.uberfire.security.Identity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;

@Dependent
@Templated(value = "QuickNewProcessInstanceViewImpl.html")
public class QuickNewProcessInstanceViewImpl extends Composite
        implements
        QuickNewProcessInstancePresenter.InboxView {

    @Inject
    private Identity identity;
    @Inject
    private PlaceManager placeManager;
    private QuickNewProcessInstancePresenter presenter;
    @Inject
    @DataField
    public Button startProcessInstanceButton;
   
    @DataField
    public SuggestBox processIdText;
    @Inject
    private Event<NotificationEvent> notification;
    
    private MultiWordSuggestOracle oracle;
    
    private Constants constants = GWT.create(Constants.class);
    
    public QuickNewProcessInstanceViewImpl() {
        oracle = new MultiWordSuggestOracle();
        processIdText = new SuggestBox(oracle);
    }

    @Override
    public void init(QuickNewProcessInstancePresenter presenter) {
        this.presenter = presenter;

    }

    @EventHandler("startProcessInstanceButton")
    public void startProcessInstanceButton(ClickEvent e) {
        presenter.startProcessInstance(processIdText.getText());
    }

    public void displayNotification(String text) {
        notification.fire(new NotificationEvent(text));
    }
    
    public void setAvailableProcesses(Collection<ProcessSummary> processes) {
        
        for (ProcessSummary process : processes) {
            oracle.add(process.getId());
        }
    }
}
