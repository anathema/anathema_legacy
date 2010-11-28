package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class HealthTemplateTest extends BasicTestCase{

  public void testDefaultToughnessTrait() throws Exception {
    assertEquals(AbilityType.Endurance, new GenericHealthTemplate().getToughnessControllingTrait());
  }  
}