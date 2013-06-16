package net.sf.anathema.character.impl.model.temporary;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateFactory;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedCasteTraitTypeGroup;
import net.sf.anathema.character.impl.model.traits.AttributeTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.AttributeTypeGroupFactory;
import net.sf.anathema.character.impl.model.traits.creation.FavorableTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.library.trait.favorable.GrumpyIncrementChecker;
import net.sf.anathema.character.library.trait.favorable.IIncrementChecker;
import net.sf.anathema.character.main.traits.model.HashTraitMap;
import net.sf.anathema.character.main.traits.model.MappedTraitGroup;

public class InternalAttributeConfiguration implements AttributeConfiguration {

  private ICharacterTemplate template;
  private ICharacterModelContext modelContext;
  private final IIdentifiedCasteTraitTypeGroup[] attributeTraitGroups;
  private final HashTraitMap traitMap = new HashTraitMap();

  public InternalAttributeConfiguration(ICharacterTemplate template, ICharacterModelContext modelContext) {
    this.template = template;
    this.modelContext = modelContext;
    this.attributeTraitGroups = new AttributeTypeGroupFactory().createTraitGroups(template.getCasteCollection(), template.getAttributeGroups());
    addAttributes();
  }

  private FavorableTraitFactory createFactory() {
    return new FavorableTraitFactory(modelContext.getTraitContext(), template.getAdditionalRules().getAdditionalTraitRules(),
            modelContext.getBasicCharacterContext(), modelContext.getCharacterListening());
  }

  private void addAttributes() {
    IIncrementChecker incrementChecker = new GrumpyIncrementChecker();
    ITraitTemplateFactory templateFactory = template.getTraitTemplateCollection().getTraitTemplateFactory();
    addFavorableTraits(attributeTraitGroups, incrementChecker, new AttributeTemplateFactory(templateFactory));
  }

  public void addFavorableTraits(IIdentifiedCasteTraitTypeGroup[] traitGroups, IIncrementChecker incrementChecker,
                                 TypedTraitTemplateFactory factory) {
    FavorableTraitFactory favorableTraitFactory = createFactory();
    for (IIdentifiedCasteTraitTypeGroup traitGroup : traitGroups) {
      Trait[] traits = favorableTraitFactory.createTraits(traitGroup, incrementChecker, factory);
      addTraits(traits);
    }
  }

  protected final void addTraits(Trait... traits) {
    for (Trait trait : traits) {
      traitMap.addTrait(trait);
    }
  }

  public IIdentifiedCasteTraitTypeGroup[] getGroups() {
    return attributeTraitGroups;
  }

  public Trait[] getAllAttributes() {
    return traitMap.getAll();
  }

  @Override
  public TraitGroup[] getTraitGroups() {
    TraitGroup[] groups = new TraitGroup[attributeTraitGroups.length];
    for (int index = 0; index < groups.length; index++) {
      final IIdentifiedCasteTraitTypeGroup typeGroup = attributeTraitGroups[index];
      groups[index] = new MappedTraitGroup(traitMap, typeGroup);
    }
    return groups;
  }

}
