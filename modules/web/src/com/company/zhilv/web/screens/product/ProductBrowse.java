package com.company.zhilv.web.screens.product;

import com.company.zhilv.entity.Product;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.Objects;

@UiController("zhilv_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
    @Inject
    private Table<Product> productsTable;
    @Inject
    private Metadata metadata;
    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe("productsTable.copy")
    private void onProductsTableCopy(Action.ActionPerformedEvent event) {
        Product product = productsTable.getSingleSelected();
        if (Objects.nonNull(product)) {
            Product newProduct = metadata.create(Product.class);
            newProduct.setName(product.getName());
            newProduct.setSpecification(product.getSpecification());
            newProduct.setNumber(product.getNumber());
            newProduct.setPacking(product.getPacking());
            newProduct.setPlace(product.getPlace());
            newProduct.setManufactureDate(product.getManufactureDate());
            newProduct.setMemo(product.getMemo());
            screenBuilders.editor(productsTable).editEntity(newProduct).build().show();
        }
    }
}