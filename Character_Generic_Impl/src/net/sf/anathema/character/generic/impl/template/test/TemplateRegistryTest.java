package net.sf.anathema.character.generic.impl.template.test;

import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.testing.BasicTestCase;

public class TemplateRegistryTest extends BasicTestCase {

  private ITemplateRegistry registry;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.registry = new TemplateRegistry();
  }

  public void testRegisterAndRetrieveTemplate() throws Exception {
    SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(
        CharacterType.MORTAL,
        null,
        ExaltedEdition.FirstEdition);
    registry.register(template);
    assertEquals(template, registry.getTemplate(new TemplateType(CharacterType.MORTAL)));
  }

  public void testRegisterAndRetrieveDefaultTemplate() throws Exception {
    SimpleDummyCharacterTemplate defaultTemplate = new SimpleDummyCharacterTemplate(
        CharacterType.MORTAL,
        null,
        ExaltedEdition.FirstEdition);
    SimpleDummyCharacterTemplate otherTemplate = new SimpleDummyCharacterTemplate(CharacterType.MORTAL, "Second", //$NON-NLS-1$
        ExaltedEdition.FirstEdition);
    registry.register(defaultTemplate);
    registry.register(otherTemplate);
    assertEquals(defaultTemplate, registry.getDefaultTemplate(CharacterType.MORTAL));
  }

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
    ICharacterTemplate[] allSupportedTemplates = registry.getAllSupportedTemplates(CharacterType.MORTAL);
    assertTrue(ArrayUtilities.contains(allSupportedTemplates, defaultTemplate));
    assertTrue(ArrayUtilities.contains(allSupportedTemplates, otherTemplate));
    assertFalse(ArrayUtilities.contains(allSupportedTemplates, unsupportedTemplate));
  }

  public void testRegisterAndRetrieveByRuleset() throws Exception {
//    SimpleDummyCharacterTemplate firstTemplate = new SimpleDummyCharacterTemplate(
//        CharacterType.MORTAL,
//        null,
//        ExaltedEdition.FirstEdition);
//    SimpleDummyCharacterTemplate secondTemplate = new SimpleDummyCharacterTemplate(
//        CharacterType.MORTAL,
//        null,
//        ExaltedEdition.SecondEdition);
//    registry.register(firstTemplate);
//    registry.register(secondTemplate);
//    assertEquals(firstTemplate, registry.getTemplate(
//        new TemplateType(CharacterType.MORTAL),
//        ExaltedEdition.FirstEdition));
//    assertEquals(secondTemplate, registry.getTemplate(
//        new TemplateType(CharacterType.MORTAL),
//        ExaltedEdition.SecondEdition));
  }
}