package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionLunarPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public FirstEditionLunarPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  @Override
  protected int getAnimaPowerCount() {
    return 4;
  }

  @Override
  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new LunarAnimaTableEncoder(getResources(), getBaseFont(), getSymbolBaseFont(), getFontSize());
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new LunarGreatCurseEncoder(getBaseFont(), getSymbolBaseFont(), getResources());
  }
}