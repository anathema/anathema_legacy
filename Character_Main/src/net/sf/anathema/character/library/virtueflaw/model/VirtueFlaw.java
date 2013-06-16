package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.TraitType;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;
import org.jmock.example.announcer.Announcer;

public class VirtueFlaw implements IVirtueFlaw {

  private ITraitType root;
  private Trait limitTrait;
  private final ITextualDescription name = new SimpleTextualDescription("");
  private final Announcer<IChangeListener> control = Announcer.to(IChangeListener.class);
  private final ICharacterModelContext context;

  public VirtueFlaw(ICharacterModelContext context) {
    this.context = context;
  }

  @Override
  public ITraitType getRoot() {
    return root;
  }

  @Override
  public void setRoot(ITraitType root) {
    this.root = root;
    control.announce().changeOccurred();
  }

  @Override
  public Trait getLimitTrait() {
    if (limitTrait == null) {
      limitTrait =
              new LimitedTrait(new TraitType(getLimitString()), SimpleTraitTemplate.createStaticLimitedTemplate(0, 10, LowerableState.LowerableLoss),
                      new FriendlyIncrementChecker(), context.getTraitContext());
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