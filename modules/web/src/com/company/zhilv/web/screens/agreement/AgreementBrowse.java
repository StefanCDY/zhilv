package com.company.zhilv.web.screens.agreement;

import com.company.zhilv.entity.Agreement;
import com.company.zhilv.entity.AgreementStatus;
import com.company.zhilv.service.AgreementService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.DialogAction;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

import static java.util.Objects.nonNull;

@UiController("zhilv_Agreement.browse")
@UiDescriptor("agreement-browse.xml")
@LookupComponent("agreementsTable")
@LoadDataBeforeShow
public class AgreementBrowse extends StandardLookup<Agreement> {
    @Inject
    private CollectionContainer<Agreement> agreementsDc;
    @Inject
    private Table<Agreement> agreementsTable;
    @Inject
    private Dialogs dialogs;
    @Inject
    private Messages messages;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private ExportDisplay exportDisplay;
    @Inject
    private AgreementService agreementService;

    public void showLicense(Agreement entity, String columnId) {
        FileDescriptor agreement = entity.getAgreement();
        if (nonNull(agreement)) {
            exportDisplay.show(agreement);
        }
    }

    @Install(to = "agreementsTable.cancel", subject = "enabledRule")
    private boolean agreementsTableCancelEnabledRule() {
        Agreement agreement = agreementsTable.getSingleSelected();
        if (nonNull(agreement)) {
            AgreementStatus status = agreement.getStatus();
            return status == AgreementStatus.RUNNING || status == AgreementStatus.UNENFORCED;
        }
        return false;
    }

    @Subscribe("agreementsTable.cancel")
    private void onAgreementsTableCancel(Action.ActionPerformedEvent event) {
        Agreement agreement = agreementsTable.getSingleSelected();
        if (nonNull(agreement)) {
            dialogs.createOptionDialog(Dialogs.MessageType.CONFIRMATION)
                    .withCaption(messages.getMainMessage("dialogs.Confirmation"))
                    .withMessage(messageBundle.getMessage("agreement.cancel"))
                    .withActions(
                            new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY)
                                    .withHandler(actionPerformedEvent -> {
                                        Agreement cancelAgreement = agreementService.cancelAgreement(agreement);
                                        agreementsDc.replaceItem(cancelAgreement);
                                    }),
                            new DialogAction(DialogAction.Type.NO))
                    .show();
        }
    }

    @Install(to = "agreementsTable", subject = "styleProvider")
    private String agreementsTableStyleProvider(Agreement entity, String property) {
        if (Objects.equals("status", property)) {
            switch (entity.getStatus()) {
                case RUNNING:
                    return "running";
                case EXPIRED:
                    return "expired";
                case CANCELED:
                    return "canceled";
                case UNENFORCED:
                    return "unenforced";
            }
        }
        return null;
    }

}