package net.sf.anathema.character.infernal.urge.presenter;

import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.character.infernal.urge.view.IInfernalUrgeView;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;
import net.sf.anathema.lib.workflow.textualdescription.model.SimpleTextualDescription;

public class InfernalUrgePresenter
{
	private final IResources resources;
	private final IInfernalUrgeView view;
	private final IInfernalUrgeModel model;
	
  public InfernalUrgePresenter(IResources resources, IInfernalUrgeView urgeView, IInfernalUrgeModel model) {
    this.resources = resources;
    this.view = urgeView;
    this.model = model;
  }

  public void initPresentation() {
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
  }
}