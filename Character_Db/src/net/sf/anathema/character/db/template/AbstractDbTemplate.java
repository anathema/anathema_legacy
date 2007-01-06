package net.sf.anathema.character.db.template;

import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.template.AbstractCharacterTemplate;
import net.sf.anathema.character.generic.impl.template.essence.DbEssenceTemplate;
import net.sf.anathema.character.generic.impl.template.magic.CharmSet;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMagicTemplate;
import net.sf.anathema.character.generic.impl.template.magic.SpellMagicTemplate;
import net.sf.anathema.character.generic.impl.traits.ITraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.template.abilities.GroupedTraitType;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.template.creation.IBonusPointCosts;
import net.sf.anathema.character.generic.template.essence.IEssenceTemplate;
import net.sf.anathema.character.generic.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.type.CharacterType;

public abstract class AbstractDbTemplate extends AbstractCharacterTemplate {
  private final IBonusPointCosts bonusCosts = new DbBonusPointCosts();
  private final IAdditionalRules rules;

  private final ITraitTemplateCollection traitTemplateCollection;
  private final IPresentationProperties presentationProperties;
  private final IMagicTemplate magicTemplate;

  public final ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }

  @Override
  public IAdditionalRules getAdditionalRules() {
    return rules;
  }

  @Override
  protected ICasteType[] getAllCasteTypes() {
    return DBAspect.values();
  }

  public IGroupedTraitType[] getAbilityGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AbilityType.Linguistics, DBAspect.Air.getId(), DBAspect.Air.getId()),
        new GroupedTraitType(AbilityType.Lore, DBAspect.Air.getId(), DBAspect.Air.getId()),
        new GroupedTraitType(AbilityType.Occult, DBAspect.Air.getId(), DBAspect.Air.getId()),
        new GroupedTraitType(AbilityType.Stealth, DBAspect.Air.getId(), DBAspect.Air.getId()),
        new GroupedTraitType(AbilityType.Thrown, DBAspect.Air.getId(), DBAspect.Air.getId()),
        new GroupedTraitType(AbilityType.Awareness, DBAspect.Earth.getId(), DBAspect.Earth.getId()),
        new GroupedTraitType(AbilityType.Craft, DBAspect.Earth.getId(), DBAspect.Earth.getId()),
        new GroupedTraitType(AbilityType.Endurance, DBAspect.Earth.getId(), DBAspect.Earth.getId()),
        new GroupedTraitType(AbilityType.MartialArts, DBAspect.Earth.getId(), DBAspect.Earth.getId()),
        new GroupedTraitType(AbilityType.Resistance, DBAspect.Earth.getId(), DBAspect.Earth.getId()),
        new GroupedTraitType(AbilityType.Athletics, DBAspect.Fire.getId(), DBAspect.Fire.getId()),
        new GroupedTraitType(AbilityType.Dodge, DBAspect.Fire.getId(), DBAspect.Fire.getId()),
        new GroupedTraitType(AbilityType.Melee, DBAspect.Fire.getId(), DBAspect.Fire.getId()),
        new GroupedTraitType(AbilityType.Presence, DBAspect.Fire.getId(), DBAspect.Fire.getId()),
        new GroupedTraitType(AbilityType.Socialize, DBAspect.Fire.getId(), DBAspect.Fire.getId()),
        new GroupedTraitType(AbilityType.Brawl, DBAspect.Water.getId(), DBAspect.Water.getId()),
        new GroupedTraitType(AbilityType.Bureaucracy, DBAspect.Water.getId(), DBAspect.Water.getId()),
        new GroupedTraitType(AbilityType.Investigation, DBAspect.Water.getId(), DBAspect.Water.getId()),
        new GroupedTraitType(AbilityType.Larceny, DBAspect.Water.getId(), DBAspect.Water.getId()),
        new GroupedTraitType(AbilityType.Sail, DBAspect.Water.getId(), DBAspect.Water.getId()),
        new GroupedTraitType(AbilityType.Archery, DBAspect.Wood.getId(), DBAspect.Wood.getId()),
        new GroupedTraitType(AbilityType.Medicine, DBAspect.Wood.getId(), DBAspect.Wood.getId()),
        new GroupedTraitType(AbilityType.Performance, DBAspect.Wood.getId(), DBAspect.Wood.getId()),
        new GroupedTraitType(AbilityType.Ride, DBAspect.Wood.getId(), DBAspect.Wood.getId()),
        new GroupedTraitType(AbilityType.Survival, DBAspect.Wood.getId(), DBAspect.Wood.getId()) };
  }

  public IBonusPointCosts getBonusPointCosts() {
    return bonusCosts;
  }

  public IEssenceTemplate getEssenceTemplate() {
    return new DbEssenceTemplate();
  }

  public IExperiencePointCosts getExperienceCost() {
    return new DbExperienceCosts();
  }

  public AbstractDbTemplate(ICharmCache charmProvider, IAdditionalRules rules, ITraitTemplateFactory templateFactory) {
    traitTemplateCollection = new TraitTemplateCollection(templateFactory);
    ICharmTemplate charmTemplate = createCharmTemplate(charmProvider);
    ISpellMagicTemplate spellMagic = new SpellMagicTemplate(
        new CircleType[] { CircleType.Terrestrial },
        new CircleType[0]);
    this.magicTemplate = createMagicTemplate(charmTemplate, spellMagic);
    this.rules = rules;
    presentationProperties = new DbPresentationProperties(getTemplateType());
  }

  private ICharmTemplate createCharmTemplate(ICharmCache charmProvider) {
    TerrestrialMartialArtsRules martialArtsRules = new TerrestrialMartialArtsRules();
    martialArtsRules.setHighLevelAtCreation(getHighLevelMartialArtsAtCreation());
    return new CharmTemplate(martialArtsRules, CharmSet.createRegularCharmSet(
        charmProvider,
        CharacterType.DB,
        ExaltedEdition.FirstEdition));
  }

  protected boolean getHighLevelMartialArtsAtCreation() {
    return false;
  }

  protected IMagicTemplate createMagicTemplate(ICharmTemplate charmTemplate, ISpellMagicTemplate spellMagic) {
    return new DefaultMagicTemplate(charmTemplate, spellMagic);
  }

  public IMagicTemplate getMagicTemplate() {
    return magicTemplate;
  }

  public final IPresentationProperties getPresentationProperties() {
    return presentationProperties;
  }
}