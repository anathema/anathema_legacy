package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.charmdatabase.view.info.CharmSourcePanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.platform.fx.ListSelectionView;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmSourcePanel implements CharmSourcePanel {

private MigPane pane;
	
	private ListSelectionView<SourceBook> listView = new ListSelectionView<>();
	
	public FxCharmSourcePanel(final Resources resources) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane = new MigPane(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200));
				pane.add(listView.getNode());
			}
		});
		
		// TODO: A graphical means to render the trait values would be nice.
		listView.setCellRenderer(new AgnosticUIConfiguration<SourceBook>() {

			@Override
			public RelativePath getIconsRelativePath(SourceBook value) {
				return null;
			}

			@Override
			public String getLabel(SourceBook source) {
				// TODO: We should display page numbers as well
				return source != null ? resources.getString("ExaltedSourceBook." + source.getId()) : null;
			}

			@Override
			public void configureTooltip(SourceBook item,
					ConfigurableTooltip configurableTooltip) {
				// nothing to do
			}
			
		});
	}
	
	public Node getNode() {
		return pane;
	}

	@Override
	public void setSources(SourceBook[] sources) {
		listView.setObjects(sources);
	}

}
