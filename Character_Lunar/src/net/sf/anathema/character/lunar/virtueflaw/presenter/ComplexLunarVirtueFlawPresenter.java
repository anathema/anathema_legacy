package net.sf.anathema.character.lunar.virtueflaw.presenter;

import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.character.lunar.virtueflaw.model.ILunarVirtueFlaw;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class ComplexLunarVirtueFlawPresenter extends VirtueFlawPresenter {

  private final ILunarVirtueFlawView view;
  private final ILunarVirtueFlawModel model;

  public ComplexLunarVirtueFlawPresenter(IResources resources, ILunarVirtueFlawView virtueFlawView, ILunarVirtueFlawModel model) {
    super(resources, virtueFlawView, model);
    this.view = virtueFlawView;
    this.model = model;
  }

  @Override
  protected void initAdditionalPresentation() {
    ILunarVirtueFlaw virtueFlaw = model.getVirtueFlaw();
    TextualPresentation presentation = new TextualPresentation();
    initDescriptionPresentation(virtueFlaw, presentation);
  }

  private void initDescriptionPresentation(ILunarVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView descriptionView = view.addTextView(getResources().getString("VirtueFlaw.Description.Name"), 30, 3); //$NON-NLS-1$
    textualPresentation.initView(descriptionView, virtueFlaw.getDescription());
  }
}