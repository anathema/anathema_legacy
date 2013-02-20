package net.sf.anathema.character.db.magic;

import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.SpecialCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.special.SpecialCharmParser;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import org.dom4j.Element;

@SpecialCharmParser
public class ElementalCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_ELEMENTAL = "elemental";

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    return new ElementalMultipleEffectCharm(id);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element elementalElement = charmElement.element(TAG_ELEMENTAL);
    return elementalElement != null;
  }
}