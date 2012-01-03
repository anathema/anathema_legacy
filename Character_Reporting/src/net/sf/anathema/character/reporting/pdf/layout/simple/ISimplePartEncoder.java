package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;

public interface ISimplePartEncoder {

  public IBoxContentEncoder getAnimaEncoder();

  public IBoxContentEncoder getGreatCurseEncoder();

  public IBoxContentEncoder getSocialCombatEncoder();

  public IBoxContentEncoder getIntimaciesEncoder(SimpleEncodingRegistry registry);

  public IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);
}
