package net.sf.anathema.charmdatabase.view.fx;

import static net.sf.anathema.platform.fx.FxThreading.runOnCorrectThread;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.charmdatabase.view.AgnosticCharmDatabaseView;
import net.sf.anathema.charmdatabase.view.CharmDatabaseView;
import net.sf.anathema.charmdatabase.view.CharmBasicsPanel;
import net.sf.anathema.charmdatabase.view.info.CharmInformationPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmRulesPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
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
		this.details = new FxCharmDetails(new ComboBoxSelectionFactory(), resources);
		this.navigation = new FxCharmNavigation(new MagicDisplayLabeler(resources));
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
		return navigation.addEditTemplateTool();
	}

	@Override
	public CharmBasicsPanel addBasicsPanel(String title) {
		return details.addBasicsPanel(title);
	}

	@Override
	public CharmRulesPanel addRulesPanel(String title) {
		return details.addRulesPanel(title);
	}

	@Override
	public CharmInformationPanel addInformationPanel(String title) {
		return details.addInformationPanel(title);
	}

	@Override
	public ITextView addTextualFilter(String label) {
		return navigation.addTextualFilter(label);
	}
}