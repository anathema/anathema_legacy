package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class NullEncoderFactory extends SimpleIdentifier implements EncoderFactory {
  public static final NullBoxContentEncoder NULL_ENCODER = new NullBoxContentEncoder("Unknown");

  public NullEncoderFactory(String id) {
    super(id);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return NULL_ENCODER;
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }

  @Override
  public float getPreferredHeight(EncodingMetrics metrics, float width) {
    return 30;
  }
}
