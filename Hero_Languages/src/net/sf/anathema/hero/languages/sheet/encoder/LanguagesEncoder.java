package net.sf.anathema.hero.languages.sheet.encoder;

import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;
import net.sf.anathema.hero.languages.sheet.content.LanguagesContent;

public class LanguagesEncoder extends LineFillingContentEncoder<LanguagesContent> {

  public LanguagesEncoder() {
    super(LanguagesContent.class);
  }
}
