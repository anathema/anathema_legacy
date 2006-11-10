package net.sf.anathema.character.db.reporting;

import net.sf.anathema.character.reporting.sheet.common.anima.PdfAnimaEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class DbPdfAnimaEncoder extends PdfAnimaEncoder {

  public DbPdfAnimaEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont, int fontSize) {
    super(resources, baseFont, symbolBaseFont, fontSize, new DbAnimaTableEncoder(resources, baseFont, fontSize));
  }

  @Override
  protected String[] getResourceIds() {
    return new String[] { "First", "Second", "Third", "Fourth" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  }
}