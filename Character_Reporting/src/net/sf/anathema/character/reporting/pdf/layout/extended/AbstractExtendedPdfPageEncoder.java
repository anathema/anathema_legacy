package net.sf.anathema.character.reporting.pdf.layout.extended;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.HorizontalLineBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.PdfBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.HorizontalAlignment;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration;
import net.sf.anathema.character.reporting.pdf.rendering.page.PageEncoder;
import net.sf.anathema.lib.resources.IResources;

import static net.sf.anathema.character.reporting.pdf.rendering.page.PageConfiguration.Offset;

public abstract class AbstractExtendedPdfPageEncoder implements PageEncoder {
  private final IResources resources;

  private final PageConfiguration pageConfiguration;
  private final IExtendedPartEncoder partEncoder;
  private final PdfBoxEncoder boxEncoder;

  public AbstractExtendedPdfPageEncoder(IExtendedPartEncoder partEncoder, IResources resources,
          PageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder();
  }

  public abstract void encode(Document document, SheetGraphics graphics, ReportSession session)
          throws DocumentException;

  protected void encodeCopyright(SheetGraphics graphics) throws DocumentException {
    float lineHeight = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 2f;
    Font copyrightFont = graphics.createCommentFont();
    float copyrightHeight = getPageConfiguration().getLowerContentY();

    Bounds firstColumnBounds =
            getPageConfiguration().getColumnRectangle(getContentHeight(), copyrightHeight, 1, Offset(0));
    Anchor voidstatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidstatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    graphics.createSimpleColumn(firstColumnBounds).withLeading(lineHeight).andTextPart(voidstatePhrase).encode();

    // TODO: Eliminate these hard-coded copyright dates; these should be in a properties file or something.
    Anchor anathemaPhrase =
            new Anchor("Created with Anathema \u00A92011\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds =
            getPageConfiguration().getColumnRectangle(getContentHeight(), copyrightHeight, 1, Offset(1));
    graphics.createSimpleColumn(anathemaBounds).withLeading(lineHeight).andAlignment(HorizontalAlignment.Center)
            .andTextPart(anathemaPhrase).encode();
    Anchor whitewolfPhrase =
            new Anchor("Exalted \u00A92011 by White Wolf, Inc.\nhttp://www.white-wolf.com", //$NON-NLS-1$
                    copyrightFont);
    whitewolfPhrase.setReference("http://www.white-wolf.com"); //$NON-NLS-1$

    Bounds whitewolfBounds =
            getPageConfiguration().getColumnRectangle(getContentHeight(), copyrightHeight, 1, Offset(2));
    graphics.createSimpleColumn(whitewolfBounds).withLeading(lineHeight).andAlignment(HorizontalAlignment.Right)
            .andTextPart(whitewolfPhrase).encode();
  }

  protected IResources getResources() {
    return resources;
  }

  protected PageConfiguration getPageConfiguration() {
    return pageConfiguration;
  }

  public float getContentHeight() {
    return pageConfiguration.getContentHeight();
  }

  protected IExtendedPartEncoder getPartEncoder() {
    return partEncoder;
  }

  protected PdfBoxEncoder getBoxEncoder() {
    return boxEncoder;
  }

  protected String getHeaderLabel(String key) {
    return getResources().getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  protected float calculateBoxIncrement(float height) {
    return height + IVoidStateFormatConstants.PADDING;
  }

  protected float removeBoxIncrement(float height) {
    return height - IVoidStateFormatConstants.PADDING;
  }

  protected Bounds calculateBounds(int column, int span, float distanceFromTop, float height) {
    return getPageConfiguration().getColumnRectangle(distanceFromTop, height, span, Offset(column - 1));
  }

  private float getWidth(int span) {
    return getPageConfiguration().getColumnWidth(span);
  }

  protected float encodeFixedBox(SheetGraphics graphics, ReportSession session, ContentEncoder encoder, int column,
          int span, float distanceFromTop, float height) throws DocumentException {
    getBoxEncoder().encodeBox(session, graphics, encoder, calculateBounds(column, span, distanceFromTop, height));
    return height;
  }

  protected float encodeFixedBoxBottom(SheetGraphics graphics, ReportSession session, ContentEncoder encoder,
          int column, int span, float bottom, float height) throws DocumentException {
    getBoxEncoder().encodeBox(session, graphics, encoder, calculateBounds(column, span, bottom - height, height));
    return height;
  }

  protected float encodeVariableBox(SheetGraphics graphics, ReportSession session, IVariableContentEncoder encoder,
          int column, int span, float distanceFromTop, float maxHeight) throws DocumentException {
    float height = Math.min(maxHeight, boxEncoder.getRequestedHeight(graphics, encoder, session, getWidth(span)));
    return encodeFixedBox(graphics, session, encoder, column, span, distanceFromTop, height);
  }

  protected float encodeVariableBoxBottom(SheetGraphics graphics, ReportSession session,
          IVariableContentEncoder encoder, int column, int span, float bottom, float maxHeight)
          throws DocumentException {
    float height = Math.min(maxHeight, boxEncoder.getRequestedHeight(graphics, encoder, session, getWidth(span)));
    return encodeFixedBoxBottom(graphics, session, encoder, column, span, bottom, height);
  }

  protected float encodeNotes(SheetGraphics graphics, ReportSession session, String title, int column, int span,
          float distanceFromTop, float height, int textColumns) throws DocumentException {
    ContentEncoder encoder = new HorizontalLineBoxContentEncoder(textColumns, resources, title);
    return encodeFixedBox(graphics, session, encoder, column, span, distanceFromTop, height);
  }
}
