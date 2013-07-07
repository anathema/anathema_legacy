package net.sf.anathema.hero.sheet.pdf.encoder.graphics;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;

public class SimpleColumnBuilder {

  private final SimpleColumn simpleColumn;

  public SimpleColumnBuilder(PdfContentByte directContent, Bounds bounds) {
    this.simpleColumn = new SimpleColumn(directContent, bounds);
  }

  public SimpleColumnBuilder andAlignment(HorizontalAlignment alignment) {
    simpleColumn.setAlignment(alignment);
    return this;
  }

  public SimpleColumnBuilder withLeading(float leading) {
    simpleColumn.setLeading(leading);
    return this;
  }

  public SimpleColumnBuilder withElement(Element element) {
    simpleColumn.addElement(element);
    return this;
  }

  public SimpleColumnBuilder andTextPart(Phrase phrase) {
    simpleColumn.addText(phrase);
    return this;
  }

  public SimpleColumn encode() {
      simpleColumn.encode();
      return simpleColumn;
  }

  public SimpleColumn get() {
    return simpleColumn;
  }
}
