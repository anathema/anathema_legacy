package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.lib.util.SimpleIdentifier;

public abstract class AbstractEncoderFactory extends SimpleIdentifier implements EncoderFactory {

  private PreferredHeight preferredHeight;

  public AbstractEncoderFactory(String id) {
    super(id);
  }

  protected void setPreferredHeight(PreferredHeight value) {
    this.preferredHeight = value;
  }

  @Override
  public float getPreferredHeight(EncodingMetrics metrics, float width) {
    if (preferredHeight == null) {
      return new NullEncoderFactory(null).getPreferredHeight(metrics, width);
    }
    return preferredHeight.getValue(metrics, width);
  }
}
