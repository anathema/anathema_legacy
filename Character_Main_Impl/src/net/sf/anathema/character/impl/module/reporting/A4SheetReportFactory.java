package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.impl.reporting.PageSize;

public class A4SheetReportFactory extends AbstractCharacterReportFactory {

  @Override
  protected PageSize getPageSize() {
    return PageSize.A4;
  }
}