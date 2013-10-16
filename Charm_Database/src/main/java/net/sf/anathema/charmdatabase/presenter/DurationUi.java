package net.sf.anathema.charmdatabase.presenter;

import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.gui.AbstractUIConfiguration;

public class DurationUi extends AbstractUIConfiguration<Duration> {
	private Resources resources;

	public DurationUi(Resources resources) {
		this.resources = resources;
	}

	@Override
	protected String labelForExistingValue(Duration value) {
		return value.getText(resources);
	}
}
