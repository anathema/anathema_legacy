package net.sf.anathema.character.reporting.pdf.rendering.graphics.shape;

import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.reporting.pdf.rendering.Position;

public interface Shape {

  void encodeOutlined(Position lowerLeft);

  void encodeFilled(Position lowerLeft);
}
