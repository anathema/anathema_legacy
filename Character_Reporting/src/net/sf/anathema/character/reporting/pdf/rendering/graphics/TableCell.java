package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class TableCell extends PdfPCell {

  public TableCell(PdfPTable table, int border) {
    super(table);
    setBorder(border);
  }

  public TableCell(Phrase phrase, int border) {
    super(phrase);
    setBorder(border);
  }

  public TableCell(Phrase phrase, int border, int horizontalAlignment, int verticalAlignment) {
    super(phrase);
    setBorder(border);
    setHorizontalAlignment(horizontalAlignment);
    setVerticalAlignment(verticalAlignment);
  }

  public TableCell(Image image) {
    this(new Phrase(new Chunk(image, 0, 0)), Rectangle.NO_BORDER);
    setHorizontalAlignment(Element.ALIGN_CENTER);
    setVerticalAlignment(Element.ALIGN_MIDDLE);
  }
}
