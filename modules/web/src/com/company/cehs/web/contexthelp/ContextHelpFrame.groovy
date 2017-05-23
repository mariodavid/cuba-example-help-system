package com.company.cehs.web.contexthelp

import com.company.cehs.entity.ContextHelp
import com.company.cehs.service.ContextHelpService
import com.haulmont.cuba.gui.components.*
import com.haulmont.cuba.web.gui.components.WebComponentsHelper
import com.haulmont.cuba.web.toolkit.ui.CubaHorizontalSplitPanel
import com.haulmont.cuba.web.toolkit.ui.client.split.SplitPanelDockMode

import javax.inject.Inject

class ContextHelpFrame extends AbstractFrame {


    @Inject
    ContextHelpService contextHelpService

    @Inject
    Label generalHelpLabel

    @Inject
    Label keyboardShortcutLabel

    @Inject
    Label userDefinedHelpLabel

    @Inject
    SplitPanel helpSplit

    @Override
    void init(Map<String, Object> params) {
        super.init(params)
        initContextHelp()
    }

    protected void initContextHelp() {
        configureHelpSplit()
        initShowHelpAction()
        loadContextHelp()
    }


    protected void configureHelpSplit() {
        CubaHorizontalSplitPanel vHelpSplitPanel = (CubaHorizontalSplitPanel) WebComponentsHelper.unwrap(helpSplit)
        vHelpSplitPanel.dockable = true
        vHelpSplitPanel.dockMode = SplitPanelDockMode.RIGHT
    }

    protected void initShowHelpAction() {
        AbstractAction showHelpAction = new AbstractAction("showHelpAction") {
            @Override
            void actionPerform(Component component) {
                if (helpSplit.splitPosition == 100) {
                    helpSplit.splitPosition = 75
                } else {
                    helpSplit.splitPosition = 100
                }
            }
        }
        wrappedFrame.addAction(showHelpAction)
    }


    protected void loadContextHelp() {
        ContextHelp contextHelp = contextHelpService.getContextHelpForScreen(frame.id)
        if (contextHelp) {
            generalHelpLabel.value = contextHelp?.generalHelpText
            keyboardShortcutLabel.value = contextHelp?.keyboardShortcutsText
            userDefinedHelpLabel.value = contextHelp?.userDefinedHelpText
        }
    }
}