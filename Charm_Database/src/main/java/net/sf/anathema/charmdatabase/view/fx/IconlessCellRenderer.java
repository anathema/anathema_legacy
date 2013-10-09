package net.sf.anathema.charmdatabase.view.fx;

import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.ConfigurableTooltip;

public abstract class IconlessCellRenderer<N> implements AgnosticUIConfiguration<N> {

	@Override
	public RelativePath getIconsRelativePath(N value) {
		return null;
	}

	@Override
	public void configureTooltip(N item, ConfigurableTooltip configurableTooltip) {
		// nothing to do			
	}
}
