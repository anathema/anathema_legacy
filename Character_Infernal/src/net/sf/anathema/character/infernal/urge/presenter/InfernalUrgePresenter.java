package net.sf.anathema.character.infernal.urge.presenter;

import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.character.infernal.urge.model.InfernalUrge;
import net.sf.anathema.character.infernal.urge.view.IInfernalUrgeView;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class InfernalUrgePresenter extends VirtueFlawPresenter
{
	private final IInfernalUrgeView view;
	private final IInfernalUrgeModel model;
	
  public InfernalUrgePresenter(IResources resources, IInfernalUrgeView urgeView, IInfernalUrgeModel model)
  {
	super(resources, urgeView, model);
    this.view = urgeView;
    this.model = model;
  }
  
  @Override
  protected void initBasicPresentation() {
	    IVirtueFlaw virtueFlaw = model.getVirtueFlaw();
	   	initRootPresentation(virtueFlaw, "Torment.FlawedVirtue.Name");
	  }
  
  @Override
  protected void initAdditionalPresentation() {
    InfernalUrge virtueFlaw = (InfernalUrge) model.getVirtueFlaw();
    TextualPresentation presentation = new TextualPresentation();
    initDescriptionPresentation(virtueFlaw, presentation);
  }
  
  private void initDescriptionPresentation(InfernalUrge virtueFlaw, TextualPresentation textualPresentation) {
	    ITextView descriptionView = view.addTextView(getResources().getString("VirtueFlaw.Description.Name"), 30, 3); //$NON-NLS-1$
	    textualPresentation.initView(descriptionView, virtueFlaw.getDescription());
	  }

  /*public void initPresentation() {
    TextualPresentation presentation = new TextualPresentation();
    final ITextView descriptionView = view.addTextView(resources.getString("InfernalUrge.Title"), 30, 2); //$NON-NLS-1$
    presentation.initView(descriptionView, new SimpleTextualDescription(model.getUrge()));
    
    descriptionView.addTextChangedListener(new IObjectValueChangedListener<String>()
    		{
				@Override
				public void valueChanged(String newValue)
				{
					model.setUrge(newValue);
				}
    		});
  }*/
}