package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;

public interface ISimplePartEncoder {

  ContentEncoder getAnimaEncoder();

  ContentEncoder getGreatCurseEncoder();

  PageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);
}
