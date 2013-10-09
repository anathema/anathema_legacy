package net.sf.anathema.charmdatabase.view.fx;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.charmdatabase.view.rules.CharmTraitMinimumsPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.platform.fx.ListSelectionView;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmTraitsPanel implements CharmTraitMinimumsPanel {

private MigPane pane;
	
	private ListSelectionView<ValuedTraitType> listView = new ListSelectionView<>();
	
	public FxCharmTraitsPanel(final Resources resources) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane = new MigPane(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200));
				pane.add(listView.getNode());
			}
		});
		
		// TODO: A graphical means to render the trait values would be nice.
		listView.setCellRenderer(new AgnosticUIConfiguration<ValuedTraitType>() {

			@Override
			public RelativePath getIconsRelativePath(ValuedTraitType value) {
				return null;
			}

			@Override
			public String getLabel(ValuedTraitType trait) {
				return trait != null ? resources.getString(trait.getType().getId()) + " "
			+ trait.getCurrentValue() : null;
			}

			@Override
			public void configureTooltip(ValuedTraitType item,
					ConfigurableTooltip configurableTooltip) {
				// nothing to do
			}
			
		});
	}
	
	public Node getNode() {
		return pane;
	}

	@Override
	public void setTraits(ValuedTraitType[] traits) {
		listView.setObjects(traits);
	}

}
