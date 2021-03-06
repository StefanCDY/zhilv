package com.company.zhilv.web.screens.order;

import com.company.zhilv.entity.*;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.data.options.ContainerOptions;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

import static java.util.Objects.nonNull;

@UiController("zhilv_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {
    @Inject
    private CollectionContainer<Product> productsDc;
    @Inject
    private CollectionContainer<MeasureUnit> measureUnitsDc;
    @Inject
    private CollectionPropertyContainer<OrderItem> orderItemsDc;
    @Inject
    private Table<OrderItem> itemsTable;
    @Inject
    private DateField<Date> deliveryTimeField;
    @Inject
    private TextField<String> logisticsNumberField;
    @Inject
    private Metadata metadata;
    @Inject
    private UiComponents uiComponents;
    @Inject
    private Notifications notifications;
    @Inject
    private MessageBundle messageBundle;
    private Map<UUID, Integer> productMap = new HashMap<>();

    @Subscribe
    private void onInitEntity(InitEntityEvent<Order> event) {
        Order order = event.getEntity();
        OrderItem orderItem = metadata.create(OrderItem.class);
        orderItem.setOrder(order);
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
    }

    @Subscribe(id = "orderItemsDc", target = Target.DATA_CONTAINER)
    private void onOrderItemsDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<OrderItem> event) {
        String property = event.getProperty();
        if ("amount".equals(property) || "unitPrice".equals(property)) {
            OrderItem orderItem = event.getItem();
            BigDecimal amount = orderItem.getAmount();
            BigDecimal unitPrice = orderItem.getUnitPrice();
            if (nonNull(amount) && nonNull(unitPrice)) {
                orderItem.setTotalPrice(amount.multiply(unitPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        } else if ("totalPrice".equals(property)) {
            Order order = getEditedEntity();
            BigDecimal totalPrice = order.getOrderItems().stream().map(OrderItem::getTotalPrice).filter(Objects::nonNull).reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalPrice(totalPrice);
        }
    }

    @Subscribe("contractField")
    private void onContractFieldValueChange(HasValue.ValueChangeEvent<Contract> event) {
        Contract contract = event.getValue();
        if (event.isUserOriginated()) {
            Order order = getEditedEntity();
            Customer customer = contract.getCustomer();
            order.setInvoiceContact(customer.getInvoiceContact());
            order.setInvoicePhone(customer.getInvoicePhone());
            order.setInvoiceAddress(customer.getInvoiceAddress());
            order.setReceiver(customer.getReceiver());
            order.setReceivePhone(customer.getReceivePhone());
            order.setReceiveAddress(customer.getReceiveAddress());
        }
        if (nonNull(contract)) {
            List<Product> productList = productsDc.getMutableItems();
            productList.clear();
            productMap.clear();
            List<ContractItem> contractItems = contract.getContractItems();
            for (int i = 0; i < contractItems.size(); i++) {
                Product product = contractItems.get(i).getProduct();
                productList.add(product);
                productMap.put(product.getId(), i);
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

    @Subscribe
    private void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        long count = orderItemsDc.getMutableItems().stream().map(OrderItem::getProduct).filter(Objects::isNull).count();
        if (count > 0) {
            notifications.create(Notifications.NotificationType.HUMANIZED).withCaption(messageBundle.getMessage("order.orderItem.product.notNull")).show();
            event.preventCommit();
            return;
        }
        Order order = getEditedEntity();
        if (PersistenceHelper.isNew(order)) {
            order.setCode(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now()));
        }
    }

    @Subscribe("itemsTable.add")
    private void onItemsTableAdd(Action.ActionPerformedEvent event) {
        OrderItem item = metadata.create(OrderItem.class);
        item.setOrder(getEditedEntity());
        orderItemsDc.getMutableItems().add(item);
    }

    @Subscribe("itemsTable.remove")
    private void onItemsTableRemove(Action.ActionPerformedEvent event) {
        OrderItem item = itemsTable.getSingleSelected();
        if (nonNull(item)) {
            orderItemsDc.getMutableItems().remove(item);
        }
    }

    public Component generateProductCell(OrderItem item) {
        LookupField<Product> lookupField = uiComponents.create(LookupField.of(Product.class));
        lookupField.setOptions(new ContainerOptions<>(productsDc));
        lookupField.setValue(item.getProduct());
        lookupField.setRequired(true);
        lookupField.setWidthFull();
        lookupField.addValueChangeListener(event -> {
            if (event.isUserOriginated()) {
                Product product = event.getValue();
                Contract contract = getEditedEntity().getContract();
                if (nonNull(contract)) {
                    Integer index = productMap.get(product.getId());
                    ContractItem contractItem = contract.getContractItems().get(index);
                    item.setBatchNumber(contractItem.getBatchNumber());
                    item.setMeasureUnit(contractItem.getMeasureUnit());
                    item.setAmount(contractItem.getAmount());
                    item.setUnitPrice(contractItem.getUnitPrice());
                    item.setProduct(product);
                }
            }
        });
        return lookupField;
    }

    public Component generateMeasureUnitCell(OrderItem item) {
        LookupField<MeasureUnit> lookupField = uiComponents.create(LookupField.of(MeasureUnit.class));
        lookupField.setOptions(new ContainerOptions<>(measureUnitsDc));
        lookupField.setValue(item.getMeasureUnit());
        lookupField.setWidthFull();
        lookupField.addValueChangeListener(event -> {
            if (event.isUserOriginated()) {
                item.setMeasureUnit(event.getValue());
            }
        });
        return lookupField;
    }
}