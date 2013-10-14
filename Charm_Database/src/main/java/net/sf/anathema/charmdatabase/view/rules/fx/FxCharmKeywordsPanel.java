package net.sf.anathema.charmdatabase.view.rules.fx;

import java.util.Arrays;
import java.util.Comparator;

import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxListPanel;
import net.sf.anathema.charmdatabase.view.fx.IconlessCellRenderer;
import net.sf.anathema.charmdatabase.view.rules.CharmKeywordsPanel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.platform.fx.NodeHolder;

public class FxCharmKeywordsPanel extends AbstractFxListPanel<MagicAttribute> implements CharmKeywordsPanel, NodeHolder {
	
	public FxCharmKeywordsPanel(final Resources resources) {
		// TODO: A graphical means to render the trait values would be nice.
		super(new IconlessCellRenderer<MagicAttribute>() {
					@Override
					public String getLabel(MagicAttribute attribute) {
						return attribute != null ? resources.getString(attribute.getId()) 
								+ (attribute.isVisualized() ? "" : " " + resources.getString("Charms.Creation.Rules.Keywords.NonVisualized"))
										: null;
					}
		});
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
		setObjects(keywords);
	}

}
