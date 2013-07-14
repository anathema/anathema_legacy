package net.sf.anathema.hero.languages.sheet.encoder;

import net.sf.anathema.hero.languages.sheet.content.LanguagesContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.LineFillingContentEncoder;

public class LanguagesEncoder extends LineFillingContentEncoder<LanguagesContent> {

  public LanguagesEncoder() {
    super(LanguagesContent.class);
  }
}
