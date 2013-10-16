package net.sf.anathema.charmdatabase.view.info.fx;

import net.sf.anathema.character.main.magic.basic.source.SourceBook;
import net.sf.anathema.charmdatabase.view.fx.AbstractFxListPanel;
import net.sf.anathema.charmdatabase.view.fx.IconlessCellRenderer;
import net.sf.anathema.charmdatabase.view.info.CharmSourcePanel;
import net.sf.anathema.hero.charms.compiler.CharmInformationUtilities;
import net.sf.anathema.hero.charms.display.tooltip.ISourceStringBuilder;
import net.sf.anathema.lib.util.Identifier;

public class FxCharmSourcePanel extends AbstractFxListPanel<SourceBook> implements CharmSourcePanel {
	
	// TODO: This should not be static.
	// Can't use the instance variable in the constructor, though. Explore alternative arrangements.
	private static String currentCharmId = "";
	
	public FxCharmSourcePanel(final ISourceStringBuilder sourceBuilder) {
		super(new IconlessCellRenderer<SourceBook>() {
			@Override
			public String getLabel(SourceBook source) {
				return source != null ? sourceBuilder.createStringForSource(currentCharmId, source) : null;
			}
		});
	}

	@Override
	public void setSources(SourceBook[] sources, Identifier type, String name) {
		currentCharmId = CharmInformationUtilities.getFormalCharmName(type, name);
		setObjects(sources);
	}
}
