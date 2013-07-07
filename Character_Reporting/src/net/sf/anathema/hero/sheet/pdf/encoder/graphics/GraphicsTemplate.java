package net.sf.anathema.hero.sheet.pdf.encoder.graphics;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;

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

  public Image getImageInstance() throws BadElementException {
    return Image.getInstance(template);
  }
}
