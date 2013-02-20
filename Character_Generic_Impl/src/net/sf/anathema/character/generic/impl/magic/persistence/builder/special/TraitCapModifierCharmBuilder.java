package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.TraitCapModifyingCharm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.TraitTypeFinder;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import org.dom4j.Element;

public class TraitCapModifierCharmBuilder {
  private static final String TAG_TRAIT_CAP_MODIFIER = "traitCapModifier";
  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  public ISpecialCharm readTraitCapModifierCharm(Element charmElement, String id) {
    Element traitCapModifierElement = charmElement.element(TAG_TRAIT_CAP_MODIFIER);
    if (traitCapModifierElement == null) {
      return null;
    }
    String traitString = traitCapModifierElement.attributeValue(SpecialCharmBuilder.ATTRIB_TRAIT);
    if (traitString == null) {
      traitString = id.split("\\.")[2];
    }
    ITraitType trait = traitTypeFinder.getTrait(traitString);
    int modifier = Integer.parseInt(traitCapModifierElement.attributeValue(SpecialCharmBuilder.ATTRIB_MODIFIER));
    return new TraitCapModifyingCharm(id, trait, modifier);
  }
}