package net.sf.anathema.character.generic.framework.xml;

import junit.framework.TestCase;
import net.sf.anathema.character.generic.framework.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;

public class HealthTemplateTest extends TestCase {

  public void testDefaultToughnessTrait() throws Exception {
    assertEquals(AbilityType.Endurance, new GenericHealthTemplate().getToughnessControllingTraits()[0]);
  }  
}