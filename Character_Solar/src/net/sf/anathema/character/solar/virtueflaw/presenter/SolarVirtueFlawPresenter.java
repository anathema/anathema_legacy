package net.sf.anathema.character.solar.virtueflaw.presenter;

import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class SolarVirtueFlawPresenter extends VirtueFlawPresenter {

  private final ISolarVirtueFlawView view;
  private final ISolarVirtueFlawModel model;

  public SolarVirtueFlawPresenter(IResources resources, ISolarVirtueFlawView virtueFlawView, ISolarVirtueFlawModel model) {
    super(resources, virtueFlawView, model);
    this.view = virtueFlawView;
    this.model = model;
  }

  @Override
  protected void initAdditionalPresentation() {
    ISolarVirtueFlaw virtueFlaw = model.getVirtueFlaw();
    initConditionPresentation(virtueFlaw);
    initDescriptionPresentation(virtueFlaw);
  }

  private void initDescriptionPresentation(ISolarVirtueFlaw virtueFlaw) {
    ITextView descriptionView = view.addTextView(getResources().getString("VirtueFlaw.Description.Name"), 30, 3); //$NON-NLS-1$
    TextualPresentation.initView(descriptionView, virtueFlaw.getDescription());
  }

  private void initConditionPresentation(ISolarVirtueFlaw virtueFlaw) {
    ITextView conditionView = view.addTextView(getResources().getString("VirtueFlaw.LimitBreakCondition.Name"), 30, 2); //$NON-NLS-1$
    TextualPresentation.initView(conditionView, virtueFlaw.getLimitBreak());
  }

}