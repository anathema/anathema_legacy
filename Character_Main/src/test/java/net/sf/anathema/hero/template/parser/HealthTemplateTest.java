package net.sf.anathema.hero.template.parser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.xml.health.GenericHealthTemplate;

public class HealthTemplateTest extends TestCase {

  public void testDefaultToughnessTrait() throws Exception {
    assertEquals(AbilityType.Resistance, new GenericHealthTemplate().getToughnessControllingTraits()[0]);
  }
}