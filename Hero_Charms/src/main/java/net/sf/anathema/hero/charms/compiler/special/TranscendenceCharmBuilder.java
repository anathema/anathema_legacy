package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import net.sf.anathema.hero.charms.model.special.prerequisite.PrerequisiteModifyingCharm;
import net.sf.anathema.character.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.hero.traits.model.TraitType;

@SuppressWarnings("UnusedDeclaration")
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