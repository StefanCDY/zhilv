package com.company.zhilv.web.screens.specification;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.Specification;

@UiController("zhilv_Specification.edit")
@UiDescriptor("specification-edit.xml")
@EditedEntityContainer("specificationDc")
@LoadDataBeforeShow
public class SpecificationEdit extends StandardEditor<Specification> {
}