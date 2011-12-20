package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;

public class GraphicsTemplate {

  private PdfTemplate template;
  private BaseFont baseFont;
  private PdfContentByte parent;
  private BaseFont symbolBaseFont;

  public GraphicsTemplate(PdfContentByte parent, BaseFont baseFont, BaseFont symbolBaseFont, float width, float height) {
    this.parent = parent;
    this.symbolBaseFont = symbolBaseFont;
    this.template = parent.createTemplate(width, height);
    this.baseFont = baseFont;
  }

  @Deprecated
  public PdfTemplate getTemplate() {
    return template;
  }

  public SheetGraphics getTemplateGraphics() {
    return new SheetGraphics(template, baseFont, symbolBaseFont);
  }

  public void addToParentAt(float templateX, float templateY) {
    parent.addTemplate(template, templateX, templateY);
  }

  public Image getImageInstance() throws BadElementException {
    return Image.getInstance(template);
  }
}
