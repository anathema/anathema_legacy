package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.character.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.magic.parser.dto.special.TraitCapModifierDto;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.traitcap.TraitCapModifyingCharm;

@SuppressWarnings("UnusedDeclaration")
public class TraitCapModifierCharmBuilder implements SpecialCharmBuilder {

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    TraitCapModifierDto dto = overallDto.traitCapModifier;
    return new TraitCapModifyingCharm(overallDto.charmId, traitTypeFinder.getTrait(dto.trait), dto.modifier);
  }

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.traitCapModifier != null;
  }
}