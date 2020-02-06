package com.company.zhilv.web.screens.contract;

import com.company.zhilv.entity.Contract;
import com.company.zhilv.entity.ContractItem;
import com.company.zhilv.entity.ContractStatus;
import com.company.zhilv.entity.Product;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.data.options.ContainerOptions;
import com.haulmont.cuba.gui.components.data.value.ContainerValueSource;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.Objects;

@UiController("zhilv_Contract.edit")
@UiDescriptor("contract-edit.xml")
@EditedEntityContainer("contractDc")
@LoadDataBeforeShow
public class ContractEdit extends StandardEditor<Contract> {
    @Inject
    private CollectionContainer<Product> productsDc;
    @Inject
    private CollectionPropertyContainer<ContractItem> itemsDc;
    @Inject
    private Table<ContractItem> itemsTable;
    @Inject
    private Metadata metadata;
    @Inject
    private UiComponents uiComponents;
    @Inject
    private Notifications notifications;
    @Inject
    private MessageBundle messageBundle;

    public Component generateProductCell(ContractItem item) {
        LookupField<Product> lookupField = uiComponents.create(LookupField.of(Product.class));
        lookupField.setValueSource(new ContainerValueSource<>(itemsDc, "product"));
        lookupField.setOptions(new ContainerOptions<>(productsDc));
        lookupField.setValue(item.getProduct());
        return lookupField;
    }

    @Subscribe("itemsTable.add")
    private void onItemsTableAdd(Action.ActionPerformedEvent event) {
        ContractItem item = metadata.create(ContractItem.class);
        item.setContract(getEditedEntity());
        itemsDc.getMutableItems().add(item);
    }

    @Subscribe("itemsTable.remove")
    private void onItemsTableRemove(Action.ActionPerformedEvent event) {
        ContractItem item = itemsTable.getSingleSelected();
        if (Objects.nonNull(item)) {
            itemsDc.getMutableItems().remove(item);
        }
    }

    @Subscribe
    private void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Contract contract = getEditedEntity();
        Date startDate = contract.getStartDate();
        Date endDate = contract.getEndDate();
        if (startDate.after(endDate)) {
            notifications.create().withCaption(messageBundle.getMessage("contract.startDate.after.endDate")).show();
            event.preventCommit();
            return;
        }
        Date now = new Date();
        if (now.compareTo(startDate) >= 0 && now.compareTo(endDate) <= 0) {
            contract.setStatus(ContractStatus.RUNNING);
        } else if (now.before(startDate)) {
            contract.setStatus(ContractStatus.UNENFORCED);
        } else if (now.after(endDate)) {
            contract.setStatus(ContractStatus.EXPIRED);
        }
    }
}