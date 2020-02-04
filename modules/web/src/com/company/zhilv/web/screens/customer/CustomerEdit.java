package com.company.zhilv.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.Customer;

@UiController("zhilv_Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
@LoadDataBeforeShow
public class CustomerEdit extends StandardEditor<Customer> {
}