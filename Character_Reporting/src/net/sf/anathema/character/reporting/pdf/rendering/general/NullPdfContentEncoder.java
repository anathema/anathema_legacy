package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;

public class NullPdfContentEncoder implements IBoxContentEncoder {

  private final String headerKey;

  public NullPdfContentEncoder() {
    this("Null"); //$NON-NLS-1$
  }

  public NullPdfContentEncoder(String headerKey) {
    this.headerKey = headerKey;
  }

  public void encode(Graphics graphics, ReportContent reportContent) throws DocumentException {
    // Nothing to do
  }

  public String getHeaderKey(ReportContent reportContent) {
    return headerKey;
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
