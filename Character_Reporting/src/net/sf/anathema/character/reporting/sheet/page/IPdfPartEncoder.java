package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;

public interface IPdfPartEncoder {

  public IPdfContentEncoder getAnimaEncoder();

  public IPdfContentEncoder getEssenceEncoder();

  public IPdfContentEncoder getGreatCurseEncoder();

  public IPdfContentEncoder getCombatStatsEncoder();

  public IPdfContentEncoder getSocialCombatEncoder();

  public IPdfContentEncoder getIntimaciesEncoder(PdfEncodingRegistry registry);

  public IPdfContentEncoder getHealthAndMovementEncoder();

  public boolean hasSecondPage();
}