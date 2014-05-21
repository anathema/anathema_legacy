package net.sf.anathema.herotype.solar.model.curse;

import net.sf.anathema.character.main.library.trait.DefaultTraitType;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.hero.traits.model.trait.ModificationType;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.traits.model.trait.LimitedTrait;
import net.sf.anathema.hero.traits.template.TraitTemplate;
import net.sf.anathema.hero.traits.template.TraitTemplateFactory;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;
import org.jmock.example.announcer.Announcer;

public class VirtueFlawImpl implements VirtueFlaw {

  private TraitType root;
  private Trait limitTrait;
  private final ITextualDescription name = new SimpleTextualDescription("");
  private final Announcer<ChangeListener> control = Announcer.to(ChangeListener.class);
  private Hero hero;

  public VirtueFlawImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public TraitType getRoot() {
    return root;
  }

  @Override
  public void setRoot(TraitType root) {
    this.root = root;
    control.announce().changeOccurred();
  }

  @Override
  public Trait getLimitTrait() {
    if (limitTrait == null) {
      DefaultTraitType traitType = new DefaultTraitType(getLimitString());
      TraitTemplate limitedTemplate = TraitTemplateFactory.createStaticLimitedTemplate(0, 10, ModificationType.Free);
      limitTrait = new LimitedTrait(hero, traitType, limitedTemplate, new FriendlyIncrementChecker());
    }
    return limitTrait;
  }

  protected String getLimitString() {
    return "VirtueFlaw.LimitTrait";
  }

  @Override
  public void addRootChangeListener(ChangeListener listener) {
    control.addListener(listener);
  }

  @Override
  public ITextualDescription getName() {
    return name;
  }

  @Override
  public boolean isFlawComplete() {
    return !(root == null || name.isEmpty());
  }
}