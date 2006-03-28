package net.sf.anathema.character.generic.impl.template.test;

import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.impl.template.TemplateRegistry;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.testing.BasicTestCase;

public class TemplateRegistryTest extends BasicTestCase {

  private TemplateRegistry registry;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    this.registry = new TemplateRegistry();
  }

  public void testRegisterAndRetrieveTemplate() throws Exception {
    SimpleDummyCharacterTemplate template = new SimpleDummyCharacterTemplate(
        CharacterType.MORTAL,
        null,
        ExaltedRuleSet.getFirstEditionSets());
    registry.register(template);
    assertEquals(template, registry.get(new TemplateType(CharacterType.MORTAL)));
  }
}