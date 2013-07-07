package net.sf.anathema.hero.intimacies.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.encoder.general.LineFillingContentEncoder;
import net.sf.anathema.hero.intimacies.sheet.content.SimpleIntimaciesContent;

public class SimpleIntimaciesEncoder extends LineFillingContentEncoder<SimpleIntimaciesContent> {

  public SimpleIntimaciesEncoder() {
    super(SimpleIntimaciesContent.class);
  }
}
