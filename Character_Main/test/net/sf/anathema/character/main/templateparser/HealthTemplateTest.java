package net.sf.anathema.character.main.templateparser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.main.traits.types.AbilityType;

public class HealthTemplateTest extends TestCase {

  public void testDefaultToughnessTrait() throws Exception {
    assertEquals(AbilityType.Resistance, new GenericHealthTemplate().getToughnessControllingTraits()[0]);
  }
}