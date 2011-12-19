package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfTemplate;

public class GraphicsTemplate {

  private PdfTemplate template;
  private BaseFont baseFont;
  private PdfContentByte parent;

  public GraphicsTemplate(PdfContentByte parent, BaseFont baseFont, float width, float height) {
    this.parent = parent;
    this.template = parent.createTemplate(width, height);
    this.baseFont = baseFont;
  }

  @Deprecated
  public PdfTemplate getTemplate() {
    return template;
  }

  public SheetGraphics getTemplateGraphics() {
    return new SheetGraphics(template, baseFont);
  }

  public void addToParentAt(float templateX, float templateY) {
    parent.addTemplate(template, templateX, templateY);
  }
}
