package net.sf.anathema.hero.sheet.pdf.encoder.graphics.shape;

import net.sf.anathema.hero.sheet.pdf.encoder.general.Position;

public interface Shape {

  void encodeOutlined(Position lowerLeft);

  void encodeFilled(Position lowerLeft);
}
