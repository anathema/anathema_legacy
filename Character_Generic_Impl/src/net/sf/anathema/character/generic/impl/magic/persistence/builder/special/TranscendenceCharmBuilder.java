package net.sf.anathema.character.generic.impl.magic.persistence.builder.special;

import net.sf.anathema.character.generic.impl.magic.charm.special.PrerequisiteModifyingCharm;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.SpecialCharmBuilder;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.TraitTypeFinder;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.traits.ITraitType;
import org.dom4j.Element;

public class TranscendenceCharmBuilder {

  private static final String TAG_TRANSCENDENCE = "transcendence";
  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  public ISpecialCharm readTranscendenceCharm(Element charmElement, String id) {
    Element transcendenceElement = charmElement.element(TAG_TRANSCENDENCE);
    if (transcendenceElement == null) {
      return null;
    }
    ITraitType trait = getGenericTraitType(id);
    int modifier = Integer.parseInt(transcendenceElement.attributeValue(SpecialCharmBuilder.ATTRIB_MODIFIER));
    return new PrerequisiteModifyingCharm(id, trait, modifier);
  }

  private ITraitType getGenericTraitType(String value) {
    String[] split = value.split("\\.");
    String traitString = split[split.length - 1];
    return traitTypeFinder.getTrait(traitString);
  }
}