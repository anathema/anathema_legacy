package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.reporting.common.encoder.IBasicPdfPartEncoder;
import net.sf.anathema.character.reporting.common.encoder.IPdfPageEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.extended.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;

public interface IPdfPartEncoder extends IBasicPdfPartEncoder{

  public IPdfContentBoxEncoder getAnimaEncoder();

  public IPdfContentBoxEncoder getEssenceEncoder();

  public IPdfContentBoxEncoder getGreatCurseEncoder();

  public IPdfContentBoxEncoder getOverdriveEncoder();

  public IPdfContentBoxEncoder getCombatStatsEncoder();

  public IPdfContentBoxEncoder getSocialCombatEncoder();

  public IPdfContentBoxEncoder getIntimaciesEncoder(PdfEncodingRegistry registry);

  public IPdfContentBoxEncoder getHealthAndMovementEncoder();

  public boolean hasMagicPage();

  public float getWeaponryHeight();

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);

  public boolean isEncodeAttributeAsFavorable();
}
