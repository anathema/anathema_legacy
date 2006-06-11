package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;

public interface IPdfPartEncoder {
  
  public IPdfContentEncoder getEssenceEncoder();

  public boolean hasSecondPage();
}