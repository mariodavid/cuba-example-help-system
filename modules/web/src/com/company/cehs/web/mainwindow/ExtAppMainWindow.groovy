package com.company.cehs.web.mainwindow

import com.company.cehs.entity.ContextHelp;
import com.company.cehs.service.ContextHelpService;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.client.ClientConfig;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.FtsConfigHelper;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.app.core.dev.LayoutAnalyzer;
import com.haulmont.cuba.gui.app.core.dev.LayoutTip;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.mainwindow.AppMenu;
import com.haulmont.cuba.gui.components.mainwindow.AppWorkArea;
import com.haulmont.cuba.gui.components.mainwindow.FoldersPane;
import com.haulmont.cuba.gui.components.mainwindow.FtsField;
import com.haulmont.cuba.web.WebConfig;
import com.haulmont.cuba.web.app.mainwindow.AppMainWindow;
import com.haulmont.cuba.web.gui.components.WebComponentsHelper;
import com.haulmont.cuba.web.toolkit.ui.CubaHorizontalSplitPanel;
import com.haulmont.cuba.web.toolkit.ui.client.split.SplitPanelDockMode;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ClientConnector;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import org.apache.commons.lang.StringUtils;
import org.vaadin.peter.contextmenu.ContextMenu;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class ExtAppMainWindow extends AbstractMainWindow {



    @Inject
    protected AppMenu mainMenu;

    @Inject
    protected AppWorkArea workArea;

    @Inject
    protected BoxLayout titleBar;

    @Inject
    protected FoldersPane foldersPane;

    @Inject
    protected SplitPanel foldersSplit;

    @Inject
    protected SplitPanel helpSplit;

    @Inject
    protected FtsField ftsField;

    @Inject
    protected Embedded logoImage;

    @Inject
    protected WebConfig webConfig;

    @Inject
    protected Configuration configuration;

    @Inject
    protected ContextHelpService contextHelpService;

    @Inject
    Label generalHelpLabel

    @Inject
    Label keyboardShortcutLabel

    @Inject
    Label userDefinedHelpLabel

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        mainMenu.requestFocus();

        String logoImagePath = messages.getMainMessage("application.logoImage");
        if (StringUtils.isNotBlank(logoImagePath) && !"application.logoImage".equals(logoImagePath)) {
            logoImage.setSource("theme://" + logoImagePath);
        }

        ClientConfig clientConfig = configuration.getConfig(ClientConfig.class);
        if (clientConfig.getLayoutAnalyzerEnabled()) {
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.setOpenAutomatically(true);
            contextMenu.setAsContextMenuOf(logoImage.unwrap(com.vaadin.ui.AbstractComponent.class));
            ContextMenu.ContextMenuItem analyzeLayout = contextMenu.addItem(messages.getMainMessage("actions.analyzeLayout"));

            analyzeLayout.addItemClickListener(new ContextMenu.ContextMenuItemClickListener() {
                @Override
                void contextMenuItemClicked(ContextMenu.ContextMenuItemClickEvent contextMenuItemClickEvent) {
                    LayoutAnalyzer analyzer = new LayoutAnalyzer();
                    List<LayoutTip> tipsList = analyzer.analyze(this);

                    if (tipsList.isEmpty()) {
                        showNotification("No layout problems found", Frame.NotificationType.HUMANIZED);
                    } else {
                        openWindow("layoutAnalyzer", WindowManager.OpenType.DIALOG, ParamsMap.of("tipsList", tipsList));
                    }
                }
            });
        }

        if (webConfig.getUseInverseHeader()) {
            titleBar.setStyleName("cuba-app-menubar cuba-inverse-header");
        }

        if (!FtsConfigHelper.getEnabled()) {
            ftsField.setVisible(false);
        }

        if (webConfig.getFoldersPaneEnabled()) {
            if (webConfig.getFoldersPaneVisibleByDefault()) {
                foldersSplit.setSplitPosition(webConfig.getFoldersPaneDefaultWidth(), Component.UNITS_PIXELS);
            } else {
                foldersSplit.setSplitPosition(0);
                helpSplit.setSplitPosition(0);
            }

            CubaHorizontalSplitPanel vSplitPanel = (CubaHorizontalSplitPanel) WebComponentsHelper.unwrap(foldersSplit);
            vSplitPanel.setDefaultPosition(webConfig.getFoldersPaneDefaultWidth() + "px");
            vSplitPanel.setMaxSplitPosition(50, Sizeable.Unit.PERCENTAGE);
            vSplitPanel.setDockable(true);

            CubaHorizontalSplitPanel vHelpSplitPanel = (CubaHorizontalSplitPanel) WebComponentsHelper.unwrap(helpSplit);
            vHelpSplitPanel.setDefaultPosition(webConfig.getFoldersPaneDefaultWidth() + "px");
            vSplitPanel.setMaxSplitPosition(25, Sizeable.Unit.PERCENTAGE);
            vHelpSplitPanel.setDockable(true);
            vHelpSplitPanel.setDockMode(SplitPanelDockMode.RIGHT);

            vHelpSplitPanel.addListener(new com.vaadin.ui.Component.Listener() {
                @Override
                public void componentEvent(com.vaadin.ui.Component.Event event) {
                    System.out.println("hallo");
                }
            });
            vHelpSplitPanel.addSplitterClickListener(new AbstractSplitPanel.SplitterClickListener() {
                @Override
                public void splitterClick(AbstractSplitPanel.SplitterClickEvent event) {
                    System.out.println("hallo");
                }
            });

            vHelpSplitPanel.addDetachListener(new ClientConnector.DetachListener() {
                @Override
                public void detach(ClientConnector.DetachEvent event) {
                    System.out.println("hallo");
                }
            });

            AbstractAction showHelpAction = new AbstractAction("showHelpAction") {
                @Override
                public void actionPerform(Component component) {

                    if (helpSplit.getSplitPosition() == 100) {


                        ContextHelp contextHelp = contextHelpService.getContextHelpForScreen(getWindowManager().getOpenWindows()[0]?.id)


                        if(contextHelp) {
                            generalHelpLabel.setValue(contextHelp?.generalHelpText)
                            keyboardShortcutLabel.setValue(contextHelp?.keyboardShortcutsText)
                            userDefinedHelpLabel.setValue(contextHelp?.userDefinedHelpText)

                        }
                        helpSplit.setSplitPosition(75);
                    }
                    else {
                        helpSplit.setSplitPosition(100);
                    }

                }
            };

            showHelpAction.setShortcut("F1");
            addAction(showHelpAction);

        } else {
            foldersPane.setEnabled(false);
            foldersPane.setVisible(false);

            foldersSplit.remove(workArea);

            int foldersSplitIndex = indexOf(foldersSplit);

            remove(foldersSplit);
            add(workArea, foldersSplitIndex);

            expand(workArea);
        }
    }

}
