package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class NullEncoderFactory extends Identificate implements BoxContentEncoderFactory {
  public static final NullBoxContentEncoder NULL_ENCODER = new NullBoxContentEncoder("Unknown");

  public NullEncoderFactory(String id) {
    super(id);
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    return NULL_ENCODER;
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }

  @Override
  public boolean hasAttribute(EncoderAttributeType type) {
    return false;
  }

  @Override
  public float getValue(BasicContent content, EncoderAttributeType type) {
    throw new UnsupportedOperationException();
  }
}
