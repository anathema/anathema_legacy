package net.sf.anathema.character.generic.template;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.dummy.template.DummyUnsupportedTemplate;
import net.sf.anathema.character.generic.dummy.template.SimpleDummyCharacterTemplate;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TemplateRegistryTest {

  private ITemplateRegistry registry;

  @Before
  public void setUp() throws Exception {
    this.registry = new TemplateRegistry();
  }

  @Test
  public void testRegisterAndRetrieveTemplate() throws Exception {
    SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(
      CharacterType.MORTAL,
      null,
      ExaltedEdition.FirstEdition);
    registry.register(template);
    assertEquals(template, registry.getTemplate(new TemplateType(CharacterType.MORTAL), ExaltedEdition.FirstEdition));
  }

  @Test
  public void testRegisterAndRetrieveDefaultTemplate() throws Exception {
    SimpleDummyCharacterTemplate defaultTemplate = new SimpleDummyCharacterTemplate(
      CharacterType.MORTAL,
      null,
      ExaltedEdition.FirstEdition);
    SimpleDummyCharacterTemplate otherTemplate = new SimpleDummyCharacterTemplate(CharacterType.MORTAL, "Second", //$NON-NLS-1$
      ExaltedEdition.FirstEdition);
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    assertEquals(defaultTemplate, registry.getDefaultTemplate(CharacterType.MORTAL, ExaltedEdition.FirstEdition));
    assertNotSame(otherTemplate, registry.getDefaultTemplate(CharacterType.MORTAL, ExaltedEdition.FirstEdition));
  }

  @Test
  public void testRegisterAndRetrieveAllSupportedTemplates() throws Exception {
    ICharacterTemplate defaultTemplate = new SimpleDummyCharacterTemplate(
      CharacterType.MORTAL,
      null,
      ExaltedEdition.FirstEdition);
    ICharacterTemplate otherTemplate = new SimpleDummyCharacterTemplate(CharacterType.MORTAL, "Second", //$NON-NLS-1$
      ExaltedEdition.FirstEdition);
    ICharacterTemplate unsupportedTemplate = new DummyUnsupportedTemplate(CharacterType.MORTAL, "Third", //$NON-NLS-1$
      ExaltedEdition.FirstEdition);
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    registry.register(unsupportedTemplate);
    ICharacterExternalsTemplate[] allSupportedTemplates = registry.getAllSupportedTemplates(CharacterType.MORTAL);
    assertTrue(ArrayUtilities.contains(allSupportedTemplates, defaultTemplate));
    assertTrue(ArrayUtilities.contains(allSupportedTemplates, otherTemplate));
    assertFalse(ArrayUtilities.contains(allSupportedTemplates, unsupportedTemplate));
  }

  @Test
  public void testRegisterAndRetrieveByRuleset() throws Exception {
    SimpleDummyCharacterTemplate firstTemplate = new SimpleDummyCharacterTemplate(
      CharacterType.MORTAL,
      null,
      ExaltedEdition.FirstEdition);
    SimpleDummyCharacterTemplate secondTemplate = new SimpleDummyCharacterTemplate(
      CharacterType.MORTAL,
      null,
      ExaltedEdition.SecondEdition);
    registry.register(firstTemplate);
    registry.register(secondTemplate);
    assertEquals(firstTemplate, registry.getTemplate(
      new TemplateType(CharacterType.MORTAL),
      ExaltedEdition.FirstEdition));
    assertEquals(secondTemplate, registry.getTemplate(
      new TemplateType(CharacterType.MORTAL),
      ExaltedEdition.SecondEdition));
  }
}