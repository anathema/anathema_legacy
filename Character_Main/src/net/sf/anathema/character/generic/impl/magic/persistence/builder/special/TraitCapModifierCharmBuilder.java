package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.TraitCapModifyingCharm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.TraitTypeFinder;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.TraitType;
import org.dom4j.Element;

@SpecialCharmParser
public class TraitCapModifierCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_TRAIT_CAP_MODIFIER = "traitCapModifier";
  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element traitCapModifierElement = charmElement.element(TAG_TRAIT_CAP_MODIFIER);
    String traitString = traitCapModifierElement.attributeValue(ATTRIB_TRAIT);
    if (traitString == null) {
      traitString = id.split("\\.")[2];
    }
    TraitType trait = traitTypeFinder.getTrait(traitString);
    int modifier = Integer.parseInt(traitCapModifierElement.attributeValue(ATTRIB_MODIFIER));
    return new TraitCapModifyingCharm(id, trait, modifier);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element traitCapModifierElement = charmElement.element(TAG_TRAIT_CAP_MODIFIER);
    return traitCapModifierElement != null;
  }
}