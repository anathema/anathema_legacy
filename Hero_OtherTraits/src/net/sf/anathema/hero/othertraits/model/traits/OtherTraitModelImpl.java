package net.sf.anathema.hero.othertraits.model.traits;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.generic.template.ITraitTemplateCollection;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.impl.model.traits.EssenceLimitationListener;
import net.sf.anathema.character.impl.model.traits.EssenceTemplateFactory;
import net.sf.anathema.character.impl.model.traits.VirtueTemplateFactory;
import net.sf.anathema.character.impl.model.traits.WillpowerTemplateFactory;
import net.sf.anathema.character.impl.model.traits.creation.DefaultTraitFactory;
import net.sf.anathema.character.impl.model.traits.creation.TypedTraitTemplateFactory;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.character.main.hero.HeroModel;
import net.sf.anathema.character.main.hero.InitializationContext;
import net.sf.anathema.character.main.model.othertraits.OtherTraitModel;
import net.sf.anathema.character.main.model.traits.DefaultTraitMap;
import net.sf.anathema.character.main.model.traits.TraitModel;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.lib.util.Identifier;

public class OtherTraitModelImpl extends DefaultTraitMap implements OtherTraitModel, HeroModel {

  private HeroTemplate template;

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    this.template = hero.getTemplate();
    addEssence(context.getTraitContext());
    addVirtues(context.getTraitContext());
    addWillpower(context.getTraitContext());
    connectWillpowerAndVirtues();
    TraitModel traitModel = TraitModelFetcher.fetch(hero);
    getTrait(OtherTraitType.Essence).addCurrentValueListener(new EssenceLimitationListener(traitModel, hero));
    traitModel.addTraits(getAll());
  }

  @Override
  public void initializeListening(ChangeAnnouncer announcer) {
    for (Trait trait : getAll()) {
      trait.addCurrentValueListener(new TraitValueChangedListener(announcer, trait));
    }
  }

  private void connectWillpowerAndVirtues() {
    Trait willpower = getTrait(OtherTraitType.Willpower);
    Trait[] virtues = getTraits(VirtueType.values());
    if (getAdditionalTraitRules().isWillpowerVirtueBased()) {
      new WillpowerListening().initListening(willpower, virtues);
    } else {
      willpower.setModifiedCreationRange(5, 10);
    }
  }

  private void addEssence(TraitContext traitContext) {
    TypedTraitTemplateFactory templateFactory = new EssenceTemplateFactory(getTemplateCollection().getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, getAdditionalTraitRules(), templateFactory);
    addTraits(traitFactory.createTrait(OtherTraitType.Essence));
  }

  private void addVirtues(TraitContext traitContext) {
    TypedTraitTemplateFactory templateFactory = new VirtueTemplateFactory(getTemplateCollection().getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, getAdditionalTraitRules(), templateFactory);
    addTraits(traitFactory.createTraits(VirtueType.values()));
  }

  private void addWillpower(TraitContext traitContext) {
    TypedTraitTemplateFactory templateFactory = new WillpowerTemplateFactory(getTemplateCollection().getTraitTemplateFactory());
    DefaultTraitFactory traitFactory = new DefaultTraitFactory(traitContext, getAdditionalTraitRules(), templateFactory);
    addTraits(traitFactory.createTrait(OtherTraitType.Willpower));
  }

  private IAdditionalTraitRules getAdditionalTraitRules() {
    return this.template.getAdditionalRules().getAdditionalTraitRules();
  }

  private ITraitTemplateCollection getTemplateCollection() {
    return this.template.getTraitTemplateCollection();
  }

  @Override
  public int getEssenceCap(boolean modified) {
    Trait essence = getTrait(OtherTraitType.Essence);
    return modified ? essence.getModifiedMaximalValue() : essence.getUnmodifiedMaximalValue();
  }
}
