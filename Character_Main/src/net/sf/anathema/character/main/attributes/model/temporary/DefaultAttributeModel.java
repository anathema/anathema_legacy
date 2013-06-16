package net.sf.anathema.character.main.attributes.model.temporary;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
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
import net.sf.anathema.character.main.traits.model.DefaultTraitMap;
import net.sf.anathema.character.main.traits.model.MappedTraitGroup;
import net.sf.anathema.character.main.traits.model.TraitModel;

public class DefaultAttributeModel extends DefaultTraitMap implements AttributeModel {

  private ICharacterTemplate template;
  private ICharacterModelContext modelContext;
  private final IIdentifiedCasteTraitTypeGroup[] attributeTraitGroups;

  public DefaultAttributeModel(ICharacterTemplate template, ICharacterModelContext modelContext, TraitModel traitModel) {
    this.template = template;
    this.modelContext = modelContext;
    this.attributeTraitGroups = new AttributeTypeGroupFactory().createTraitGroups(template.getCasteCollection(), template.getAttributeGroups());
    addAttributes();
    traitModel.addTraits(getAll());
  }

  private FavorableTraitFactory createFactory() {
    return new FavorableTraitFactory(modelContext.getTraitContext(), template.getAdditionalRules().getAdditionalTraitRules(),
            modelContext.getBasicCharacterContext(), modelContext.getCharacterListening());
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
