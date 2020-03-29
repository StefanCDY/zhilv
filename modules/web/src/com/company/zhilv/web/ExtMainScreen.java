package com.company.zhilv.web;

import com.company.zhilv.bean.SystemInfo;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.mainwindow.AppWorkArea;
import com.haulmont.cuba.gui.components.mainwindow.FoldersPane;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import com.haulmont.cuba.security.app.UserSessionService;
import com.haulmont.cuba.web.WebConfig;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.haulmont.cuba.web.widgets.CubaHorizontalSplitPanel;
import com.vaadin.server.Sizeable;

import javax.inject.Inject;


@UiController("topMenuMainScreen")
@UiDescriptor("ext-main-screen.xml")
public class ExtMainScreen extends MainScreen {
    @Inject
    private SplitPanel foldersSplit;
    @Inject
    private FoldersPane foldersPane;
    @Inject
    private AppWorkArea workArea;
    @Inject
    private WebConfig webConfig;
    @Inject
    private Label<String> sessionLabel;
    @Inject
    private HtmlAttributes htmlAttributes;
    @Inject
    private Messages messages;
    @Inject
    private UserSessionService userSessionService;
    @Inject
    private InstanceContainer<SystemInfo> systemInfoDc;
    @Inject
    private Metadata metadata;
    private final String SESSION_LABEL_CSS_COLOR = "DodgerBlue";

    public ExtMainScreen() {
        addInitListener(this::initLayout);
    }

    protected void initLayout(@SuppressWarnings("unused") InitEvent event) {
        if (webConfig.getFoldersPaneEnabled()) {
            if (webConfig.getFoldersPaneVisibleByDefault()) {
                foldersSplit.setSplitPosition(webConfig.getFoldersPaneDefaultWidth(), SizeUnit.PIXELS);
            } else {
                foldersSplit.setSplitPosition(0);
            }
            CubaHorizontalSplitPanel vSplitPanel = (CubaHorizontalSplitPanel) WebComponentsHelper.unwrap(foldersSplit);
            vSplitPanel.setDefaultPosition(webConfig.getFoldersPaneDefaultWidth() + "px");
            vSplitPanel.setMaxSplitPosition(50, Sizeable.Unit.PERCENTAGE);
            vSplitPanel.setDockable(true);
        } else {
            foldersPane.setEnabled(false);
            foldersPane.setVisible(false);
            foldersSplit.remove(workArea);
            int foldersSplitIndex = getWindow().indexOf(foldersSplit);
            getWindow().remove(foldersSplit);
            getWindow().add(workArea, foldersSplitIndex);
            getWindow().expand(workArea);
        }
        htmlAttributes.setCssProperty(sessionLabel, HtmlAttributes.CSS.COLOR, SESSION_LABEL_CSS_COLOR);
        systemInfoDc.setItem(metadata.create(SystemInfo.class));
        refreshSessionLabel();
    }

    public void onTimerClick(Timer source) {
        refreshSessionLabel();
        systemInfoDc.setItem(metadata.create(SystemInfo.class));
    }

    private void refreshSessionLabel() {
        UserSessionService.Filter filter = UserSessionService.Filter.ALL;
        long sessionNum = userSessionService.loadUserSessionEntities(filter).stream()
                .filter(userSessionEntity -> !userSessionEntity.getSystem()).count();
        String message = messages.formatMainMessage("system_online_number", sessionNum);
        sessionLabel.setValue(message);
    }
}