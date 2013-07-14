package net.sf.anathema.hero.intimacies.sheet.encoder;

import net.sf.anathema.hero.intimacies.sheet.content.SimpleIntimaciesContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;

public class SimpleIntimaciesEncoder extends LineFillingContentEncoder<SimpleIntimaciesContent> {

  public SimpleIntimaciesEncoder() {
    super(SimpleIntimaciesContent.class);
  }
}
