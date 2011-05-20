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
    TextualPresentation presentation = new TextualPresentation();
    initConditionPresentation(virtueFlaw, presentation);
    initDescriptionPresentation(virtueFlaw, presentation);
  }

  private void initDescriptionPresentation(ISolarVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView descriptionView = view.addTextView(getResources().getString("VirtueFlaw.Description.Name"), 30, 3); //$NON-NLS-1$
    textualPresentation.initView(descriptionView, virtueFlaw.getDescription());
  }

  private void initConditionPresentation(ISolarVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView conditionView = view.addTextView(getResources().getString("VirtueFlaw.LimitBreakCondition.Name"), 30, 2); //$NON-NLS-1$
    textualPresentation.initView(conditionView, virtueFlaw.getLimitBreak());
  }
}