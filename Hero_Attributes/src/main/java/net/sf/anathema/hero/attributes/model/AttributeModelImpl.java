package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.TraitGroup;
import net.sf.anathema.character.main.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.main.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.template.HeroTemplate;
import net.sf.anathema.character.main.template.ITraitTemplateFactory;
import net.sf.anathema.character.main.template.abilities.GroupedTraitType;
import net.sf.anathema.character.main.traits.AttributeTemplateFactory;
import net.sf.anathema.character.main.traits.ITraitTemplate;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.main.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.main.traits.lists.AllAbilityTraitTypeList;
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
import net.sf.anathema.hero.traits.DefaultTraitMap;
import net.sf.anathema.hero.traits.MappedTraitGroup;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.GroupedTraitTypeBuilder;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.hero.traits.template.GroupedTraitsTemplate;
import net.sf.anathema.lib.util.Identifier;

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
    addAttributes(hero.getTemplate().getTraitTemplateCollection().getTraitTemplateFactory());
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    traitModel.addTraits(getAll());
  }

  @Override
  public GroupedTraitType[] getAttributeGroups() {
    return abilityGroups;
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (Trait attribute : getAll()) {
      attribute.addCurrentValueListener(new TraitValueChangedListener(announcer, attribute));
    }
  }

  private FavorableTraitFactory createFactory() {
    return new FavorableTraitFactory(hero);
  }

  private void addAttributes(ITraitTemplateFactory templateFactory) {
    IncrementChecker incrementChecker = new GrumpyIncrementChecker();
    addFavorableTraits(incrementChecker, new AttributeTemplateFactory(templateFactory));
  }

  public void addFavorableTraits(IncrementChecker incrementChecker, TypedTraitTemplateFactory factory) {
    FavorableTraitFactory favorableTraitFactory = createFactory();
    for (IIdentifiedCasteTraitTypeList traitGroup : attributeTraitGroups) {
      Trait[] traits = favorableTraitFactory.createTraits(traitGroup, incrementChecker, factory);
      addTraits(traits);
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
  public IdentifiedTraitTypeList[] getAttributeTypeGroups() {
    return attributeTraitGroups;
  }

  public Trait[] getAll(AttributeGroupType groupType) {
    for (IdentifiedTraitTypeList group : getAttributeTypeGroups()) {
      if (group.getListId().equals(groupType)) {
        return getTraits(group.getAll().toArray(new TraitType[0]));
      }
    }
    return new Trait[0];
  }
}
