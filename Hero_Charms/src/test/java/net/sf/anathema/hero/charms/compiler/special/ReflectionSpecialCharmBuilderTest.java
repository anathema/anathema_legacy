package net.sf.anathema.hero.charms.compiler.special;

import net.sf.anathema.character.main.magic.parser.dto.special.MultiEffectDto;
import net.sf.anathema.character.main.magic.parser.dto.special.SpecialCharmDto;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.charms.model.special.ISpecialCharm;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReflectionSpecialCharmBuilderTest {

  private ObjectFactory factory = mock(ObjectFactory.class);
  private ArrayList<SpecialCharmBuilder> registeredBuilders = new ArrayList<>();

  @Before
  public void setUpFactory() throws Exception {
    when(factory.instantiateAllImplementers(SpecialCharmBuilder.class)).thenReturn(registeredBuilders);
  }
  
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
    registeredBuilders.add(builder);
  }
}