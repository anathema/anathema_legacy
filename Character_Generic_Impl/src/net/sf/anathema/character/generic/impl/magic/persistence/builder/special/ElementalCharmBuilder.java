package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.ElementalMultipleEffectCharm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

public class ElementalCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_ELEMENTAL = "elemental";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element elementalElement = charmElement.element(TAG_ELEMENTAL);
    return new ElementalMultipleEffectCharm(id);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element elementalElement = charmElement.element(TAG_ELEMENTAL);
    return elementalElement != null;
  }
}