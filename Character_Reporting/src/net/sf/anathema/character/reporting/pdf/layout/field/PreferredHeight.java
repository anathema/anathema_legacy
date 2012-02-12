package net.sf.anathema.character.reporting.pdf.layout.field;

public class PreferredHeight implements HeightStrategy {

  private FieldEncoder encoder;

  public PreferredHeight(FieldEncoder encoder) {
    this.encoder = encoder;
  }

  @Override
  public float getHeight(float contentWidth) {
    return encoder.getPreferredHeight(contentWidth);
  }
}
