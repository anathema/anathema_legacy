package net.sf.anathema.herotype.solar.display.curse;

import net.sf.anathema.fx.hero.configurableview.ConfigurableCharacterView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveVirtueFlaw;
import net.sf.anathema.herotype.solar.model.curse.DescriptiveVirtueFlawModel;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.lib.workflow.textualdescription.TextualPresentation;

public class SolarVirtueFlawPresenter extends VirtueFlawPresenter {

  private final ConfigurableCharacterView view;
  private final DescriptiveVirtueFlawModel model;

  public SolarVirtueFlawPresenter(Hero hero, Resources resources, ConfigurableCharacterView view, DescriptiveVirtueFlawModel model) {
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
    ITextView descriptionView = view.addAreaView(getResources().getString("VirtueFlaw.Description.Name"));
    textualPresentation.initView(descriptionView, virtueFlaw.getDescription());
  }

  private void initConditionPresentation(DescriptiveVirtueFlaw virtueFlaw, TextualPresentation textualPresentation) {
    ITextView conditionView = view.addAreaView(getResources().getString("VirtueFlaw.LimitBreakCondition.Name"));
    textualPresentation.initView(conditionView, virtueFlaw.getLimitBreak());
  }
}