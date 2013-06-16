package net.sf.anathema.character.main.template;

import net.sf.anathema.character.main.testing.dummy.DummyMundaneCharacterType;
import net.sf.anathema.character.main.testing.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.template.ICharacterExternalsTemplate;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.TemplateType;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
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
    SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(characterType, null);
    registry.register(template);
    assertEquals(template, registry.getTemplate(new TemplateType(characterType)));
  }

  @Test
  public void testRegisterAndRetrieveDefaultTemplate() throws Exception {
    SimpleDummyCharacterTemplate defaultTemplate = new SimpleDummyCharacterTemplate(characterType, null);
    SimpleDummyCharacterTemplate otherTemplate = new SimpleDummyCharacterTemplate(characterType, "Second");
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    assertEquals(defaultTemplate, registry.getDefaultTemplate(characterType));
    assertNotSame(otherTemplate, registry.getDefaultTemplate(characterType));
  }

  @Test
  public void testRegisterAndRetrieveAllSupportedTemplates() throws Exception {
    ICharacterTemplate defaultTemplate = new SimpleDummyCharacterTemplate(characterType, null);
    ICharacterTemplate otherTemplate = new SimpleDummyCharacterTemplate(characterType, "Second");
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    ICharacterExternalsTemplate[] allSupportedTemplates = registry.getAllSupportedTemplates(characterType);
    assertTrue(ArrayUtils.contains(allSupportedTemplates, defaultTemplate));
    assertTrue(ArrayUtils.contains(allSupportedTemplates, otherTemplate));
  }
}