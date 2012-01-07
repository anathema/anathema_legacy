package net.sf.anathema.character.reporting.pdf.rendering.graphics.shape;

import net.sf.anathema.character.reporting.pdf.rendering.extent.Position;

public interface Shape {

  void encodeOutlined(Position lowerLeft);

  void encodeFilled(Position lowerLeft);
}
