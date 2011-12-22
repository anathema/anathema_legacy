package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;

public class SimpleColumn {

  public static boolean hasMoreText(int status) {
    return ColumnText.hasMoreText(status);
  }

  private final ColumnText columnText;

  public SimpleColumn(PdfContentByte directContent, Bounds bounds) {
    columnText = new ColumnText(directContent);
    float minX = bounds.getMinX();
    float minY = bounds.getMinY();
    float maxX = bounds.getMaxX();
    float maxY = bounds.getMaxY();
    columnText.setSimpleColumn(minX, minY, maxX, maxY);
  }

  public void addElement(Element element) {
    columnText.addElement(element);
  }

  public void addText(Phrase phrase) {
    columnText.addText(phrase);
  }

  public void setText(Phrase phrase) {
    columnText.setText(phrase);
  }

  public void setLeading(float leading) {
    columnText.setLeading(leading);
  }

  public void setAlignment(HorizontalAlignment alignment) {
    columnText.setAlignment(alignment.getPdfAlignment());
  }

  public void setYLine(float yLine) {
    columnText.setYLine(yLine);
  }

  public float getYLine() {
    return columnText.getYLine();
  }

  public int getLinesWritten() {
    return columnText.getLinesWritten();
  }

  public int encode() throws DocumentException {
    return columnText.go();
  }

  public int simulate() throws DocumentException {
    return columnText.go(true);
  }
}
