package com.company.zhilv.web.screens.customer;

import com.company.zhilv.entity.Customer;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

import static java.util.Objects.nonNull;

@UiController("zhilv_Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
    @Inject
    private ExportDisplay exportDisplay;

    public void showLicense(Customer entity, String columnId) {
        FileDescriptor licence = entity.getLicence();
        if (nonNull(licence)) {
            exportDisplay.show(licence);
        }
    }
}