package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.traits.ITraitTemplate;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.library.trait.DefaultTraitType;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;
import org.jmock.example.announcer.Announcer;

import static net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate.createStaticLimitedTemplate;

public class VirtueFlaw implements IVirtueFlaw {

  private TraitType root;
  private Trait limitTrait;
  private final ITextualDescription name = new SimpleTextualDescription("");
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private Hero hero;

  public VirtueFlaw(Hero hero) {
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
      ITraitTemplate limitedTemplate = createStaticLimitedTemplate(0, 10, LowerableState.LowerableLoss);
      limitTrait = new LimitedTrait(hero, traitType, limitedTemplate, new FriendlyIncrementChecker());
    }
    return limitTrait;
  }

  protected String getLimitString() {
    return "VirtueFlaw.LimitTrait";
  }

  @Override
  public void addRootChangeListener(IChangeListener listener) {
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