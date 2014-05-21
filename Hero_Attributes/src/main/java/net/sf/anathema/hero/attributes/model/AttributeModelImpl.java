package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitGroup;
import net.sf.anathema.character.main.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.traits.limitation.TraitLimitation;
import net.sf.anathema.hero.traits.model.GroupedTraitType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.lists.AllAttributeTraitTypeList;
import net.sf.anathema.character.main.traits.lists.IIdentifiedCasteTraitTypeList;
import net.sf.anathema.character.main.traits.lists.IdentifiedTraitTypeList;
import net.sf.anathema.character.main.traits.types.AttributeGroupType;
import net.sf.anathema.hero.concept.CasteCollection;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.traits.model.DefaultTraitMap;
import net.sf.anathema.hero.traits.MappedTraitGroup;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.group.GroupedTraitTypeBuilder;
import net.sf.anathema.hero.traits.model.TraitFactory;
import net.sf.anathema.hero.traits.model.trait.template.TraitLimitationFactory;
import net.sf.anathema.hero.traits.model.trait.template.TraitTemplateMap;
import net.sf.anathema.hero.traits.model.trait.template.TraitTemplateMapImpl;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.lib.util.Identifier;

import java.util.List;

public class AttributeModelImpl extends DefaultTraitMap implements AttributeModel, HeroModel {

  private IIdentifiedCasteTraitTypeList[] attributeTraitGroups;
  private Hero hero;
  private GroupedTraitType[] abilityGroups;
  private GroupedTraitsTemplate template;

  public AttributeModelImpl(GroupedTraitsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    this.hero = hero;
    CasteCollection casteCollection = HeroConceptFetcher.fetch(hero).getCasteCollection();
    this.abilityGroups = GroupedTraitTypeBuilder.BuildFor(template, AllAttributeTraitTypeList.getInstance());
    this.attributeTraitGroups = new AttributeTypeGroupFactory().createTraitGroups(casteCollection, getAttributeGroups());
    addAttributes();
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    traitModel.addTraits(getAll());
  }

  private void addAttributes() {
    IncrementChecker incrementChecker = new GrumpyIncrementChecker();
    TraitFactory traitFactory = new TraitFactory(this.hero);
    for (IIdentifiedCasteTraitTypeList traitGroup : attributeTraitGroups) {
      TraitTemplateMap map = new TraitTemplateMapImpl(template);
      Trait[] traits = traitFactory.createTraits(traitGroup, incrementChecker, map);
      addTraits(traits);
    }
  }

  @Override
  public GroupedTraitType[] getAttributeGroups() {
    return abilityGroups;
  }

  @Override
  public int getTraitMaximum() {
    TraitLimitation limitation = TraitLimitationFactory.createLimitation(template.standard.limitation);
    return limitation.getAbsoluteLimit(hero);
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (Trait attribute : getAll()) {
      attribute.addCurrentValueListener(new TraitValueChangedListener(announcer, attribute));
    }
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    TraitGroup[] groups = new TraitGroup[attributeTraitGroups.length];
    for (int index = 0; index < groups.length; index++) {
      final IIdentifiedCasteTraitTypeList typeGroup = attributeTraitGroups[index];
      groups[index] = new MappedTraitGroup(this, typeGroup);
    }
    return groups;
  }

  @Override
  public IdentifiedTraitTypeList[] getTraitTypeList() {
    return attributeTraitGroups;
  }

  public Trait[] getAll(AttributeGroupType groupType) {
    for (IdentifiedTraitTypeList group : getTraitTypeList()) {
      if (group.getListId().equals(groupType)) {
        List<TraitType> all = group.getAll();
        return getTraits(all.toArray(new TraitType[all.size()]));
      }
    }
    return new Trait[0];
  }
}
