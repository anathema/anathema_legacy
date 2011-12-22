package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.Anchor;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.PdfPageConfiguration;

import static net.sf.anathema.character.reporting.pdf.rendering.graphics.HorizontalAlignment.Center;
import static net.sf.anathema.character.reporting.pdf.rendering.graphics.HorizontalAlignment.Right;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.FONT_SIZE;

public class CopyrightEncoder {

  private PdfPageConfiguration pageConfiguration;
  private int contentHeight;

  public CopyrightEncoder(PdfPageConfiguration pageConfiguration, int contentHeight) {
    this.pageConfiguration = pageConfiguration;
    this.contentHeight = contentHeight;
  }

   // TODO: Eliminate these hard-coded copyright dates; these should be in a properties file or something.
  public void encodeCopyright(SheetGraphics graphics) throws DocumentException {
    Font copyrightFont = graphics.createCommentFont();
    float copyrightHeight = pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
    Bounds firstColumnBounds = pageConfiguration.getFirstColumnRectangle(contentHeight, copyrightHeight, 1);
    Anchor voidStatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidStatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    graphics.createSimpleColumn(firstColumnBounds).withLeading((float) FONT_SIZE).andTextPart(voidStatePhrase).encode();
    Anchor anathemaPhrase = new Anchor("Created with Anathema \u00A92007-2012\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds = pageConfiguration.getSecondColumnRectangle(contentHeight, copyrightHeight, 1);
    graphics.createSimpleColumn(anathemaBounds).withLeading(FONT_SIZE).andAlignment(Center).andTextPart(anathemaPhrase).encode();
    Anchor whiteWolfPhrase = new Anchor("Exalted \u00A92007 by White Wolf, Inc.\nhttp://www.white-wolf.com", copyrightFont); //$NON-NLS-1$
    whiteWolfPhrase.setReference("http://www.white-wolf.com"); //$NON-NLS-1$
    Bounds whiteWolfBounds = pageConfiguration.getThirdColumnRectangle(contentHeight, copyrightHeight);
    graphics.createSimpleColumn(whiteWolfBounds).withLeading(FONT_SIZE).andAlignment(Right).andTextPart(whiteWolfPhrase).encode();
  }
}
