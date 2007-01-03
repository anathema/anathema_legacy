package net.sf.anathema.dummy.character.template;

import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.caste.ICasteCollection;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.impl.caste.CasteCollection;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.impl.rules.ExaltedRuleSet;
import net.sf.anathema.character.generic.impl.template.essence.NullEssenceTemplate;
import net.sf.anathema.character.generic.impl.template.magic.CharmTemplate;
import net.sf.anathema.character.generic.impl.template.magic.DefaultMartialArtsRules;
import net.sf.anathema.character.generic.impl.template.magic.NullCharmSet;
import net.sf.anathema.character.generic.impl.traits.ExaltTraitTemplateFactory;
import net.sf.anathema.character.generic.impl.traits.TraitTemplateCollection;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.MartialArtsLevel;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
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
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.generic.template.points.IAttributeCreationPoints;
import net.sf.anathema.character.generic.template.points.IFavorableTraitCreationPoints;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.data.Range;
import net.sf.anathema.lib.exception.NotYetImplementedException;

public class DummyCharacterTemplate implements ICharacterTemplate {

  private static final String DUMMYGROUP = "DummyGroup"; //$NON-NLS-1$
  private final ITraitTemplateCollection traitTemplateCollection = new TraitTemplateCollection(
      new ExaltTraitTemplateFactory());
  private IExperiencePointCosts experienceCosts;

  public IBonusPointCosts getBonusPointCosts() {
    throw new NotYetImplementedException();
  }

  public IGroupedTraitType[] getAbilityGroups() {
    AbilityType[] all = AbilityType.getAbilityTypes(ExaltedRuleSet.PowerCombat.getEdition());
    IGroupedTraitType[] abilityTypes = new GroupedTraitType[all.length];
    for (int index = 0; index < all.length; index++) {
      abilityTypes[index] = new GroupedTraitType(all[index], DUMMYGROUP, null);
    }
    return abilityTypes;
  }

  public ITemplateType getTemplateType() {
    return new TemplateType(CharacterType.MORTAL);
  }

  public Range getEssenceRange() {
    return new Range(1, 1);
  }

  public IExperiencePointCosts getExperienceCost() {
    return experienceCosts;
  }

  public MartialArtsLevel getMartialArtsLevel() {
    throw new NotYetImplementedException();
  }

  public IPresentationProperties getPresentationProperties() {
    throw new NotYetImplementedException();
  }

  public IAdditionalRules getAdditionalRules() {
    return new DummyAdditionalRules();
  }

  public ICreationPoints getCreationPoints() {
    return new ICreationPoints() {

      public int getBackgroundPointCount() {
        throw new NotYetImplementedException();
      }

      public int getBonusPointCount() {
        throw new NotYetImplementedException();
      }

      public int getVirtueCreationPoints() {
        throw new NotYetImplementedException();
      }

      public IFavorableTraitCreationPoints getAbilityCreationPoints() {
        throw new NotYetImplementedException();
      }

      public IAttributeCreationPoints getAttributeCreationPoints() {
        throw new NotYetImplementedException();
      }

      public int getFavoredCreationCharmCount() {
        throw new NotYetImplementedException();
      }

      public int getDefaultCreationCharmCount() {
        throw new NotYetImplementedException();
      }
    };
  }

  public IEssenceTemplate getEssenceTemplate() {
    return new NullEssenceTemplate();
  }

  public ICasteType[] getAllCasteTypes() {
    return new ICasteType[0];
  }

  public ICasteCollection getCasteCollection() {
    return new CasteCollection(new ICasteType[0]);
  }

  public IMagicTemplate getMagicTemplate() {
    return new IMagicTemplate() {
      public boolean canBuyFromFreePicks(IMagic magic) {
        return true;
      }

      public ISpellMagicTemplate getSpellMagic() {
        throw new NotYetImplementedException();
      }

      public ICharmTemplate getCharmTemplate() {
        return new CharmTemplate(new DefaultMartialArtsRules(MartialArtsLevel.Mortal, false), new NullCharmSet());
      }

      public FavoringTraitType getFavoringTraitType() {
        return FavoringTraitType.AbilityType;
      }
    };
  }

  public ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }

  public ITraitType getToughnessControllingTraitType() {
    return AbilityType.Endurance;
  }

  public IAdditionalTemplate[] getAdditionalTemplates() {
    return new IAdditionalTemplate[0];
  }

  public void setExperienceCosts(IExperiencePointCosts experienceCosts) {
    this.experienceCosts = experienceCosts;
  }

  public IExaltedEdition getEdition() {
    return ExaltedEdition.FirstEdition;
  }

  public IGroupedTraitType[] getAttributeGroups() {
    return new IGroupedTraitType[] {
        new GroupedTraitType(AttributeType.Strength, AttributeGroupType.Physical.getId(), null),
        new GroupedTraitType(AttributeType.Dexterity, AttributeGroupType.Physical.getId(), null),
        new GroupedTraitType(AttributeType.Stamina, AttributeGroupType.Physical.getId(), null),
        new GroupedTraitType(AttributeType.Charisma, AttributeGroupType.Social.getId(), null),
        new GroupedTraitType(AttributeType.Manipulation, AttributeGroupType.Social.getId(), null),
        new GroupedTraitType(AttributeType.Appearance, AttributeGroupType.Social.getId(), null),
        new GroupedTraitType(AttributeType.Perception, AttributeGroupType.Mental.getId(), null),
        new GroupedTraitType(AttributeType.Intelligence, AttributeGroupType.Mental.getId(), null),
        new GroupedTraitType(AttributeType.Wits, AttributeGroupType.Mental.getId(), null), };
  }
}