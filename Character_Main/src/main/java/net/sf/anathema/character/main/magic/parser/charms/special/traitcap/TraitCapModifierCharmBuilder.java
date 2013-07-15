package net.sf.anathema.character.main.magic.parser.charms.special.traitcap;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.TraitCapModifyingCharm;
import net.sf.anathema.character.main.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmParser;
import org.dom4j.Element;

@SpecialCharmParser
public class TraitCapModifierCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_TRAIT_CAP_MODIFIER = "traitCapModifier";
  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    TraitCapModifierDto dto = createDto(charmElement, id);
    return new TraitCapModifyingCharm(id, traitTypeFinder.getTrait(dto.trait), dto.modifier);
  }

  private TraitCapModifierDto createDto(Element charmElement, String id) {
    TraitCapModifierDto dto = new TraitCapModifierDto();
    Element traitCapModifierElement = charmElement.element(TAG_TRAIT_CAP_MODIFIER);
    dto.trait = readTraitString(id, traitCapModifierElement);
    dto.modifier =  Integer.parseInt(traitCapModifierElement.attributeValue(ATTRIB_MODIFIER));
    return dto;
  }

  private String readTraitString(String id, Element traitCapModifierElement) {
    String traitString = traitCapModifierElement.attributeValue(ATTRIB_TRAIT);
    if (traitString == null) {
      traitString = id.split("\\.")[2];
    }
    return traitString;
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    Element traitCapModifierElement = charmElement.element(TAG_TRAIT_CAP_MODIFIER);
    return traitCapModifierElement != null;
  }
}