package net.sf.anathema.hero.concept.sheet.personal.encoder;

import net.sf.anathema.hero.sheet.pdf.encoder.boxes.EncodingMetrics;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.PreferredHeight;

public class PreferredPersonalInfoHeight implements PreferredHeight {
  @Override
  public float getValue(EncodingMetrics metrics, float width) {
    PersonalInfoEncoder encoder = new PersonalInfoEncoder(null);
    return encoder.getPreferredContentHeight(metrics.getSession());
  }
}
