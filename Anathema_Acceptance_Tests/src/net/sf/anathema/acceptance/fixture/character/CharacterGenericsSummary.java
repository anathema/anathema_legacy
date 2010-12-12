package net.sf.anathema.acceptance.fixture.character;

import java.util.Map;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class CharacterGenericsSummary {

  protected final Map<Object, Object> summary;

  private static final String KEY_CHARACTER_MODULE_CONTAINER = "characterModuleContainer"; //$NON-NLS-1$

  public CharacterGenericsSummary(Map<Object, Object> summary) {
    this.summary = summary;
  }

  public void setCharacterModuleContainer(CharacterModuleContainer container) {
    summary.put(KEY_CHARACTER_MODULE_CONTAINER, container);
  }

  public CharacterModuleContainer getCharacterModuleContainer() {
    return (CharacterModuleContainer) summary.get(KEY_CHARACTER_MODULE_CONTAINER);
  }

  public ICharacterGenerics getCharacterGenerics() {
    return getCharacterModuleContainer().getCharacterGenerics();
  }

  public ICharacterTemplate createTemplate(String characterType, String subtemplate, String edition) {
    ICharacterType characterTypeObject = CharacterType.getById(characterType);
    ExaltedEdition editionType = ExaltedEdition.valueOf(edition);
    if (StringUtilities.isNullOrEmpty(subtemplate)) {
      return getCharacterGenerics().getTemplateRegistry().getDefaultTemplate(characterTypeObject, editionType);
    }
    IIdentificate subtype = new Identificate(subtemplate);
    ITemplateType templateType = new TemplateType(characterTypeObject, subtype);
    return getCharacterGenerics().getTemplateRegistry().getTemplate(templateType, editionType);
  }
}