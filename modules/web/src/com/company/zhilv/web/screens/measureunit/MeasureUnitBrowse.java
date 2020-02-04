package com.company.zhilv.web.screens.measureunit;

import com.haulmont.cuba.gui.screen.*;
import com.company.zhilv.entity.MeasureUnit;

@UiController("zhilv_MeasureUnit.browse")
@UiDescriptor("measure-unit-browse.xml")
@LookupComponent("measureUnitsTable")
@LoadDataBeforeShow
public class MeasureUnitBrowse extends StandardLookup<MeasureUnit> {
}