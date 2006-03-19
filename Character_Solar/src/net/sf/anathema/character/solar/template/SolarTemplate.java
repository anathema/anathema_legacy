package net.sf.anathema.character.solar.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.template.AbstractCharacterTemplate;
import net.sf.anathema.character.generic.impl.template.essence.DefaultEssenceTemplate;
import net.sf.anathema.character.generic.impl.template.experience.DefaultExperienceCosts;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMagicTemplate;
import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
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
import net.sf.anathema.character.solar.caste.SolarCaste;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.lib.exception.PersistenceException;

public class SolarTemplate extends AbstractCharacterTemplate {

  public static final TemplateType TEMPLATE_TYPE = new TemplateType(CharacterType.SOLAR);

  private final IBonusPointCosts bonusCosts = new DefaultBonusPointCosts();
  private IAdditionalRules additionalRules;
  private ICreationPoints creationPoints = new SolarCreationPoints();
  private final ITraitTemplateCollection traitTemplateCollection = new TraitTemplateCollection(
      new ExaltTraitTemplateFactory());

  private IPresentationProperties presentationProperties;

  private IMagicTemplate magicTemplate;

  public SolarTemplate(ICharmCache charmProvider, IAdditionalRules additionalRules) throws PersistenceException {
    ICharmTemplate charmTemplate = new CharmTemplate(MartialArtsLevel.Celestial, charmProvider, CharacterType.SOLAR);
    ISpellMagicTemplate spellMagic = new SpellMagicTemplate(CircleType.getSorceryCircles(), new CircleType[] {
        CircleType.Shadowlands,
        CircleType.Labyrinth });
    this.magicTemplate = new DefaultMagicTemplate(charmTemplate, spellMagic);
    this.additionalRules = additionalRules;
    presentationProperties = new SolarPresentationProperties();
  }

  public TemplateType getTemplateType() {
    return TEMPLATE_TYPE;
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return additionalRules;
  }

  public IEssenceTemplate getEssenceTemplate() {
    return new DefaultEssenceTemplate();
  }

  public IGroupedTraitType[] getAbilityGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Archery, SolarCaste.Dawn.getId(), SolarCaste.Dawn.getId()),
        new GroupedTraitType(AbilityType.Brawl, SolarCaste.Dawn.getId(), SolarCaste.Dawn.getId()),
        new GroupedTraitType(AbilityType.MartialArts, SolarCaste.Dawn.getId(), SolarCaste.Dawn.getId()),
        new GroupedTraitType(AbilityType.Melee, SolarCaste.Dawn.getId(), SolarCaste.Dawn.getId()),
        new GroupedTraitType(AbilityType.Thrown, SolarCaste.Dawn.getId(), SolarCaste.Dawn.getId()),
        new GroupedTraitType(AbilityType.Endurance, SolarCaste.Zenith.getId(), SolarCaste.Zenith.getId()),
        new GroupedTraitType(AbilityType.Performance, SolarCaste.Zenith.getId(), SolarCaste.Zenith.getId()),
        new GroupedTraitType(AbilityType.Presence, SolarCaste.Zenith.getId(), SolarCaste.Zenith.getId()),
        new GroupedTraitType(AbilityType.Resistance, SolarCaste.Zenith.getId(), SolarCaste.Zenith.getId()),
        new GroupedTraitType(AbilityType.Survival, SolarCaste.Zenith.getId(), SolarCaste.Zenith.getId()),
        new GroupedTraitType(AbilityType.Craft, SolarCaste.Twilight.getId(), SolarCaste.Twilight.getId()),
        new GroupedTraitType(AbilityType.Investigation, SolarCaste.Twilight.getId(), SolarCaste.Twilight.getId()),
        new GroupedTraitType(AbilityType.Lore, SolarCaste.Twilight.getId(), SolarCaste.Twilight.getId()),
        new GroupedTraitType(AbilityType.Medicine, SolarCaste.Twilight.getId(), SolarCaste.Twilight.getId()),
        new GroupedTraitType(AbilityType.Occult, SolarCaste.Twilight.getId(), SolarCaste.Twilight.getId()),
        new GroupedTraitType(AbilityType.Athletics, SolarCaste.Night.getId(), SolarCaste.Night.getId()),
        new GroupedTraitType(AbilityType.Awareness, SolarCaste.Night.getId(), SolarCaste.Night.getId()),
        new GroupedTraitType(AbilityType.Dodge, SolarCaste.Night.getId(), SolarCaste.Night.getId()),
        new GroupedTraitType(AbilityType.Larceny, SolarCaste.Night.getId(), SolarCaste.Night.getId()),
        new GroupedTraitType(AbilityType.Stealth, SolarCaste.Night.getId(), SolarCaste.Night.getId()),
        new GroupedTraitType(AbilityType.Bureaucracy, SolarCaste.Eclipse.getId(), SolarCaste.Eclipse.getId()),
        new GroupedTraitType(AbilityType.Linguistics, SolarCaste.Eclipse.getId(), SolarCaste.Eclipse.getId()),
        new GroupedTraitType(AbilityType.Ride, SolarCaste.Eclipse.getId(), SolarCaste.Eclipse.getId()),
        new GroupedTraitType(AbilityType.Sail, SolarCaste.Eclipse.getId(), SolarCaste.Eclipse.getId()),
        new GroupedTraitType(AbilityType.Socialize, SolarCaste.Eclipse.getId(), SolarCaste.Eclipse.getId()) };
  }

  public IExperiencePointCosts getExperienceCost() {
    return new DefaultExperienceCosts();
  }

  public IPresentationProperties getPresentationProperties() {
    return presentationProperties;
  }

  public ICasteType[] getAllCasteTypes() {
    return SolarCaste.values();
  }

  public IBonusPointCosts getBonusPointCosts() {
    return bonusCosts;
  }

  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  public ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }

  public IMagicTemplate getMagicTemplate() {
    return magicTemplate;
  }

  @Override
  public IAdditionalTemplate[] getAdditionalTemplates() {
    return new IAdditionalTemplate[] { new SolarVirtueFlawTemplate() };
  }
}