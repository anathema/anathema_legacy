package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;

@DoNotInstantiateAutomatically
public class ConfigurableDummySpecialCharmBuilder implements SpecialCharmBuilder {
  private SpecialCharmDto dto;
  private ISpecialCharm charm;

  @Override
  public ISpecialCharm readCharm(SpecialCharmDto dto) {
    if (dto.equals(this.dto)){
      return charm;
    }
    throw new RuntimeException();
  }

  @Override
  public boolean supports(SpecialCharmDto dto) {
    return dto.equals(this.dto);
  }

  public ConfigurableDummySpecialCharmBuilder support(SpecialCharmDto dto) {
    this.dto = dto;
    return this;
  }

  public ConfigurableDummySpecialCharmBuilder with(ISpecialCharm charm) {
    this.charm = charm;
    return this;
  }
}