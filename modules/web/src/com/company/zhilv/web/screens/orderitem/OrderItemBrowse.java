package com.company.zhilv.web.screens.orderitem;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.OrderItem;

@UiController("zhilv_OrderItem.browse")
@UiDescriptor("order-item-browse.xml")
@LookupComponent("orderItemsTable")
@LoadDataBeforeShow
public class OrderItemBrowse extends StandardLookup<OrderItem> {
}