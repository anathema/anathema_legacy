package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;

public interface ISimplePartEncoder {

  IBoxContentEncoder getAnimaEncoder();

  IBoxContentEncoder getGreatCurseEncoder();

  IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);
}
