package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.common.encoder.IBasicPdfPartEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfPageEncoder;
import net.sf.anathema.character.reporting.common.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.SimpleEncodingRegistry;

public interface ISimplePartEncoder extends IBasicPdfPartEncoder{

  public IPdfContentBoxEncoder getAnimaEncoder();

  public IPdfContentBoxEncoder getEssenceEncoder();

  public IPdfContentBoxEncoder getGreatCurseEncoder();

  public IPdfContentBoxEncoder getOverdriveEncoder();

  public IPdfContentBoxEncoder getCombatStatsEncoder();

  public IPdfContentBoxEncoder getSocialCombatEncoder();

  public IPdfContentBoxEncoder getIntimaciesEncoder(SimpleEncodingRegistry registry);

  public IPdfContentBoxEncoder getHealthAndMovementEncoder();

  public boolean hasSecondPage();

  public float getWeaponryHeight();

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);

  public boolean isEncodeAttributeAsFavorable();
}
