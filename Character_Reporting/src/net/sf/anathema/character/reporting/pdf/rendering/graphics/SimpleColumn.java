package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class SimpleColumn {

  private final ColumnText columnText;

  public SimpleColumn(PdfContentByte directContent, Bounds bounds) {
    columnText = new ColumnText(directContent);
    float minX = bounds.getMinX();
    float minY = bounds.getMinY();
    float maxX = bounds.getMaxX();
    float maxY = bounds.getMaxY();
    columnText.setSimpleColumn(minX, minY, maxX,maxX);
    setAlignment(HorizontalAlignment.Left);
  }

  public void addText(Phrase phrase) {
    columnText.addText(phrase);
  }

  public int go() throws DocumentException {
    return columnText.go();
  }

  public void setLeading(float leading) {
    columnText.setLeading(leading);
  }

  public void setAlignment(HorizontalAlignment alignment) {
    columnText.setAlignment(alignment.getPdfAlignment());
  }

  public float getYLine() {
    return columnText.getYLine();
  }

  public int go(boolean simulate) throws DocumentException {
    return columnText.go(simulate);
  }

  public void setYLine(float yLine) {
    columnText.setYLine(yLine);
  }

  public void setText(Phrase phrase) {
    columnText.setText(phrase);
  }

  public int getLinesWritten() {
    return columnText.getLinesWritten();
  }
}
