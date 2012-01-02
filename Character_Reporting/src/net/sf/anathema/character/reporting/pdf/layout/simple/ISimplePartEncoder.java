package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.layout.extended.IWeaponryPartEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;

public interface ISimplePartEncoder extends IWeaponryPartEncoder {

  public IBoxContentEncoder getAnimaEncoder();

  public IBoxContentEncoder getEssenceEncoder();

  public IBoxContentEncoder getGreatCurseEncoder();

  public IBoxContentEncoder getCombatStatsEncoder();

  public IBoxContentEncoder getSocialCombatEncoder();

  public IBoxContentEncoder getIntimaciesEncoder(SimpleEncodingRegistry registry);

  public IBoxContentEncoder getHealthAndMovementEncoder();

  public boolean hasSecondPage();

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);

  public boolean isEncodeAttributeAsFavorable();
}
