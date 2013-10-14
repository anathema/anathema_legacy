package net.sf.anathema.charmdatabase.view.fx;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.view.CharmBasicsPanel;
import net.sf.anathema.charmdatabase.view.CharmDetails;
import net.sf.anathema.charmdatabase.view.info.CharmInformationPanel;
import net.sf.anathema.charmdatabase.view.info.fx.FxCharmInformationPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmRulesPanel;
import net.sf.anathema.charmdatabase.view.rules.fx.FxCharmRulesPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;

public class FxCharmDetails extends AbstractFxContainerPanel implements CharmDetails {

  private final static String BASICS_HEIGHT = "20%";
  private final static String RULES_HEIGHT = "60%";
  private final static String INFO_HEIGHT = "20%";

  //private final FxCharmListView<IEquipmentStats> listView = new FxCharmListView<>();
  private final FxCharmBasicsPanel basicsPanel;
  private final FxCharmRulesPanel rulesPanel;
  private final FxCharmInformationPanel informationPanel;

  public FxCharmDetails(SelectionViewFactory selectionFactory, Resources resources) {
    super(selectionFactory, new LC().wrapAfter(1), new AC().grow().fill(), new AC().grow().fill());
    this.basicsPanel = new FxCharmBasicsPanel(selectionFactory);
    this.rulesPanel = new FxCharmRulesPanel(resources, selectionFactory);
    this.informationPanel = new FxCharmInformationPanel(resources);
  }

  @Override
  public CharmBasicsPanel addBasicsPanel(final String title) {
    addSubpanel(basicsPanel, title, new CC().height(BASICS_HEIGHT).growY().push());
    return basicsPanel;
  }

  @Override
  public CharmRulesPanel addRulesPanel(final String title) {
    addSubpanel(rulesPanel, title, new CC().height(RULES_HEIGHT).growY().push());
    return rulesPanel;
  }

  @Override
  public CharmInformationPanel addInformationPanel(final String title) {
    addSubpanel(informationPanel, title, new CC().height(INFO_HEIGHT).growY().push());
    return informationPanel;
  }
}