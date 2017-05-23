package com.company.cehs.web.contexthelp

import com.haulmont.cuba.core.global.AppBeans
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.company.cehs.entity.ContextHelp
import com.haulmont.cuba.gui.components.Component
import com.haulmont.cuba.gui.components.FieldGroup
import com.haulmont.cuba.gui.components.LookupField
import com.haulmont.cuba.gui.config.WindowConfig
import com.haulmont.cuba.gui.config.WindowInfo
import com.haulmont.cuba.gui.data.Datasource
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory

import javax.inject.Inject;

public class ContextHelpEdit extends AbstractEditor<ContextHelp> {

    @Inject
    protected FieldGroup fieldGroup

    @Inject
    protected ComponentsFactory componentsFactory

    @Override
    protected void postInit() {

        fieldGroup.addCustomField('screenId', new FieldGroup.CustomFieldGenerator() {
            @Override
            Component generateField(Datasource datasource, String propertyId) {
                LookupField lookupField = componentsFactory.createComponent(LookupField.NAME)

                if (PersistenceHelper.isNew(item) && item.screenId == null) {
                    lookupField.enabled = true
                }
                else {
                    lookupField.enabled = false
                }
                lookupField.optionsMap = createScreenIdOptions()
                lookupField.setDatasource(datasource, propertyId)
                lookupField.nullOptionVisible = false

                lookupField
            }
        })
    }

    protected LinkedHashMap createScreenIdOptions() {
        def screenOptions = [:]
        WindowConfig windowConfig = AppBeans.get(WindowConfig)
        windowConfig.windows.each { WindowInfo windowInfo ->
            screenOptions."${windowInfo.id}" = windowInfo.id

        }
        screenOptions
    }
}