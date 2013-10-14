package net.sf.anathema.charmdatabase.view.info.fx;

import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxListPanel;
import net.sf.anathema.charmdatabase.view.fx.IconlessCellRenderer;
import net.sf.anathema.charmdatabase.view.info.CharmSourcePanel;
import net.sf.anathema.framework.environment.Resources;

public class FxCharmSourcePanel extends AbstractFxListPanel<SourceBook> implements CharmSourcePanel {
	
	public FxCharmSourcePanel(final Resources resources) {
		super(new IconlessCellRenderer<SourceBook>() {
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
