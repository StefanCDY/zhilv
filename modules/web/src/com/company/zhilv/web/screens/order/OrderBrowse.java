package com.company.zhilv.web.screens.order;

import com.company.zhilv.entity.Order;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

@UiController("zhilv_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {
    @Inject
    private Table<Order> ordersTable;
    @Inject
    private Metadata metadata;
    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe("ordersTable.copy")
    private void onOrdersTableCopy(Action.ActionPerformedEvent event) {
        Order order = ordersTable.getSingleSelected();
        if (Objects.nonNull(order)) {
            Order newOrder = metadata.create(Order.class);
            newOrder.setTotalPrice(order.getTotalPrice());
//            newOrder.setAmount(order.getAmount());
            newOrder.setCargoOutNumber(order.getCargoOutNumber());
            newOrder.setContract(order.getContract());
//            newOrder.setCustomer(order.getCustomer());
            newOrder.setDeliveryAddress(order.getDeliveryAddress());
            newOrder.setDeliveryTime(order.getDeliveryTime());
            newOrder.setDeliveryType(order.getDeliveryType());
            newOrder.setInvoiceAddress(order.getInvoiceAddress());
            newOrder.setInvoiceContact(order.getInvoiceContact());
            newOrder.setInvoiceNumber(order.getInvoiceNumber());
            newOrder.setInvoicePhone(order.getInvoicePhone());
            newOrder.setInvoiceType(order.getInvoiceType());
            newOrder.setIsDeliver(order.getIsDeliver());
            newOrder.setLogisticsNumber(order.getLogisticsNumber());
//            newOrder.setMeasureUnit(order.getMeasureUnit());
            newOrder.setMemo(order.getMemo());
            newOrder.setOrderDate(order.getOrderDate());
//            newOrder.setProduct(order.getProduct());
            newOrder.setReceiveAddress(order.getReceiveAddress());
            newOrder.setReceivePhone(order.getReceivePhone());
            newOrder.setReceiver(order.getReceiver());
            newOrder.setRequirement(order.getRequirement());
            newOrder.setReturnDate(order.getReturnDate());
            newOrder.setReturnType(order.getReturnType());
//            newOrder.setUnitPrice(order.getUnitPrice());
            screenBuilders.editor(ordersTable).editEntity(newOrder).build().show();
        }
    }
}