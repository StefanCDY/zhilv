package com.company.zhilv.web.screens.agreement;

import com.company.zhilv.entity.Agreement;
import com.company.zhilv.entity.AgreementStatus;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;

@UiController("zhilv_Agreement.edit")
@UiDescriptor("agreement-edit.xml")
@EditedEntityContainer("agreementDc")
@LoadDataBeforeShow
public class AgreementEdit extends StandardEditor<Agreement> {
    @Inject
    private Notifications notifications;
    @Inject
    private MessageBundle messageBundle;

    @Subscribe
    private void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Agreement agreement = getEditedEntity();
        Date startDate = agreement.getStartDate();
        Date endDate = agreement.getEndDate();
        if (startDate.after(endDate)) {
            notifications.create().withCaption(messageBundle.getMessage("agreement.startDate.after.endDate")).show();
            event.preventCommit();
            return;
        }
        Date now = new Date();
        if (now.compareTo(startDate) >= 0 && now.compareTo(endDate) <= 0) {
            agreement.setStatus(AgreementStatus.RUNNING);
        } else if (now.before(startDate)) {
            agreement.setStatus(AgreementStatus.UNENFORCED);
        } else if (now.after(endDate)) {
            agreement.setStatus(AgreementStatus.EXPIRED);
        }
    }
}