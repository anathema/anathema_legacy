package net.sf.anathema.character.reporting.pdf.rendering.graphics;

import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
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
    super(image);
    setHorizontalAlignment(Element.ALIGN_CENTER);
    setVerticalAlignment(Element.ALIGN_MIDDLE);
    setBorder(Rectangle.NO_BORDER);
  }
}
