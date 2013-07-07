package net.sf.anathema.hero.sheet.pdf.page.layout.field;

import net.sf.anathema.hero.sheet.pdf.encoder.boxes.BoxBoundsFactory;

public class PreferredHeight implements HeightStrategy {

  private FieldEncoder encoder;

  public PreferredHeight(FieldEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public float getHeight(float contentWidth) {
    float preferredContentHeight = encoder.getPreferredHeight(contentWidth);
    if (preferredContentHeight == 0) {
      return 0;
    }
    return BoxBoundsFactory.getBoxHeight(preferredContentHeight);
  }
}
