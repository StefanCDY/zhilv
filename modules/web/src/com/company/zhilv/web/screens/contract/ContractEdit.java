package com.company.zhilv.web.screens.contract;

import com.company.zhilv.entity.Contract;
import com.company.zhilv.entity.ContractStatus;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;

@UiController("zhilv_Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
@LoadDataBeforeShow
public class ContractEdit extends StandardEditor<Contract> {
    @Inject
    private Notifications notifications;
    @Inject
    private MessageBundle messageBundle;

    @Subscribe
    private void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Contract contract = getEditedEntity();
        Date startDate = contract.getStartDate();
        Date endDate = contract.getEndDate();
        if (startDate.after(endDate)) {
            notifications.create().withCaption(messageBundle.getMessage("contract.startDate.after.endDate")).show();
            event.preventCommit();
            return;
        }
        Date now = new Date();
        if (now.compareTo(startDate) >= 0 && now.compareTo(endDate) <= 0) {
            contract.setStatus(ContractStatus.RUNNING);
        } else if (now.before(startDate)) {
            contract.setStatus(ContractStatus.UNENFORCED);
        } else if (now.after(endDate)) {
            contract.setStatus(ContractStatus.EXPIRED);
        }
    }
}