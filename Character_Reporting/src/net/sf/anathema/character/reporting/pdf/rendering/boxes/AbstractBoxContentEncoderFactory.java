package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.lib.util.Identificate;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBoxContentEncoderFactory extends Identificate implements BoxContentEncoderFactory {

  private Map<EncoderAttributeType, EncoderAttributeValue> attributesByType = new HashMap<EncoderAttributeType, EncoderAttributeValue>();

  public AbstractBoxContentEncoderFactory(String id) {
    super(id);
  }

  protected void setAttribute(EncoderAttributeType type, EncoderAttributeValue value) {
    attributesByType.put(type, value);
  }

  @Override
  public boolean hasAttribute(EncoderAttributeType type) {
    return attributesByType.containsKey(type);
  }

  @Override
  public float getValue(BasicContent content, EncoderAttributeType type) {
    return attributesByType.get(type).getValue(content);
  }
}
