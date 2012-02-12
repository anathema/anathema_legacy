package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.lib.util.Identificate;

public abstract class AbstractEncoderFactory extends Identificate implements EncoderFactory {

  private EncoderAttributeValue preferredHeight;

  public AbstractEncoderFactory(String id) {
    super(id);
  }

  protected void setPreferredHeight(EncoderAttributeValue value) {
    this.preferredHeight = value;
  }

  @Override
  public float getPreferredHeight(EncodingMetrics metrics) {
    if (preferredHeight == null) {
      return new NullEncoderFactory(null).getPreferredHeight(metrics);
    }
    return preferredHeight.getValue(metrics);
  }
}
