package net.sf.anathema.character.reporting.pdf.layout.field;

import net.sf.anathema.character.reporting.pdf.rendering.general.box.BoxBoundsFactory;

public class PreferredHeight implements HeightStrategy {

  private FieldEncoder encoder;

  public PreferredHeight(FieldEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public float getHeight(float contentWidth) {
    float preferredContentHeight = encoder.getPreferredHeight(contentWidth);
    return BoxBoundsFactory.getBoxHeight(preferredContentHeight);
  }
}
