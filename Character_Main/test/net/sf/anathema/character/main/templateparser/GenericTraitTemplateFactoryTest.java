package net.sf.anathema.character.main.templateparser;

import junit.framework.TestCase;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplate;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.main.traits.limitation.StaticTraitLimitation;

public class GenericTraitTemplateFactoryTest extends TestCase {

  public void testCloneWithWillpower() throws Exception {
    GenericTraitTemplateFactory templateFactory = new GenericTraitTemplateFactory();
    GenericTraitTemplate willpowerTemplate = new GenericTraitTemplate();
    willpowerTemplate.setLimitation(new StaticTraitLimitation(10));
    templateFactory.setWillpowerTemplate(willpowerTemplate);
    GenericTraitTemplateFactory clonedFactory = templateFactory.clone();
    assertEquals(new StaticTraitLimitation(10), clonedFactory.createWillpowerTemplate().getLimitation());
  }
}