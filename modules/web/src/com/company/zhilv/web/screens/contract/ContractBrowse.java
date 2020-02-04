package com.company.zhilv.web.screens.contract;

import com.company.zhilv.entity.Contract;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

import static java.util.Objects.nonNull;

@UiController("zhilv_Contract.browse")
@UiDescriptor("contract-browse.xml")
@LookupComponent("contractsTable")
@LoadDataBeforeShow
public class ContractBrowse extends StandardLookup<Contract> {
    @Inject
    private ExportDisplay exportDisplay;

    public void showContract(Contract entity, String columnId) {
        FileDescriptor contract = entity.getContract();
        if (nonNull(contract)) {
            exportDisplay.show(contract);
        }
    }
}