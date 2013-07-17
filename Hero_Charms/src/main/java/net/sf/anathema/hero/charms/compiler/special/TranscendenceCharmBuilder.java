package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.PrerequisiteModifyingCharm;
import net.sf.anathema.character.main.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.traits.TraitType;

@RegisteredSpecialCharmBuilder
public class TranscendenceCharmBuilder implements SpecialCharmBuilder {

  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(SpecialCharmDto overallDto) {
    TraitType trait = traitTypeFinder.getTrait(overallDto.transcendence.trait);
    int modifier = overallDto.transcendence.modifier;
    return new PrerequisiteModifyingCharm(overallDto.charmId, trait, modifier);
  }

  @Override
  public boolean supports(SpecialCharmDto overallDto) {
    return overallDto.transcendence != null;
  }
}