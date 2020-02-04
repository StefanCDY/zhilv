package com.company.zhilv.web.screens.specification;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.Specification;

@UiController("zhilv_Specification.browse")
@UiDescriptor("specification-browse.xml")
@LookupComponent("specificationsTable")
@LoadDataBeforeShow
public class SpecificationBrowse extends StandardLookup<Specification> {
}