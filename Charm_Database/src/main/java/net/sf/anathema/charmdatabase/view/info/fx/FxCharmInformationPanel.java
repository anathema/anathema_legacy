package net.sf.anathema.charmdatabase.view.info.fx;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxContainerPanel;
import net.sf.anathema.charmdatabase.view.info.CharmInformationPanel;
import net.sf.anathema.charmdatabase.view.info.CharmSourcePanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.NodeHolder;

public class FxCharmInformationPanel extends AbstractFxContainerPanel implements CharmInformationPanel, NodeHolder {

  private final FxCharmSourcePanel sourcePanel;

  public FxCharmInformationPanel(Resources resources) {
    super(new LC().wrapAfter(3).fill().insets("4"), new AC(), new AC());
    sourcePanel = new FxCharmSourcePanel(resources);
  }

  @Override
  public ITextView addDescriptionView(String label) {
    return addTextBoxView(label, new CC().spanX(2).grow());
  }

  @Override
  public CharmSourcePanel addSourcePanel(final String title) {
    addSubpanel(sourcePanel, title, new CC().grow());
    return sourcePanel;
  }
}