package com.company.zhilv.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.Product;

@UiController("zhilv_Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
}