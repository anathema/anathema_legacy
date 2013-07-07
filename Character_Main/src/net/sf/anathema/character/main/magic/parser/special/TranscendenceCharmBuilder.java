package net.sf.anathema.character.main.magic.parser.special;

import net.sf.anathema.character.main.magic.model.charm.special.PrerequisiteModifyingCharm;
import net.sf.anathema.character.main.magic.parser.TraitTypeFinder;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.traits.TraitType;
import org.dom4j.Element;

@SpecialCharmParser
public class TranscendenceCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_TRANSCENDENCE = "transcendence";
  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    Element transcendenceElement = charmElement.element(TAG_TRANSCENDENCE);
    TraitType trait = getGenericTraitType(id);
    int modifier = Integer.parseInt(transcendenceElement.attributeValue(ATTRIB_MODIFIER));
    return new PrerequisiteModifyingCharm(id, trait, modifier);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element transcendenceElement = charmElement.element(TAG_TRANSCENDENCE);
    return transcendenceElement != null;
  }

  private TraitType getGenericTraitType(String value) {
    String[] split = value.split("\\.");
    String traitString = split[split.length - 1];
    return traitTypeFinder.getTrait(traitString);
  }
}