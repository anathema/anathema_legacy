package net.sf.anathema.character.main.magic.parser.charms.special.transcendence;

import net.sf.anathema.character.main.magic.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.charm.special.PrerequisiteModifyingCharm;
import net.sf.anathema.character.main.magic.parser.charms.TraitTypeFinder;
import net.sf.anathema.character.main.magic.parser.charms.special.RegisteredSpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.charms.special.SpecialCharmBuilder;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.character.main.magic.parser.dto.special.TranscendenceDto;
import net.sf.anathema.character.main.traits.TraitType;
import org.dom4j.Element;

@RegisteredSpecialCharmBuilder
public class TranscendenceCharmBuilder implements SpecialCharmBuilder {

  private static final String TAG_TRANSCENDENCE = "transcendence";
  private final TraitTypeFinder traitTypeFinder = new TraitTypeFinder();

  @Override
  public ISpecialCharm readCharm(Element charmElement, String id) {
    SpecialCharmDto overallDto = new SpecialCharmDto();
    overallDto.charmId = id;
    new TranscendenceParser().parse(charmElement, overallDto);
    return createSpecialCharm(id, overallDto.transcendence);
  }

  private ISpecialCharm createSpecialCharm(String id, TranscendenceDto transcendence) {
    TraitType trait = traitTypeFinder.getTrait(transcendence.trait);
    int modifier = transcendence.modifier;
    return new PrerequisiteModifyingCharm(id, trait, modifier);
  }

  @Override
  public boolean supports(Element charmElement) {
    return charmElement.element(TAG_TRANSCENDENCE) != null;
  }
}