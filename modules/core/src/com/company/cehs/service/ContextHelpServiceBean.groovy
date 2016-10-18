package com.company.cehs.service

import com.company.cehs.entity.ContextHelp
import com.haulmont.cuba.core.global.DataManager
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Service

import javax.inject.Inject;

@Service(ContextHelpService.NAME)
public class ContextHelpServiceBean implements ContextHelpService {

    @Inject
    DataManager dataManager

    @Override
    ContextHelp getContextHelpForScreen(String screenId) {
        LoadContext loadContext = LoadContext.create(ContextHelp)
                .setQuery(LoadContext.createQuery('select e from cehs$ContextHelp e where e.screenId = :screenId')
                .setParameter("screenId", screenId)
        )

        return dataManager.load(loadContext)

    }
}