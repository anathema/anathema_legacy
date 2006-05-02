package net.sf.anathema.character.sidereal.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.AbstractCharacterTemplate;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMagicTemplate;
import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.additional.IAdditionalTemplate;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.creation.ICreationPoints;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.sidereal.colleges.SiderealCollegeTemplate;
import net.sf.anathema.character.sidereal.presentation.SiderealPresentationProperties;
import net.sf.anathema.character.sidereal.template.trait.SiderealTraitTemplateCollection;
import net.sf.anathema.lib.exception.PersistenceException;

public class DefaultSiderealTemplate extends AbstractCharacterTemplate {

  public static final ITemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.SIDEREAL);
  private final SiderealCreationPoints creationPoints = new SiderealCreationPoints();
  private final SiderealBonusPointCosts bonusPointCosts = new SiderealBonusPointCosts();
  private final SiderealExperienceCosts experienceCosts = new SiderealExperienceCosts();

  private SiderealPresentationProperties presentationProperties;
  private final IAdditionalRules additionalRules;
  private DefaultMagicTemplate magicTemplate;

  public DefaultSiderealTemplate(ICharmCache charmProvider, IAdditionalRules additionalRules)
      throws PersistenceException {
    this.additionalRules = additionalRules;
    ICharmTemplate charmTemplate = new CharmTemplate(
        MartialArtsLevel.Sidereal,
        charmProvider,
        CharacterType.SIDEREAL,
        ExaltedEdition.FirstEdition);
    this.magicTemplate = new DefaultMagicTemplate(charmTemplate, createSpellMagicTemplate());
    this.presentationProperties = new SiderealPresentationProperties();
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return additionalRules;
  }

  public IGroupedTraitType[] getAbilityGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Endurance, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Ride, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Sail, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Survival, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Thrown, SiderealCaste.Journeys.getId(), SiderealCaste.Journeys.getId()),
        new GroupedTraitType(AbilityType.Craft, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Dodge, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Linguistics, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Performance, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Socialize, SiderealCaste.Serenity.getId(), SiderealCaste.Serenity.getId()),
        new GroupedTraitType(AbilityType.Archery, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Brawl, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Melee, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Presence, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Resistance, SiderealCaste.Battles.getId(), SiderealCaste.Battles.getId()),
        new GroupedTraitType(AbilityType.Investigation, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Larceny, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Lore, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Occult, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Stealth, SiderealCaste.Secrets.getId(), SiderealCaste.Secrets.getId()),
        new GroupedTraitType(AbilityType.Athletics, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()),
        new GroupedTraitType(AbilityType.Awareness, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()),
        new GroupedTraitType(AbilityType.Bureaucracy, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()),
        new GroupedTraitType(AbilityType.MartialArts, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()),
        new GroupedTraitType(AbilityType.Medicine, SiderealCaste.Endings.getId(), SiderealCaste.Endings.getId()) };
  }

  public IPresentationProperties getPresentationProperties() {
    return presentationProperties;
  }

  public ITemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  @Override
  public ICasteType[] getAllCasteTypes() {
    return SiderealCaste.values();
  }

  public IBonusPointCosts getBonusPointCosts() {
    return bonusPointCosts;
  }

  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  public IEssenceTemplate getEssenceTemplate() {
    return new SiderealEssenceTemplate();
  }

  public IExperiencePointCosts getExperienceCost() {
    return experienceCosts;
  }

  private ISpellMagicTemplate createSpellMagicTemplate() {
    CircleType[] necromancyCircles = new CircleType[] { CircleType.Shadowlands };
    CircleType[] sorceryCircles = new CircleType[] { CircleType.Terrestrial, CircleType.Celestial };
    return new SpellMagicTemplate(sorceryCircles, necromancyCircles);
  }

  public ITraitTemplateCollection getTraitTemplateCollection() {
    return new TraitTemplateCollection(new SiderealTraitTemplateCollection());
  }

  @Override
  public IAdditionalTemplate[] getAdditionalTemplates() {
    return new IAdditionalTemplate[] { new SiderealCollegeTemplate(
        creationPoints.getCollegeCreationPoints(),
        bonusPointCosts,
        experienceCosts) };
  }

  public IMagicTemplate getMagicTemplate() {
    return magicTemplate;
  }
}