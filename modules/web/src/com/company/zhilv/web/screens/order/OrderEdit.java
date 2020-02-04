package com.company.zhilv.web.screens.order;

import com.company.zhilv.entity.Order;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;

@UiController("zhilv_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {
    @Inject
    private TextField<String> logisticsNumberField;
    @Inject
    private DateField<Date> deliveryTimeField;

    @Subscribe
    private void onAfterInit(AfterInitEvent event) {
        Order order = getEditedEntity();
        if (Objects.nonNull(order) && order.getIsDeliver()) {
            logisticsNumberField.setEditable(true);
            deliveryTimeField.setEditable(true);
        } else {
            logisticsNumberField.setEditable(false);
            deliveryTimeField.setEditable(false);
        }
    }

    @Subscribe("isDeliverField")
    private void onIsDeliverFieldValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (Boolean.TRUE.equals(event.getValue())) {
            logisticsNumberField.setEditable(true);
            deliveryTimeField.setEditable(true);
        } else {
            logisticsNumberField.setEditable(false);
            deliveryTimeField.setEditable(false);
        }
    }
}