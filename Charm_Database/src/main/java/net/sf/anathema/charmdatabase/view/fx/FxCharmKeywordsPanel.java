package net.sf.anathema.charmdatabase.view.fx;

import java.util.Arrays;
import java.util.Comparator;

import javafx.application.Platform;
import javafx.scene.Node;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.charmdatabase.view.rules.CharmKeywordsPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;
import net.sf.anathema.platform.fx.ListSelectionView;

import org.tbee.javafx.scene.layout.MigPane;

public class FxCharmKeywordsPanel implements CharmKeywordsPanel {

private MigPane pane;
	
	private ListSelectionView<MagicAttribute> listView = new ListSelectionView<>();
	
	public FxCharmKeywordsPanel(final Resources resources) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				pane = new MigPane(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200));
				pane.add(listView.getNode());
			}
		});
		
		// TODO: A graphical means to render the trait values would be nice.
		listView.setCellRenderer(new AgnosticUIConfiguration<MagicAttribute>() {

			@Override
			public RelativePath getIconsRelativePath(MagicAttribute value) {
				return null;
			}

			@Override
			public String getLabel(MagicAttribute attribute) {
				return attribute != null ? resources.getString(attribute.getId()) 
						+ (attribute.isVisualized() ? "" : " " + resources.getString("Charms.Creation.Rules.Keywords.NonVisualized"))
								: null;
			}

			@Override
			public void configureTooltip(MagicAttribute item,
					ConfigurableTooltip configurableTooltip) {
				// nothing to do
			}
			
		});
	}
	
	public Node getNode() {
		return pane;
	}
	
	@Override
	public void setKeywords(MagicAttribute[] keywords) {
		Arrays.sort(keywords, new Comparator<MagicAttribute>() {

			@Override
			public int compare(MagicAttribute o1, MagicAttribute o2) {
				if (o1.isVisualized() && !o2.isVisualized()) {
					return -1;
				}
				if (o2.isVisualized() && !o1.isVisualized()) {
					return 1;
				}
				return o1.getId().compareTo(o2.getId());
			}
			
		});
		listView.setObjects(keywords);
	}

}
