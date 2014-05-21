package net.sf.anathema.character.main.xml.registry;

import net.sf.anathema.character.main.framework.ICharacterTemplateExtensionResourceCache;
import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.xml.GenericCharacterTemplate;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;

public class CharacterTemplateRegistryCollection implements ICharacterTemplateRegistryCollection {

  private final IXmlTemplateRegistry<GenericCharacterTemplate> characterTemplateRegistry;
  private final IXmlTemplateRegistry<GenericCreationPoints> creationPointsRegistry;
  private final IXmlTemplateRegistry<GenericBonusPointCosts> bonusPointsRegistry;
  private final IXmlTemplateRegistry<GenericExperiencePointCosts> experienceTemplateRegistry;
  private final IXmlTemplateRegistry<GenericPresentationTemplate> presentationTemplateRegistry;

  public CharacterTemplateRegistryCollection(ICharacterTemplateExtensionResourceCache cache) {
    characterTemplateRegistry = new XmlTemplateRegistry<>(cache);
    creationPointsRegistry = new XmlTemplateRegistry<>(cache);
    bonusPointsRegistry = new XmlTemplateRegistry<>(cache);
    experienceTemplateRegistry = new XmlTemplateRegistry<>(cache);
    presentationTemplateRegistry = new XmlTemplateRegistry<>(cache);
  }

  @Override
  public IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry() {
    return characterTemplateRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericCreationPoints> getCreationPointTemplateRegistry() {
    return creationPointsRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericBonusPointCosts> getBonusPointTemplateRegistry() {
    return bonusPointsRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericExperiencePointCosts> getExperienceTemplateRegistry() {
    return experienceTemplateRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericPresentationTemplate> getPresentationTemplateRegistry() {
    return presentationTemplateRegistry;
  }
}