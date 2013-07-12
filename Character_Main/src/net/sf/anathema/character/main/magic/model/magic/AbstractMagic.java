package net.sf.anathema.character.main.magic.model.magic;

import net.sf.anathema.character.main.magic.model.magic.attribute.MagicAttribute;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMagic extends SimpleIdentifier implements Magic {
  private final List<MagicAttribute> magicAttributes = new ArrayList<>();

  protected AbstractMagic(String id) {
    super(id);
  }

  public void addMagicAttribute(MagicAttribute attribute) {
    magicAttributes.add(attribute);
  }

  @Override
  public MagicAttribute[] getAttributes() {
    return magicAttributes.toArray(new MagicAttribute[magicAttributes.size()]);
  }

  @Override
  public boolean hasAttribute(Identifier attribute) {
    return magicAttributes.contains(attribute);
  }

  @Override
  public String getAttributeValue(Identifier attribute) {
    int index = magicAttributes.indexOf(attribute);
    if (index < 0) {
      return null;
    } else {
      return magicAttributes.get(index).getValue();
    }
  }
}
