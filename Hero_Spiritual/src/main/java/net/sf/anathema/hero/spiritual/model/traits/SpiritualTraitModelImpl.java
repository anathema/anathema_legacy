package net.sf.anathema.hero.spiritual.model.traits;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.traits.limitation.TraitLimitation;
import net.sf.anathema.character.main.traits.types.OtherTraitType;
import net.sf.anathema.character.main.traits.types.VirtueType;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.spiritual.SpiritualTraitModel;
import net.sf.anathema.hero.spiritual.template.SpiritualTraitsTemplate;
import net.sf.anathema.hero.traits.DefaultTraitMap;
import net.sf.anathema.hero.traits.TraitModel;
import net.sf.anathema.hero.traits.TraitModelFetcher;
import net.sf.anathema.hero.traits.model.trait.template.TraitLimitationFactory;
import net.sf.anathema.hero.traits.model.trait.template.TraitTemplateMap;
import net.sf.anathema.hero.traits.model.event.TraitValueChangedListener;
import net.sf.anathema.lib.util.Identifier;

public class SpiritualTraitModelImpl extends DefaultTraitMap implements SpiritualTraitModel, HeroModel {

  private SpiritualTraitsTemplate template;

  public SpiritualTraitModelImpl(SpiritualTraitsTemplate template) {
    this.template = template;
  }

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(HeroEnvironment environment, Hero hero) {
    addEssence(hero);
    addVirtues(hero);
    addWillpower(hero);
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
    if (template.willpower.isVirtueBased) {
      new WillpowerListening().initListening(willpower, virtues);
    }
  }

  private void addEssence(Hero hero) {
    SpiritualTraitFactory traitFactory = createTraitFactory(hero);
    addTraits(traitFactory.createTrait(OtherTraitType.Essence));
  }

  private void addVirtues(Hero hero) {
    SpiritualTraitFactory traitFactory = createTraitFactory(hero);
    addTraits(traitFactory.createTraits(VirtueType.values()));
  }

  private void addWillpower(Hero hero) {
    SpiritualTraitFactory traitFactory = createTraitFactory(hero);
    addTraits(traitFactory.createTrait(OtherTraitType.Willpower));
  }

  private SpiritualTraitFactory createTraitFactory(Hero hero) {
    TraitTemplateMap map = new SpiritualTraitTemplateMap(template);
    return new SpiritualTraitFactory(hero, map);
  }

  @Override
  public int getEssenceCap(boolean modified) {
    Trait essence = getTrait(OtherTraitType.Essence);
    return modified ? essence.getModifiedMaximalValue() : essence.getUnmodifiedMaximalValue();
  }

  @Override
  public TraitLimitation getEssenceLimitation() {
    return TraitLimitationFactory.createLimitation(template.essence.limitation);
  }
}
