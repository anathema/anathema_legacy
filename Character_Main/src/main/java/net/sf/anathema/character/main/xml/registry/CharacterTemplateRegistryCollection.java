package net.sf.anathema.character.main.xml.registry;

import net.sf.anathema.character.main.framework.ICharacterTemplateExtensionResourceCache;
import net.sf.anathema.character.main.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.main.xml.GenericCharacterTemplate;
import net.sf.anathema.character.main.xml.abilitygroup.GenericGroupedTraitTypeProvider;
import net.sf.anathema.character.main.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.main.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.main.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.main.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.main.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.main.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.main.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.main.xml.trait.pool.GenericTraitTemplatePool;

public class CharacterTemplateRegistryCollection implements ICharacterTemplateRegistryCollection {

  private final IXmlTemplateRegistry<GenericCharacterTemplate> characterTemplateRegistry;
  private final IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> groupedTraitTypeRegistry;
  private final IXmlTemplateRegistry<GenericEssenceTemplate> essenceRegistry;
  private final IXmlTemplateRegistry<GenericCreationPoints> creationPointsRegistry;
  private final IXmlTemplateRegistry<GenericBonusPointCosts> bonusPointsRegistry;
  private final IXmlTemplateRegistry<GenericExperiencePointCosts> experienceTemplateRegistry;
  private final IXmlTemplateRegistry<GenericTraitTemplateFactory> traitFactoryRegistry;
  private final IXmlTemplateRegistry<GenericTraitTemplatePool> traitTemplatePoolRegistry;
  private final IXmlTemplateRegistry<GenericPresentationTemplate> presentationTemplateRegistry;
  private final IXmlTemplateRegistry<GenericHealthTemplate> healthTemplateRegistry;

  public CharacterTemplateRegistryCollection(ICharacterTemplateExtensionResourceCache cache) {
    characterTemplateRegistry = new XmlTemplateRegistry<>(cache);
    groupedTraitTypeRegistry = new XmlTemplateRegistry<>(cache);
    essenceRegistry = new XmlTemplateRegistry<>(cache);
    creationPointsRegistry = new XmlTemplateRegistry<>(cache);
    bonusPointsRegistry = new XmlTemplateRegistry<>(cache);
    experienceTemplateRegistry = new XmlTemplateRegistry<>(cache);
    traitFactoryRegistry = new XmlTemplateRegistry<>(cache);
    traitTemplatePoolRegistry = new XmlTemplateRegistry<>(cache);
    presentationTemplateRegistry = new XmlTemplateRegistry<>(cache);
    healthTemplateRegistry = new XmlTemplateRegistry<>(cache);
  }

  @Override
  public IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry() {
    return characterTemplateRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> getTraitGroupTemplateRegistry() {
    return groupedTraitTypeRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericEssenceTemplate> getEssenceTemplateRegistry() {
    return essenceRegistry;
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
  public IXmlTemplateRegistry<GenericTraitTemplateFactory> getTraitFactoryRegistry() {
    return traitFactoryRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericTraitTemplatePool> getTraitTemplatePoolRegistry() {
    return traitTemplatePoolRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericPresentationTemplate> getPresentationTemplateRegistry() {
    return presentationTemplateRegistry;
  }

  @Override
  public IXmlTemplateRegistry<GenericHealthTemplate> getHealthTemplateRegistry() {
    return healthTemplateRegistry;
  }
}