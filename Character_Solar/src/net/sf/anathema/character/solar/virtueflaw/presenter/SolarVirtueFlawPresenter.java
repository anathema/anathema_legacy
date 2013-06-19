package net.sf.anathema.character.solar.virtueflaw.presenter;

import net.sf.anathema.character.library.virtueflaw.model.IDescriptiveVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawView;
import net.sf.anathema.character.library.virtueflaw.presenter.VirtueFlawPresenter;
import net.sf.anathema.character.main.hero.Hero;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class SolarVirtueFlawPresenter extends VirtueFlawPresenter {

  private final IDescriptiveVirtueFlawView view;
  private final IDescriptiveVirtueFlawModel model;

  public SolarVirtueFlawPresenter(Hero hero, Resources resources, IDescriptiveVirtueFlawView view, IDescriptiveVirtueFlawModel model) {
    super(hero, resources, view, model);
    this.view = view;
    this.model = model;
  }

  @Override
  protected void initAdditionalPresentation() {
    IDescriptiveVirtueFlaw virtueFlaw = model.getVirtueFlaw();
    TextualPresentation presentation = new TextualPresentation();
    initConditionPresentation(virtueFlaw, presentation);
    initDescriptionPresentation(virtueFlaw, presentation);
  }

  private void initDescriptionPresentation(IDescriptiveVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView descriptionView = view.addTextView(getResources().getString("VirtueFlaw.Description.Name"), 30, 3);
    textualPresentation.initView(descriptionView, virtueFlaw.getDescription());
  }

  private void initConditionPresentation(IDescriptiveVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView conditionView = view.addTextView(getResources().getString("VirtueFlaw.LimitBreakCondition.Name"), 30, 2);
    textualPresentation.initView(conditionView, virtueFlaw.getLimitBreak());
  }
}