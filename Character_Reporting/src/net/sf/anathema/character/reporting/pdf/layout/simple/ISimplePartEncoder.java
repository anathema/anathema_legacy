package net.sf.anathema.character.reporting.pdf.layout.simple;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IPdfPageEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;

interface ISimplePartEncoder {

  IBoxContentEncoder getAnimaEncoder();

  IBoxContentEncoder getGreatCurseEncoder();

  IBoxContentEncoder getIntimaciesEncoder(SimpleEncodingRegistry registry);

  IPdfPageEncoder[] getAdditionalPages(PdfPageConfiguration configuration);
}
