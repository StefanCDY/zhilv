package com.company.zhilv.web.screens.contract;

import com.company.zhilv.entity.Contract;
import com.company.zhilv.entity.ContractStatus;
import com.company.zhilv.service.ContractService;
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

@UiController("zhilv_Contract.browse")
@UiDescriptor("contract-browse.xml")
@LookupComponent("contractsTable")
@LoadDataBeforeShow
public class ContractBrowse extends StandardLookup<Contract> {
    @Inject
    private CollectionContainer<Contract> contractsDc;
    @Inject
    private Table<Contract> contractsTable;
    @Inject
    private Dialogs dialogs;
    @Inject
    private Messages messages;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private ExportDisplay exportDisplay;
    @Inject
    private ContractService contractService;

    public void showContract(Contract entity, String columnId) {
        FileDescriptor contract = entity.getContract();
        if (nonNull(contract)) {
            exportDisplay.show(contract);
        }
    }

    @Install(to = "contractsTable.cancel", subject = "enabledRule")
    private boolean contractsTableCancelEnabledRule() {
        Contract contract = contractsTable.getSingleSelected();
        if (nonNull(contract)) {
            ContractStatus status = contract.getStatus();
            return status == ContractStatus.RUNNING || status == ContractStatus.UNENFORCED;
        }
        return false;
    }

    @Subscribe("contractsTable.cancel")
    private void onContractsTableCancel(Action.ActionPerformedEvent event) {
        Contract contract = contractsTable.getSingleSelected();
        if (nonNull(contract)) {
            dialogs.createOptionDialog(Dialogs.MessageType.CONFIRMATION)
                    .withCaption(messages.getMainMessage("dialogs.Confirmation"))
                    .withMessage(messageBundle.getMessage("contract.cancel"))
                    .withActions(
                            new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY)
                                    .withHandler(actionPerformedEvent -> {
                                        Contract cancelContract = contractService.cancelContract(contract);
                                        contractsDc.replaceItem(cancelContract);
                                    }),
                            new DialogAction(DialogAction.Type.NO)
                    ).show();
        }
    }

    @Install(to = "contractsTable", subject = "styleProvider")
    private String contractsTableStyleProvider(Contract entity, String property) {
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