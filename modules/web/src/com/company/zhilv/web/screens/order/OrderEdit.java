package com.company.zhilv.web.screens.order;

import com.company.zhilv.entity.Order;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Date;

import static java.util.Objects.nonNull;

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
        if (nonNull(order) && order.getIsDeliver()) {
            logisticsNumberField.setEditable(true);
            deliveryTimeField.setEditable(true);
        } else {
            logisticsNumberField.setEditable(false);
            deliveryTimeField.setEditable(false);
        }
    }

    @Subscribe("amountField")
    private void onAmountFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (!event.isUserOriginated()) {
            Order order = getEditedEntity();
            BigDecimal amount = event.getValue();
            BigDecimal unitPrice = order.getUnitPrice();
            if (nonNull(unitPrice)) {
                order.setTotalPrice(amount.multiply(unitPrice));
            }
        }
    }

    @Subscribe("unitPriceField")
    private void onUnitPriceFieldValueChange(HasValue.ValueChangeEvent<BigDecimal> event) {
        if (!event.isUserOriginated()) {
            Order order = getEditedEntity();
            BigDecimal unitPrice = event.getValue();
            BigDecimal amount = order.getAmount();
            if (nonNull(amount)) {
                order.setTotalPrice(unitPrice.multiply(amount));
            }
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