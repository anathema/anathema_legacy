package net.sf.anathema.character.abyssal.reporting;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.page.AbstractFirstEditionExaltPdfPartEncoder;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionAbyssalPartEncoder extends AbstractFirstEditionExaltPdfPartEncoder {

  public FirstEditionAbyssalPartEncoder(IResources resources, PdfEncodingRegistry registry, int essenceMax) {
    super(resources, registry, essenceMax);
  }

  public IPdfContentBoxEncoder getGreatCurseEncoder() {
    return new AbyssalResonanceEncoder(getBaseFont(), getSymbolBaseFont(), getResources());
  }

  @Override
  protected int getAnimaPowerCount() {
    return 4;
  }

  @Override
  protected IPdfTableEncoder getAnimaTableEncoder() {
    return new AbyssalAnimaTableEncoder(getResources(), getBaseFont(), getFontSize());
  }
}