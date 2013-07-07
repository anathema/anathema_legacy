package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveVirtueFlaw;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveVirtueFlawModel;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class SolarVirtueFlawPresenter extends VirtueFlawPresenter {

  private final DescriptiveVirtueFlawView view;
  private final DescriptiveVirtueFlawModel model;

  public SolarVirtueFlawPresenter(Hero hero, Resources resources, DescriptiveVirtueFlawView view, DescriptiveVirtueFlawModel model) {
    super(hero, resources, view, model);
    this.view = view;
    this.model = model;
  }

  @Override
  protected void initAdditionalPresentation() {
    DescriptiveVirtueFlaw virtueFlaw = model.getVirtueFlaw();
    TextualPresentation presentation = new TextualPresentation();
    initConditionPresentation(virtueFlaw, presentation);
    initDescriptionPresentation(virtueFlaw, presentation);
  }

  private void initDescriptionPresentation(DescriptiveVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView descriptionView = view.addTextView(getResources().getString("VirtueFlaw.Description.Name"), 30, 3);
    textualPresentation.initView(descriptionView, virtueFlaw.getDescription());
  }

  private void initConditionPresentation(DescriptiveVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView conditionView = view.addTextView(getResources().getString("VirtueFlaw.LimitBreakCondition.Name"), 30, 2);
    textualPresentation.initView(conditionView, virtueFlaw.getLimitBreak());
  }
}