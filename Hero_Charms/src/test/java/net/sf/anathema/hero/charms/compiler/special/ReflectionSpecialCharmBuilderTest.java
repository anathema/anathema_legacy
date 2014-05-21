package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.character.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.framework.environment.ConfigurableDummyObjectFactory;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class ReflectionSpecialCharmBuilderTest {

  private ConfigurableDummyObjectFactory factory = new ConfigurableDummyObjectFactory();

  @Test
  public void returnsCharmFromRegisteredBuilder() {
    SpecialCharmDto dto = new SpecialCharmDto();
    ISpecialCharm charm = mock(ISpecialCharm.class);
    registerBuilderForDtoYieldingCharm(dto, charm);
    ISpecialCharm specialCharm = new ReflectionSpecialCharmBuilder(factory).readCharm(dto);
    assertThat(specialCharm, is(charm));
  }

  private void registerBuilderForDtoYieldingCharm(SpecialCharmDto dto, ISpecialCharm charm) {
    ConfigurableDummySpecialCharmBuilder builder = new ConfigurableDummySpecialCharmBuilder();
    builder.support(dto).with(charm);
    factory.add(SpecialCharmBuilder.class, builder);
  }
}