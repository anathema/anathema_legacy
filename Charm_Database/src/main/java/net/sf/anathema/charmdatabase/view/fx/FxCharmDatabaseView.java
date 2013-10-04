package net.sf.anathema.charmdatabase.view.fx;

import static net.sf.anathema.platform.fx.FxThreading.runOnCorrectThread;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.charmdatabase.view.AgnosticCharmDatabaseView;
import net.sf.anathema.charmdatabase.view.CharmDatabaseView;
import net.sf.anathema.charmdatabase.view.CharmDescriptionPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.lib.workflow.textualdescription.ITextView;
import net.sf.anathema.platform.fx.PerspectivePane;
import net.sf.anathema.platform.fx.selection.ComboBoxSelectionFactory;

public class FxCharmDatabaseView implements CharmDatabaseView {

  public final PerspectivePane perspectivePane = new PerspectivePane("skin/equipment/equipment.css", "skin/platform/dotselector.css");
  private final FxCharmNavigation navigation;
  private final FxCharmDetails details;
  public final AgnosticCharmDatabaseView view;

  public FxCharmDatabaseView(Resources resources) {
    this.details = new FxCharmDetails(new ComboBoxSelectionFactory());
    this.navigation = new FxCharmNavigation(resources);
    this.view = new AgnosticCharmDatabaseView(navigation, details);
    initializePerspective();
  }

  private void initializePerspective() {
    runOnCorrectThread(new Runnable() {
      @Override
      public void run() {
        perspectivePane.addStyleSheetClass("equipment-perspective");
        perspectivePane.setNavigationComponent(navigation.getNode());
        perspectivePane.setContentComponent(details.getNode());
      }
    });
  }

@Override
public VetoableObjectSelectionView<Charm> getTemplateListView() {
	return view.getTemplateListView();
}

@Override
public Tool addEditTemplateTool() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public CharmDescriptionPanel addDescriptionPanel(String title) {
	return details.addDescriptionPanel(title);
}

@Override
public ITextView addTextualFilter(String label) {
	return navigation.addTextualFilter(label);
}
}