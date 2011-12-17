package net.sf.anathema.character.reporting.pdf.layout.extended;

import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.reporting.pdf.rendering.general.LayoutUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;

public interface IExtendedPartEncoder extends LayoutUtilities {

  public IPdfContentBoxEncoder getAnimaEncoder();

  public IPdfContentBoxEncoder getEssenceEncoder();

  public IPdfContentBoxEncoder getDotsEncoder(OtherTraitType trait, int traitMax, String traitHeaderKey);

  public IPdfContentBoxEncoder getGreatCurseEncoder();

  public IPdfContentBoxEncoder getOverdriveEncoder();

  public IPdfContentBoxEncoder getCombatStatsEncoder();

  public IPdfContentBoxEncoder getSocialCombatEncoder();

  public IPdfContentBoxEncoder getIntimaciesEncoder(ExtendedEncodingRegistry registry);

  public IPdfContentBoxEncoder getHealthAndMovementEncoder();

  public IPdfContentBoxEncoder getHealthEncoder();

  public IPdfContentBoxEncoder getMovementEncoder();

  public boolean hasMagicPage();

  public float getWeaponryHeight();

  public IPdfVariableContentBoxEncoder[] getAdditionalFirstPageEncoders();

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);

  public boolean isEncodeAttributeAsFavorable();
}
