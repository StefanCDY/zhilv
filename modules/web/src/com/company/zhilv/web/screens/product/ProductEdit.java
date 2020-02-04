package com.company.zhilv.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.Product;

@UiController("zhilv_Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
@LoadDataBeforeShow
public class ProductEdit extends StandardEditor<Product> {
}