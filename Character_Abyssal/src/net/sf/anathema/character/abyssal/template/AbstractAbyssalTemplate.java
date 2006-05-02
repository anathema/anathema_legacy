package net.sf.anathema.character.abyssal.template;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.abyssal.template.creation.AbyssalTraitTemplateFactory;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.AbstractCharacterTemplate;
import net.sf.anathema.character.generic.impl.template.essence.DefaultEssenceTemplate;
import net.sf.anathema.character.generic.impl.template.experience.DefaultExperienceCosts;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMagicTemplate;
import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.impl.template.points.DefaultBonusPointCosts;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.exception.PersistenceException;

public abstract class AbstractAbyssalTemplate extends AbstractCharacterTemplate {

  private final IAdditionalRules additionalRules;
  private final IBonusPointCosts bonusCosts = new DefaultBonusPointCosts();
  private final ITraitTemplateCollection traitTemplateCollection = new TraitTemplateCollection(
      new AbyssalTraitTemplateFactory());
  private DefaultMagicTemplate magicTemplate;

  public AbstractAbyssalTemplate(ICharmCache charmProvider, IAdditionalRules additionalRules)
      throws PersistenceException {
    CharmTemplate charmTemplate = new CharmTemplate(
        MartialArtsLevel.Celestial,
        charmProvider,
        CharacterType.ABYSSAL,
        ExaltedEdition.FirstEdition);
    charmTemplate.setCasteAlienAllowed(AbyssalCaste.Moonshadow.getId());
    ISpellMagicTemplate spellMagic = new SpellMagicTemplate(new CircleType[] {
        CircleType.Terrestrial,
        CircleType.Celestial }, CircleType.getNecromancyCircles());
    this.magicTemplate = new DefaultMagicTemplate(charmTemplate, spellMagic);
    this.additionalRules = additionalRules;
  }

  public final IGroupedTraitType[] getAbilityGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Archery, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Brawl, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.MartialArts, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Melee, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Thrown, AbyssalCaste.Dusk.getId(), AbyssalCaste.Dusk.getId()),
        new GroupedTraitType(AbilityType.Endurance, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Performance, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Presence, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Resistance, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Survival, AbyssalCaste.Midnight.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Craft, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Midnight.getId()),
        new GroupedTraitType(AbilityType.Investigation, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()),
        new GroupedTraitType(AbilityType.Lore, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()),
        new GroupedTraitType(AbilityType.Medicine, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()),
        new GroupedTraitType(AbilityType.Occult, AbyssalCaste.Daybreak.getId(), AbyssalCaste.Daybreak.getId()),
        new GroupedTraitType(AbilityType.Athletics, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Awareness, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Dodge, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Larceny, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Stealth, AbyssalCaste.Day.getId(), AbyssalCaste.Day.getId()),
        new GroupedTraitType(AbilityType.Bureaucracy, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.Linguistics, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.Ride, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.Sail, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()),
        new GroupedTraitType(AbilityType.Socialize, AbyssalCaste.Moonshadow.getId(), AbyssalCaste.Moonshadow.getId()) };
  }

  @Override
  public final IAdditionalRules getAdditionalRules() {
    return additionalRules;
  }

  @Override
  public final ICasteType[] getAllCasteTypes() {
    return AbyssalCaste.values();
  }

  public final IBonusPointCosts getBonusPointCosts() {
    return bonusCosts;
  }

  public final IEssenceTemplate getEssenceTemplate() {
    return new DefaultEssenceTemplate();
  }

  public final IExperiencePointCosts getExperienceCost() {
    return new DefaultExperienceCosts();
  }

  public final ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }

  public IMagicTemplate getMagicTemplate() {
    return magicTemplate;
  }
}