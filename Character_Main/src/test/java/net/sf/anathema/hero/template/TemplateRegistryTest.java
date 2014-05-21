package net.sf.anathema.hero.template;

import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.hero.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemplateRegistryTest {

  private DummyMundaneCharacterType characterType = new DummyMundaneCharacterType();
  private ITemplateRegistry registry;

  @Before
  public void setUp() throws Exception {
    this.registry = new TemplateRegistry();
  }

  @Test
  public void testRegisterAndRetrieveTemplate() throws Exception {
    SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(characterType, "Test");
    registry.register(template);
    assertEquals(template, registry.getTemplate(new TemplateTypeImpl(characterType, new SimpleIdentifier("Test"))));
  }

  @Test
  public void testRegisterAndRetrieveAllSupportedTemplates() throws Exception {
    HeroTemplate defaultTemplate = new SimpleDummyCharacterTemplate(characterType, null);
    HeroTemplate otherTemplate = new SimpleDummyCharacterTemplate(characterType, "Second");
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    HeroTemplate[] allSupportedTemplates = registry.getAllSupportedTemplates(characterType);
    assertTrue(ArrayUtils.contains(allSupportedTemplates, defaultTemplate));
    assertTrue(ArrayUtils.contains(allSupportedTemplates, otherTemplate));
  }
}