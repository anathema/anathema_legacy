package net.sf.anathema.character.generic.framework.xml.registry;

import net.sf.anathema.character.generic.framework.ICharacterTemplateRegistryCollection;
import net.sf.anathema.character.generic.framework.xml.GenericCharacterTemplate;
import net.sf.anathema.character.generic.framework.xml.abilitygroup.GenericGroupedTraitTypeProvider;
import net.sf.anathema.character.generic.framework.xml.creation.GenericBonusPointCosts;
import net.sf.anathema.character.generic.framework.xml.creation.GenericCreationPoints;
import net.sf.anathema.character.generic.framework.xml.essence.GenericEssenceTemplate;
import net.sf.anathema.character.generic.framework.xml.experience.GenericExperiencePointCosts;
import net.sf.anathema.character.generic.framework.xml.health.GenericHealthTemplate;
import net.sf.anathema.character.generic.framework.xml.magic.GenericMagicTemplate;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericCharmPresentationProperties;
import net.sf.anathema.character.generic.framework.xml.presentation.GenericPresentationTemplate;
import net.sf.anathema.character.generic.framework.xml.rules.GenericAdditionalRules;
import net.sf.anathema.character.generic.framework.xml.trait.GenericTraitTemplateFactory;
import net.sf.anathema.character.generic.framework.xml.trait.pool.GenericTraitTemplatePool;

public class CharacterTemplateRegistryCollection implements ICharacterTemplateRegistryCollection {

  private final IXmlTemplateRegistry<GenericCharacterTemplate> characterTemplateRegistry = new XmlTemplateRegistry<GenericCharacterTemplate>();
  private final IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> groupedTraitTypeRegistry = new XmlTemplateRegistry<GenericGroupedTraitTypeProvider>();
  private final IXmlTemplateRegistry<GenericEssenceTemplate> essenceRegistry = new XmlTemplateRegistry<GenericEssenceTemplate>();
  private final IXmlTemplateRegistry<GenericCreationPoints> creationPointsRegistry = new XmlTemplateRegistry<GenericCreationPoints>();
  private final IXmlTemplateRegistry<GenericBonusPointCosts> bonusPointsRegistry = new XmlTemplateRegistry<GenericBonusPointCosts>();
  private final IXmlTemplateRegistry<GenericExperiencePointCosts> experienceTemplateRegistry = new XmlTemplateRegistry<GenericExperiencePointCosts>();
  private final IXmlTemplateRegistry<GenericTraitTemplateFactory> traitFactoryRegistry = new XmlTemplateRegistry<GenericTraitTemplateFactory>();
  private final IXmlTemplateRegistry<GenericTraitTemplatePool> traitTemplatePoolRegistry = new XmlTemplateRegistry<GenericTraitTemplatePool>();
  private final IXmlTemplateRegistry<GenericMagicTemplate> magicTemplateRegistry = new XmlTemplateRegistry<GenericMagicTemplate>();
  private final IXmlTemplateRegistry<GenericPresentationTemplate> presentationTemplateRegistry = new XmlTemplateRegistry<GenericPresentationTemplate>();
  private final IXmlTemplateRegistry<GenericCharmPresentationProperties> charmPresentationTemplateRegistry = new XmlTemplateRegistry<GenericCharmPresentationProperties>();
  private final IXmlTemplateRegistry<GenericHealthTemplate> healthTemplateRegistry = new XmlTemplateRegistry<GenericHealthTemplate>();
  private final IXmlTemplateRegistry<GenericAdditionalRules> rulesRegistry = new XmlTemplateRegistry<GenericAdditionalRules>();

  public IXmlTemplateRegistry<GenericCharacterTemplate> getCharacterTemplateRegistry() {
    return characterTemplateRegistry;
  }

  public IXmlTemplateRegistry<GenericGroupedTraitTypeProvider> getTraitGroupTemplateRegistry() {
    return groupedTraitTypeRegistry;
  }

  public IXmlTemplateRegistry<GenericEssenceTemplate> getEssenceTemplateRegistry() {
    return essenceRegistry;
  }

  public IXmlTemplateRegistry<GenericCreationPoints> getCreationPointTemplateRegistry() {
    return creationPointsRegistry;
  }

  public IXmlTemplateRegistry<GenericBonusPointCosts> getBonusPointTemplateRegistry() {
    return bonusPointsRegistry;
  }

  public IXmlTemplateRegistry<GenericExperiencePointCosts> getExperienceTemplateRegistry() {
    return experienceTemplateRegistry;
  }

  public IXmlTemplateRegistry<GenericTraitTemplateFactory> getTraitFactoryRegistry() {
    return traitFactoryRegistry;
  }

  public IXmlTemplateRegistry<GenericTraitTemplatePool> getTraitTemplatePoolRegistry() {
    return traitTemplatePoolRegistry;
  }

  public IXmlTemplateRegistry<GenericMagicTemplate> getMagicTemplateRegistry() {
    return magicTemplateRegistry;
  }

  public IXmlTemplateRegistry<GenericPresentationTemplate> getPresentationTemplateRegistry() {
    return presentationTemplateRegistry;
  }

  public IXmlTemplateRegistry<GenericCharmPresentationProperties> getCharmPresentationTemplateRegistry() {
    return charmPresentationTemplateRegistry;
  }

  public IXmlTemplateRegistry<GenericHealthTemplate> getHealthTemplateRegistry() {
    return healthTemplateRegistry;
  }

  public IXmlTemplateRegistry<GenericAdditionalRules> getAdditionalRulesRegistry() {
    return rulesRegistry;
  }
}