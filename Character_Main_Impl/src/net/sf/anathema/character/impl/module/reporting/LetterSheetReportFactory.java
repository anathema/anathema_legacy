package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.impl.reporting.PageSize;

public class LetterSheetReportFactory extends AbstractCharacterReportFactory {

  @Override
  protected PageSize getPageSize() {
    return PageSize.Letter;
  }
}