package com.company.zhilv.web.screens.agreement;

import com.company.zhilv.entity.Agreement;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

import static java.util.Objects.nonNull;

@UiController("zhilv_Agreement.browse")
@UiDescriptor("agreement-browse.xml")
@LookupComponent("agreementsTable")
@LoadDataBeforeShow
public class AgreementBrowse extends StandardLookup<Agreement> {
    @Inject
    private ExportDisplay exportDisplay;

    public void showLicense(Agreement entity, String columnId) {
        FileDescriptor agreement = entity.getAgreement();
        if (nonNull(agreement)) {
            exportDisplay.show(agreement);
        }
    }
}