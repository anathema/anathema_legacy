package net.sf.anathema.charmdatabase.view.fx;

import org.tbee.javafx.scene.layout.MigPane;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.charmdatabase.management.model.CharmPrerequisite;
import net.sf.anathema.charmdatabase.view.rules.CharmPrerequisitesPanel;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.platform.fx.ListSelectionView;

public class FxCharmPrerequisitesPanel implements CharmPrerequisitesPanel {

	private MigPane pane;
	
	private ListSelectionView<CharmPrerequisite> listView = new ListSelectionView<>();
	
	public FxCharmPrerequisitesPanel(final MagicDisplayLabeler labeler) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane = new MigPane(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200));
				pane.add(listView.getNode());
			}
		});
		
		listView.setCellRenderer(new AgnosticUIConfiguration<CharmPrerequisite>() {

			@Override
			public RelativePath getIconsRelativePath(CharmPrerequisite value) {
				return null;
			}

			@Override
			public String getLabel(CharmPrerequisite charm) {
				return charm != null ? charm.getText(labeler) : null;
			}

			@Override
			public void configureTooltip(CharmPrerequisite item,
					ConfigurableTooltip configurableTooltip) {
				// nothing to do
			}
			
		});
	}
	
	public Node getNode() {
		return pane;
	}

	@Override
	public void setPrerequisites(CharmPrerequisite[] charms) {
		listView.setObjects(charms);
	}

}
