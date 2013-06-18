package net.sf.anathema.hero.attributes.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.impl.model.traits.AttributeTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.AttributeTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IncrementChecker;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.model.attributes.AttributeModel;
import net.sf.anathema.character.main.model.traits.DefaultTraitMap;
import net.sf.anathema.character.main.model.traits.MappedTraitGroup;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.lib.util.Identifier;

public class AttributeModelImpl extends DefaultTraitMap implements AttributeModel, HeroModel {

  private HeroTemplate template;
  private InitializationContext context;
  private IIdentifiedCasteTraitTypeGroup[] attributeTraitGroups;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    this.template = hero.getTemplate();
    this.context = context;
    this.attributeTraitGroups = new AttributeTypeGroupFactory().createTraitGroups(template.getCasteCollection(), template.getAttributeGroups());
    addAttributes();
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    traitModel.addTraits(getAll());
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (Trait attribute : getAll()) {
      attribute.addCurrentValueListener(new TraitValueChangedListener(announcer, attribute));
    }
  }

  private FavorableTraitFactory createFactory() {
    return new FavorableTraitFactory(context.getTraitContext(), template.getAdditionalRules().getAdditionalTraitRules(),
            context.getBasicCharacterContext(), context.getCharacterListening());
  }

  private void addAttributes() {
    IncrementChecker incrementChecker = new GrumpyIncrementChecker();
    ITraitTemplateFactory templateFactory = template.getTraitTemplateCollection().getTraitTemplateFactory();
    addFavorableTraits(incrementChecker, new AttributeTemplateFactory(templateFactory));
  }

  public void addFavorableTraits(IncrementChecker incrementChecker, TypedTraitTemplateFactory factory) {
    FavorableTraitFactory favorableTraitFactory = createFactory();
    for (IIdentifiedCasteTraitTypeGroup traitGroup : attributeTraitGroups) {
      Trait[] traits = favorableTraitFactory.createTraits(traitGroup, incrementChecker, factory);
      addTraits(traits);
    }
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    TraitGroup[] groups = new TraitGroup[attributeTraitGroups.length];
    for (int index = 0; index < groups.length; index++) {
      final IIdentifiedCasteTraitTypeGroup typeGroup = attributeTraitGroups[index];
      groups[index] = new MappedTraitGroup(this, typeGroup);
    }
    return groups;
  }

  @Override
  public IIdentifiedTraitTypeGroup[] getAttributeTypeGroups() {
    return attributeTraitGroups;
  }
}
