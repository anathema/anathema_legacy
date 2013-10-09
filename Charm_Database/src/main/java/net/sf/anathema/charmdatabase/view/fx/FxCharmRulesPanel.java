package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.magic.charm.type.CharmType;
import net.sf.anathema.charmdatabase.presenter.CharmTypeUi;
import net.sf.anathema.charmdatabase.view.rules.CharmCostsPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmKeywordsPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmPrerequisitesPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmRulesPanel;
import net.sf.anathema.charmdatabase.view.rules.CharmTraitMinimumsPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.platform.fx.FxObjectSelectionView;
import net.sf.anathema.platform.fx.StyledTitledPane;
import net.sf.anathema.platform.fx.selection.SelectionViewFactory;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmRulesPanel implements CharmRulesPanel {
	
	private MigPane pane;
	
	private final SelectionViewFactory selectionViewFactory;
	
	private final FxCharmPrerequisitesPanel prerequisitesPanel;
	private final FxCharmTraitsPanel traitsPanel;
	private final FxCharmCostsPanel costsPanel;
	private final FxCharmKeywordsPanel keywordsPanel;

	public FxCharmRulesPanel(Resources resources, SelectionViewFactory selectionFactory) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane = new MigPane(new LC().wrapAfter(2).fill().insets("4"), new AC(), new AC().index(1).shrinkPrio(200));
			}
		});

		this.selectionViewFactory = selectionFactory;
		prerequisitesPanel = new FxCharmPrerequisitesPanel(new MagicDisplayLabeler(resources));
		traitsPanel = new FxCharmTraitsPanel(resources);
		costsPanel = new FxCharmCostsPanel(resources);
		keywordsPanel = new FxCharmKeywordsPanel(resources);
	}
	
	public Node getNode() {
		return pane;
	}

	@Override
	public CharmPrerequisitesPanel addPrerequisitesPanel(final String title) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Node titledPane = StyledTitledPane.Create(title, prerequisitesPanel.getNode());
				pane.add(titledPane, new CC().grow().push());
			}
		});
		return prerequisitesPanel;
	}

	@Override
	public CharmTraitMinimumsPanel addTraitsPanel(final String title) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Node titledPane = StyledTitledPane.Create(title, traitsPanel.getNode());
				pane.add(titledPane, new CC().grow().push());
			}
		});
		return traitsPanel;
	}

	@Override
	public CharmCostsPanel addCostsPanel(final String title) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Node titledPane = StyledTitledPane.Create(title, costsPanel.getNode());
				pane.add(titledPane, new CC().grow().push());
			}
		});
		return costsPanel;
	}
	
	@Override
	public CharmKeywordsPanel addKeywordsPanel(final String title) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				Node titledPane = StyledTitledPane.Create(title, keywordsPanel.getNode());
				pane.add(titledPane, new CC().grow().push());
			}
		});
		return keywordsPanel;
	}

	@Override
	public ObjectSelectionView<CharmType> addTypeView(String label, CharmTypeUi ui) {
		final FxObjectSelectionView<CharmType> selectionView = selectionViewFactory.create(label, ui);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane.add(selectionView.getNode(), new CC().grow());
			}
		});
		return selectionView;
	}
}
