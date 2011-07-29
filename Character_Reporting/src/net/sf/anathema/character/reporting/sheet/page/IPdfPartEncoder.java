package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.IPdfTableEncoder;

public interface IPdfPartEncoder {
  public static final int FONT_SIZE = IVoidStateFormatConstants.FONT_SIZE - 1;
  
  public IPdfContentBoxEncoder getAnimaEncoder();

  public IPdfContentBoxEncoder getEssenceEncoder();
  
  public IPdfContentBoxEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey);

  public IPdfContentBoxEncoder getGreatCurseEncoder();
  
  public IPdfContentBoxEncoder getOverdriveEncoder();

  public IPdfContentBoxEncoder getCombatStatsEncoder();

  public IPdfContentBoxEncoder getSocialCombatEncoder();

  public IPdfContentBoxEncoder getIntimaciesEncoder(PdfEncodingRegistry registry);

  public IPdfContentBoxEncoder getHealthAndMovementEncoder();

  public IPdfContentBoxEncoder getHealthEncoder();

  public IPdfContentBoxEncoder getMovementEncoder();
  
  public boolean hasMagicPage();

  public float getWeaponryHeight();
  
  public IPdfVariableContentBoxEncoder[] getAdditionalFirstPageEncoders();
  
  public IPdfVariableContentBoxEncoder[] getAdditionalMagicSidebarEncoders();
  
  public IPdfTableEncoder[] getAdditionalMagicEncoders();

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);

  public boolean isEncodeAttributeAsFavorable();
}