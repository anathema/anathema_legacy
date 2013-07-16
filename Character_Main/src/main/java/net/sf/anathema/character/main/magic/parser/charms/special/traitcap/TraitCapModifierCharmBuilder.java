package net.sf.anathema.character.main.magic.parser.charms.special.traitcap;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.TraitCapModifyingCharm;
import net.sf.anathema.character.main.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.TraitCapModifierDto;
import org.dom4j.Element;

@RegisteredSpecialCharmBuilder
public class TraitCapModifierCharmBuilder implements SpecialCharmBuilder {
  private static final String TAG_TRAIT_CAP_MODIFIER = "traitCapModifier";
  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    SpecialCharmDto overallDto = new SpecialCharmDto();
    overallDto.charmId = id;
    new TraitCapModifierParser().parse(charmElement, overallDto);
    TraitCapModifierDto dto = overallDto.traitCapModifier;
    return new TraitCapModifyingCharm(id, traitTypeFinder.getTrait(dto.trait), dto.modifier);
  }

  @Override
  public boolean willReadCharm(Element charmElement) {
    return charmElement.element(TAG_TRAIT_CAP_MODIFIER) != null;
  }
}