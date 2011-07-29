package net.sf.anathema.character.reporting.sheet.page;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.sheet.PdfEncodingRegistry;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.IPdfVariableContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.common.PdfHorizontalLineContentEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Anchor;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public abstract class AbstractPdfPageEncoder implements IPdfPageEncoder {
  public static final float CONTENT_HEIGHT = 755;
  private final IResources resources;
  private final BaseFont baseFont;

  private final PdfEncodingRegistry registry;
  private final PdfPageConfiguration pageConfiguration;
  private final IPdfPartEncoder partEncoder;
  private final PdfBoxEncoder boxEncoder;

  public AbstractPdfPageEncoder(IPdfPartEncoder partEncoder,
                                PdfEncodingRegistry registry,
                                IResources resources,
                                PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.registry = registry;
    this.baseFont = registry.getBaseFont();
    this.resources = resources;
    this.pageConfiguration = pageConfiguration;
    this.boxEncoder = new PdfBoxEncoder(resources, getBaseFont());
  }

  public abstract void encode(Document document, PdfContentByte directContent,
                              IGenericCharacter character,
                              IGenericDescription description)
      throws DocumentException;

  protected void encodeCopyright(PdfContentByte directContent) throws DocumentException {
    int lineHeight = IVoidStateFormatConstants.COMMENT_FONT_SIZE + 2;
    Font copyrightFont = new Font(getBaseFont(), IVoidStateFormatConstants.COMMENT_FONT_SIZE);
    float copyrightHeight = getPageConfiguration().getPageHeight() - getPageConfiguration().getContentHeight();
    Bounds firstColumnBounds = getPageConfiguration().getFirstColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    Anchor voidstatePhrase = new Anchor("Inspired by Voidstate\nhttp://www.voidstate.com", copyrightFont); //$NON-NLS-1$
    voidstatePhrase.setReference("http://www.voidstate.com"); //$NON-NLS-1$
    PdfTextEncodingUtilities.encodeText(directContent, voidstatePhrase, firstColumnBounds, lineHeight);
    
    // TODO: Eliminate these hard-coded copyright dates; these should be in a properties file or something.
    Anchor anathemaPhrase = new Anchor("Created with Anathema \u00A92011\nhttp://anathema.sf.net", copyrightFont); //$NON-NLS-1$
    anathemaPhrase.setReference("http://anathema.sf.net"); //$NON-NLS-1$
    Bounds anathemaBounds = getPageConfiguration().getSecondColumnRectangle(CONTENT_HEIGHT, copyrightHeight, 1);
    PdfTextEncodingUtilities.encodeText(directContent, anathemaPhrase, anathemaBounds, lineHeight, Element.ALIGN_CENTER);
    Anchor whitewolfPhrase = new Anchor("Exalted \u00A92011 by White Wolf, Inc.\nhttp://www.white-wolf.com", //$NON-NLS-1$
        copyrightFont);
    whitewolfPhrase.setReference("http://www.white-wolf.com"); //$NON-NLS-1$
    
    Bounds whitewolfBounds = getPageConfiguration().getThirdColumnRectangle(CONTENT_HEIGHT, copyrightHeight);
    PdfTextEncodingUtilities.encodeText(
        directContent,
        whitewolfPhrase,
        whitewolfBounds,
        lineHeight,
        Element.ALIGN_RIGHT);
  }
  
  protected PdfEncodingRegistry getRegistry() {
    return registry;
  }

  protected IResources getResources() {
    return resources;
  }

  protected BaseFont getBaseFont() {
    return baseFont;
  }

  protected PdfPageConfiguration getPageConfiguration() {
    return pageConfiguration;
  }
  
  protected IPdfPartEncoder getPartEncoder() {
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
  
  protected Bounds calculateBounds(int column, int span,
                                   float distanceFromTop, float height) {
    Bounds bounds = null;
    switch (column) {
      case 1:
        bounds = getPageConfiguration().getFirstColumnRectangle(distanceFromTop, height, span);
        break;
      case 2:
        bounds = getPageConfiguration().getSecondColumnRectangle(distanceFromTop, height, span);
        break;
      case 3:
        bounds = getPageConfiguration().getThirdColumnRectangle(distanceFromTop, height);
        break;
    }
    return bounds;
  }
  
  protected float getWidth(int column, int span) {
    switch (column) {
      case 1:
        return getPageConfiguration().getFirstColumnRectangle(0, 0, span).width;
      case 2:
        return getPageConfiguration().getSecondColumnRectangle(0, 0, span).width;
      case 3:
        return getPageConfiguration().getThirdColumnRectangle(0, 0).width;
    }
    return 0;
  }

  protected float encodeFixedBox(PdfContentByte directContent,
                                 IGenericCharacter character,
                                 IGenericDescription description,
                                 IPdfContentBoxEncoder encoder, int column,
                                 int span, float distanceFromTop, float height)
      throws DocumentException {
    getBoxEncoder().encodeBox(directContent, encoder, character,
                              description, calculateBounds(column, span, distanceFromTop, height));
    return height;
  }

  protected float encodeFixedBoxBottom(PdfContentByte directContent,
                                       IGenericCharacter character,
                                       IGenericDescription description,
                                       IPdfContentBoxEncoder encoder, int column,
                                       int span, float bottom, float height)
      throws DocumentException {
    getBoxEncoder().encodeBox(directContent, encoder, character,
                              description, calculateBounds(column, span, bottom - height, height));
    return height;
  }
  
  protected float encodeVariableBox(PdfContentByte directContent,
                                    IGenericCharacter character,
                                    IGenericDescription description,
                                    IPdfVariableContentBoxEncoder encoder, int column,
                                    int span, float distanceFromTop, float maxHeight)
      throws DocumentException {
    float height = Math.min(maxHeight, boxEncoder.getRequestedHeight(encoder, character, getWidth(column, span)));
    return encodeFixedBox(directContent, character, description, encoder, column,
                          span, distanceFromTop, height);
  }
  
  protected float encodeVariableBoxBottom(PdfContentByte directContent,
                                          IGenericCharacter character,
                                          IGenericDescription description,
                                          IPdfVariableContentBoxEncoder encoder, int column,
                                          int span, float bottom, float maxHeight)
      throws DocumentException {
    float height = Math.min(maxHeight, boxEncoder.getRequestedHeight(encoder, character, getWidth(column, span)));
    return encodeFixedBoxBottom(directContent, character, description, encoder, column,
                                span, bottom, height);  
  }
  
  protected float encodeNotes(PdfContentByte directContent,
                              IGenericCharacter character,
                              IGenericDescription description, String title,
                              int column, int span, float distanceFromTop,
                              float height, int textColumns)
      throws DocumentException {
    IPdfContentBoxEncoder encoder = new PdfHorizontalLineContentEncoder(textColumns, title);
    return encodeFixedBox(directContent, character, description, encoder,
                          column, span, distanceFromTop, height);
  }
}