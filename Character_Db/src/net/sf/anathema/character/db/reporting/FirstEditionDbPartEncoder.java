package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.anima.AnimaTableEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionDbPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public FirstEditionDbPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new FirstEditionDbGreatCurseEncoder(getBaseFont(), getResources());
  }

  @Override
  protected int getAnimaPowerCount() {
    return 4;
  }

  @Override
  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new AnimaTableEncoder(getResources(), getBaseFont(), getFontSize(), new DbAnimaTableRangeProvider());
  }
}