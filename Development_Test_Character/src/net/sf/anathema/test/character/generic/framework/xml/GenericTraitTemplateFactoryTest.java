package net.sf.anathema.test.character.generic.framework.xml;

import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.limitation.StaticTraitLimitation;
import net.sf.anathema.lib.testing.BasicTestCase;

public class GenericTraitTemplateFactoryTest extends BasicTestCase {

  public void testCloneWithWillpower() throws Exception {
    GenericTraitTemplateFactory templateFactory = new GenericTraitTemplateFactory();
    GenericTraitTemplate willpowerTemplate = new GenericTraitTemplate();
    willpowerTemplate.setLimitation(new StaticTraitLimitation(10));
    templateFactory.setWillpowerTemplate(willpowerTemplate);
    GenericTraitTemplateFactory clonedFactory = templateFactory.clone();
    assertEquals(new StaticTraitLimitation(10), clonedFactory.createWillpowerTemplate().getLimitation());
  }
}