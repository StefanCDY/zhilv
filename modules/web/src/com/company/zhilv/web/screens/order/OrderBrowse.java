package com.company.zhilv.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.Order;

@UiController("zhilv_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {
}