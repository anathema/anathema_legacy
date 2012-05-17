package net.sf.anathema.character.lunar.virtueflaw.presenter;

import net.sf.anathema.character.library.virtueflaw.model.IDescriptiveVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class ComplexLunarVirtueFlawPresenter extends VirtueFlawPresenter {

  private final IDescriptiveVirtueFlawView view;
  private final IDescriptiveVirtueFlawModel model;

  public ComplexLunarVirtueFlawPresenter(IResources resources, IDescriptiveVirtueFlawView virtueFlawView, IDescriptiveVirtueFlawModel model) {
    super(resources, virtueFlawView, model);
    this.view = virtueFlawView;
    this.model = model;
  }

  @Override
  protected void initAdditionalPresentation() {
    IDescriptiveVirtueFlaw virtueFlaw = model.getVirtueFlaw();
    TextualPresentation presentation = new TextualPresentation();
    initDescriptionPresentation(virtueFlaw, presentation);
  }

  private void initDescriptionPresentation(IDescriptiveVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView descriptionView = view.addTextView(getResources().getString("VirtueFlaw.Description.Name"), 30, 3); //$NON-NLS-1$
    textualPresentation.initView(descriptionView, virtueFlaw.getDescription());
  }
}