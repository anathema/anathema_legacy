package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Font;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.HorizontalAlignment;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SimpleColumnBuilder;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static net.sf.anathema.character.reporting.pdf.rendering.graphics.HorizontalAlignment.Center;
import static net.sf.anathema.character.reporting.pdf.rendering.graphics.HorizontalAlignment.Left;
import static net.sf.anathema.character.reporting.pdf.rendering.graphics.HorizontalAlignment.Right;
import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.FONT_SIZE;
import static net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration.Offset;

public class CopyrightEncoder {

  private PageConfiguration pageConfiguration;
  private float contentHeight;


  public CopyrightEncoder(PageConfiguration pageConfiguration) {
    this(pageConfiguration, pageConfiguration.getContentHeight());
  }

  public CopyrightEncoder(PageConfiguration pageConfiguration, float contentHeight) {
    this.pageConfiguration = pageConfiguration;
    this.contentHeight = contentHeight;
  }

  public void encodeCopyright(SheetGraphics graphics) {
    encodeVoidStateColumn(graphics);
    encodeAnathemaColumn(graphics);
    encodeWhiteWolfColumn(graphics);
  }

  private void encodeVoidStateColumn(SheetGraphics graphics) {
    Bounds bounds = pageConfiguration.getColumnRectangle(contentHeight, getCopyrightHeight(), 1, Offset(0));
    String text = "Inspired by Voidstate\nhttp://www.voidstate.com";
    Anchor phrase = createAnchor(graphics, text, "http://www.voidstate.com");
    encoderLine(graphics, phrase, Left, bounds);
  }

  private void encodeAnathemaColumn(SheetGraphics graphics) {
    Bounds bounds = pageConfiguration.getColumnRectangle(contentHeight, getCopyrightHeight(), 1, Offset(1));
    String text = induceYear("Created with Anathema \u00A92007-{0}\nhttp://anathema.sf.net");
    Anchor phrase = createAnchor(graphics, text, "http://anathema.sf.net");
    encoderLine(graphics, phrase, Center, bounds);
  }

  private void encodeWhiteWolfColumn(SheetGraphics graphics) {
    Bounds bounds = pageConfiguration.getColumnRectangle(contentHeight, getCopyrightHeight(), 1, Offset(2));
    String text = induceYear("Exalted \u00A92007-{0} by CCP hf\nhttp://www.white-wolf.com");
    Anchor phrase = createAnchor(graphics, text, "http://www.white-wolf.com");
    encoderLine(graphics, phrase, Right, bounds);
  }

  private String induceYear(String pattern)                               {
    int year = new GregorianCalendar().get(Calendar.YEAR);
    DecimalFormat format = new DecimalFormat("####",new DecimalFormatSymbols(Locale.ENGLISH));
    return MessageFormat.format(pattern, format.format(year));
  }

  private Anchor createAnchor(SheetGraphics graphics, String text, String reference) {
    Anchor phrase = new Anchor(text, getFont(graphics)); //$NON-NLS-1$
    phrase.setReference(reference); //$NON-NLS-1$
    return phrase;
  }

  private Font getFont(SheetGraphics graphics) {
    return graphics.createCommentFont();
  }

  private float getCopyrightHeight() {
    return pageConfiguration.getPageHeight() - pageConfiguration.getContentHeight();
  }

  private void encoderLine(SheetGraphics graphics, Anchor phrase, HorizontalAlignment alignment, Bounds bounds) {
    SimpleColumnBuilder column = graphics.createSimpleColumn(bounds);
    column.withLeading(FONT_SIZE).andAlignment(alignment).andTextPart(phrase).encode();
  }
}
