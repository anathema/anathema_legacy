package net.sf.anathema.hero.dummy.template;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.ConfiguredModel;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITemplateType;
import net.sf.anathema.character.main.template.ITraitTemplateCollection;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.template.abilities.AbilityGroupType;
import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.template.creation.BonusPointCosts;
import net.sf.anathema.character.main.template.creation.ICreationPoints;
import net.sf.anathema.character.main.template.essence.IEssenceTemplate;
import net.sf.anathema.character.main.template.essence.NullEssenceTemplate;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.character.main.template.magic.CharmTemplate;
import net.sf.anathema.character.main.template.magic.CharmTemplateImpl;
import net.sf.anathema.character.main.template.magic.MartialArtsRulesImpl;
import net.sf.anathema.character.main.template.magic.IMagicTemplate;
import net.sf.anathema.character.main.template.magic.ISpellMagicTemplate;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.hero.dummy.DummyMundaneCharacterType;
import net.sf.anathema.character.main.traits.TraitTemplateCollection;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.traits.types.AttributeGroupType;
import net.sf.anathema.character.main.traits.types.AttributeType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.lib.exception.NotYetImplementedException;

import java.util.ArrayList;
import java.util.List;

public class DummyHeroTemplate implements HeroTemplate {

  public ITraitTemplateCollection traitTemplateCollection = new TraitTemplateCollection(new DummyTraitTemplateFactory());
  public ITemplateType type = new TemplateType(new DummyMundaneCharacterType());
  public IEssenceTemplate essenceTemplate = new NullEssenceTemplate();
  public DummyCreationPoints creationPoints = new DummyCreationPoints();
  public DummyMagicTemplate magicTemplate = new DummyMagicTemplate();

  @Override
  public BonusPointCosts getBonusPointCosts() {
    throw new NotYetImplementedException();
  }

  @Override
  public GroupedTraitType[] getAbilityGroups() {
    AbilityType[] all = AbilityType.values();
    GroupedTraitType[] abilityTypes = new GroupedTraitType[all.length];
    for (int index = 0; index < all.length; index++) {
      abilityTypes[index] = new GroupedTraitType(all[index], AbilityGroupType.Life.getId(), new ArrayList<String>());
    }
    return abilityTypes;
  }

  @Override
  public ITemplateType getTemplateType() {
    return type;
  }

  @Override
  public IExperiencePointCosts getExperienceCost() {
    return null;
  }

  @Override
  public IPresentationProperties getPresentationProperties() {
    throw new NotYetImplementedException();
  }

  @Override
  public ICreationPoints getCreationPoints() {
    return creationPoints;
  }

  @Override
  public IEssenceTemplate getEssenceTemplate() {
    return essenceTemplate;
  }

  @Override
  public IMagicTemplate getMagicTemplate() {
    return magicTemplate;
  }

  @Override
  public ITraitTemplateCollection getTraitTemplateCollection() {
    return traitTemplateCollection;
  }

  @Override
  public TraitType[] getToughnessControllingTraitTypes() {
    return new TraitType[]{AbilityType.Resistance};
  }

  @Override
  public List<ConfiguredModel> getModels() {
    return new ArrayList<>();
  }

  @Override
  public GroupedTraitType[] getAttributeGroups() {
    return new GroupedTraitType[]{new GroupedTraitType(AttributeType.Strength, AttributeGroupType.Physical.getId(), new ArrayList<String>()),
            new GroupedTraitType(AttributeType.Dexterity, AttributeGroupType.Physical.getId(), new ArrayList<String>()),
            new GroupedTraitType(AttributeType.Stamina, AttributeGroupType.Physical.getId(), new ArrayList<String>()),
            new GroupedTraitType(AttributeType.Charisma, AttributeGroupType.Social.getId(), new ArrayList<String>()),
            new GroupedTraitType(AttributeType.Manipulation, AttributeGroupType.Social.getId(), new ArrayList<String>()),
            new GroupedTraitType(AttributeType.Appearance, AttributeGroupType.Social.getId(), new ArrayList<String>()),
            new GroupedTraitType(AttributeType.Perception, AttributeGroupType.Mental.getId(), new ArrayList<String>()),
            new GroupedTraitType(AttributeType.Intelligence, AttributeGroupType.Mental.getId(), new ArrayList<String>()),
            new GroupedTraitType(AttributeType.Wits, AttributeGroupType.Mental.getId(), new ArrayList<String>())};
  }

  @Override
  public String[] getBaseHealthProviders() {
    return new String[0];
  }

  public void setCharacterType(CharacterType characterType) {
    this.type = new TemplateType(characterType);
  }

  private static class DummyMagicTemplate implements IMagicTemplate {
    @Override
    public boolean canBuyFromFreePicks(Magic magic) {
      return true;
    }

    @Override
    public ISpellMagicTemplate getSpellMagic() {
      throw new NotYetImplementedException();
    }

    @Override
    public CharmTemplate getCharmTemplate() {
      return new CharmTemplateImpl(new MartialArtsRulesImpl(MartialArtsLevel.Mortal), false);
    }
  }

}