package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import org.apache.batik.bridge.GVTBuilder;

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

  public SimpleColumnBuilder andTextPart(Phrase phrase) {
    simpleColumn.addText(phrase);
    return this;
  }

  public SimpleColumn go() throws DocumentException {
    simpleColumn.go();
    return simpleColumn;
  }

  public SimpleColumn get() {
    return simpleColumn;
  }
}
