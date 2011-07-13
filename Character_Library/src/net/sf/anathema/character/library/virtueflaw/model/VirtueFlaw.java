package net.sf.anathema.character.library.virtueflaw.model;

import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.LowerableState;
import net.sf.anathema.character.library.trait.LimitedTrait;
import net.sf.anathema.character.library.trait.TraitType;
import net.sf.anathema.character.library.trait.favorable.FriendlyIncrementChecker;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class VirtueFlaw implements IVirtueFlaw {

  private ITraitType root;
  private IDefaultTrait limitTrait;
  private final ITextualDescription name = new SimpleTextualDescription(""); //$NON-NLS-1$
  private final ChangeControl control = new ChangeControl();
  private final ICharacterModelContext context;
  
  public VirtueFlaw(ICharacterModelContext context)
  {
	  this.context = context;
  }

  public ITraitType getRoot() {
    return root;
  }

  public void setRoot(ITraitType root) {
    this.root = root;
    control.fireChangedEvent();
  }
  
  public IDefaultTrait getLimitTrait()
  {
	  if (limitTrait == null)
		  limitTrait = new LimitedTrait(new TraitType(getLimitString()),
				  SimpleTraitTemplate.createStaticLimitedTemplate( 
		          0, 10, LowerableState.LowerableLoss), new FriendlyIncrementChecker(), context.getTraitContext());
	  return limitTrait;
  }
  
  protected String getLimitString()
  {
	  return "VirtueFlaw.LimitTrait";
  }

  public void addRootChangeListener(IChangeListener listener) {
    control.addChangeListener(listener);
  }

  public ITextualDescription getName() {
    return name;
  }

  public boolean isFlawComplete() {
    return !(root == null || name.isEmpty());
  }
}