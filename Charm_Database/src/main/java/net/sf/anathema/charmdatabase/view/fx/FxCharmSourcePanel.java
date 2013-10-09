package net.sf.anathema.charmdatabase.view.fx;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.charmdatabase.view.info.CharmSourcePanel;
import net.sf.anathema.framework.environment.Resources;

public class FxCharmSourcePanel extends AbstractFxListPanel<SourceBook> implements CharmSourcePanel {
	
	public FxCharmSourcePanel(final Resources resources) {
		super(new LC().height("100"), new AC(), new AC().index(1).shrinkPrio(200),
				new IconlessCellRenderer<SourceBook>() {
			@Override
			public String getLabel(SourceBook source) {
				// TODO: We should display page numbers as well
				return source != null ? resources.getString("ExaltedSourceBook." + source.getId()) : null;
			}
		});
	}

	@Override
	public void setSources(SourceBook[] sources) {
		setObjects(sources);
	}

}
