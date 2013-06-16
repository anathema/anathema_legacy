package net.sf.anathema.character.main.othertraits;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.traits.EssenceLimitationListener;
import net.sf.anathema.character.impl.model.traits.EssenceTemplateFactory;
import net.sf.anathema.character.impl.model.traits.VirtueTemplateFactory;
import net.sf.anathema.character.impl.model.traits.WillpowerTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.impl.model.traits.listening.WillpowerListening;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.traits.model.HashTraitMap;
import net.sf.anathema.character.main.traits.model.TraitModel;

public class DefaultOtherTraitModel implements OtherTraitModel {

  private final HashTraitMap traitMap = new HashTraitMap();
  private ICharacterTemplate template;

  public DefaultOtherTraitModel(ICharacterTemplate template, ICharacterModelContext modelContext, TraitModel traitModel) {
    this.template = template;
    addEssence(modelContext.getTraitContext());
    addVirtues(modelContext.getTraitContext());
    addWillpower(modelContext.getTraitContext());
    connectWillpowerAndVirtues();
    getTrait(OtherTraitType.Essence).addCurrentValueListener(new EssenceLimitationListener(traitModel, modelContext));
    traitModel.addTraits(traitMap.getAll());
  }

  private void connectWillpowerAndVirtues() {
    Trait willpower = traitMap.getTrait(OtherTraitType.Willpower);
    Trait[] virtues = traitMap.getTraits(VirtueType.values());
    if (getAdditionalTraitRules().isWillpowerVirtueBased()) {
      new WillpowerListening().initListening(willpower, virtues);
    } else {
      willpower.setModifiedCreationRange(5, 10);
    }
  }

  @Override
  public Trait[] getTraits(TraitType... traitType) {
    return traitMap.getTraits(traitType);
  }

  @Override
  public Trait getTrait(TraitType type) {
    return traitMap.getTrait(type);
  }

  private void addEssence(ITraitContext traitContext) {
    TypedTraitTemplateFactory templateFactory = new EssenceTemplateFactory(getTemplateCollection().getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, getAdditionalTraitRules(), templateFactory);
    addTraits(traitFactory.createTrait(OtherTraitType.Essence));
  }

  private void addVirtues(ITraitContext traitContext) {
    TypedTraitTemplateFactory templateFactory = new VirtueTemplateFactory(getTemplateCollection().getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, getAdditionalTraitRules(), templateFactory);
    addTraits(traitFactory.createTraits(VirtueType.values()));
  }

  private void addWillpower(ITraitContext traitContext) {
    TypedTraitTemplateFactory templateFactory = new WillpowerTemplateFactory(getTemplateCollection().getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, getAdditionalTraitRules(), templateFactory);
    addTraits(traitFactory.createTrait(OtherTraitType.Willpower));
  }

  protected final void addTraits(Trait... traits) {
    for (Trait trait : traits) {
      traitMap.addTrait(trait);
    }
  }

  private IAdditionalTraitRules getAdditionalTraitRules() {
    return this.template.getAdditionalRules().getAdditionalTraitRules();
  }

  private ITraitTemplateCollection getTemplateCollection() {
    return this.template.getTraitTemplateCollection();
  }
}
