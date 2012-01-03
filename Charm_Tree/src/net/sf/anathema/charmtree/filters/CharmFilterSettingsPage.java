package net.sf.anathema.charmtree.filters;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.userdialog.page.AbstractDialogPage;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.sf.anathema.lib.resources.IResources;

public class CharmFilterSettingsPage extends AbstractDialogPage
{
	private IResources resources;
	private List<ICharmFilter> filters;
	JList allowedList;
	JList excludedList;

	public CharmFilterSettingsPage(IResources resources, List<ICharmFilter> filters) {
		super(resources.getString("CharmFilters.Instructions"));
		this.resources = resources;
		this.filters = filters;
	}

	@Override
	public JComponent createContent() {
		JPanel panel = new JPanel(new GridDialogLayout(1, false));
		
		for (ICharmFilter filter : filters)
			panel.add(filter.getFilterPreferencePanel(resources));
				
		return panel;
	}
	
	@Override
	public IBasicMessage createCurrentMessage() {
		return getDefaultMessage();
	}

	@Override
	public String getTitle() {
		return resources.getString("CharmFilters.Filters");
	}

}
