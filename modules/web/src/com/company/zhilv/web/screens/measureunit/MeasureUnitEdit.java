package com.company.zhilv.web.screens.measureunit;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.MeasureUnit;

@UiController("zhilv_MeasureUnit.edit")
@UiDescriptor("measure-unit-edit.xml")
@EditedEntityContainer("measureUnitDc")
@LoadDataBeforeShow
public class MeasureUnitEdit extends StandardEditor<MeasureUnit> {
}