package net.sf.anathema.character.main.othertraits;

import net.sf.anathema.character.generic.additionalrules.IAdditionalTraitRules;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
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
import net.sf.anathema.character.impl.model.traits.listening.WillpowerListening;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.CharacterModel;
import net.sf.anathema.character.main.model.Hero;
import net.sf.anathema.character.main.model.InitializationContext;
import net.sf.anathema.character.main.traits.model.DefaultTraitMap;
import net.sf.anathema.character.main.traits.model.TraitModel;
import net.sf.anathema.lib.util.Identifier;

public class DefaultOtherTraitModel extends DefaultTraitMap implements OtherTraitModel, CharacterModel {

  private Hero hero;
  private HeroTemplate template;

  public DefaultOtherTraitModel(Hero hero, HeroTemplate template, ICharacterModelContext modelContext, TraitModel traitModel) {
    this.hero = hero;
    this.template = template;
    addEssence(modelContext.getTraitContext());
    addVirtues(modelContext.getTraitContext());
    addWillpower(modelContext.getTraitContext());
    connectWillpowerAndVirtues();
    getTrait(OtherTraitType.Essence).addCurrentValueListener(new EssenceLimitationListener(traitModel, hero));
    traitModel.addTraits(getAll());
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
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    //To change body of implemented methods use File | Settings | File Templates.
  }
}
