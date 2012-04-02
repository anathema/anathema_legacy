package net.sf.anathema.character.generic.template;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

public class TemplateRegistryTest {

  private ITemplateRegistry registry;

  @Before
  public void setUp() throws Exception {
    this.registry = new TemplateRegistry();
  }

  @Test
  public void testRegisterAndRetrieveTemplate() throws Exception {
    SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(CharacterType.MORTAL, null,
            ExaltedEdition.SecondEdition);
    registry.register(template);
    assertEquals(template, registry.getTemplate(new TemplateType(CharacterType.MORTAL), ExaltedEdition.SecondEdition));
  }

  @Test
  public void testRegisterAndRetrieveDefaultTemplate() throws Exception {
    SimpleDummyCharacterTemplate defaultTemplate = new SimpleDummyCharacterTemplate(CharacterType.MORTAL, null,
            ExaltedEdition.SecondEdition);
    SimpleDummyCharacterTemplate otherTemplate = new SimpleDummyCharacterTemplate(CharacterType.MORTAL, "Second",
            //$NON-NLS-1$
            ExaltedEdition.SecondEdition);
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    assertEquals(defaultTemplate, registry.getDefaultTemplate(CharacterType.MORTAL, ExaltedEdition.SecondEdition));
    assertNotSame(otherTemplate, registry.getDefaultTemplate(CharacterType.MORTAL, ExaltedEdition.SecondEdition));
  }

  @Test
  public void testRegisterAndRetrieveAllSupportedTemplates() throws Exception {
    ICharacterTemplate defaultTemplate = new SimpleDummyCharacterTemplate(CharacterType.MORTAL, null,
            ExaltedEdition.SecondEdition);
    ICharacterTemplate otherTemplate = new SimpleDummyCharacterTemplate(CharacterType.MORTAL, "Second", //$NON-NLS-1$
            ExaltedEdition.SecondEdition);
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    ICharacterExternalsTemplate[] allSupportedTemplates = registry.getAllSupportedTemplates(CharacterType.MORTAL);
    assertTrue(ArrayUtilities.containsValue(allSupportedTemplates, defaultTemplate));
    assertTrue(ArrayUtilities.containsValue(allSupportedTemplates, otherTemplate));
  }
}