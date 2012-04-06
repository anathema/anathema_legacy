package net.sf.anathema.character.reporting.pdf.rendering.boxes.personal;

import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncodingMetrics;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.PreferredHeight;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.BoxBoundsFactory;

public class PreferredPersonalInfoHeight implements PreferredHeight {
  @Override
  public float getValue(EncodingMetrics metrics, float width) {
    PersonalInfoEncoder encoder = new PersonalInfoEncoder(null);
    return BoxBoundsFactory.getContentHeight(encoder.getPreferredContentHeight(metrics.getSession()));
  }
}
