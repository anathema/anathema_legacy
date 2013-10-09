package net.sf.anathema.charmdatabase.view.info;

import net.sf.anathema.lib.workflow.textualdescription.ITextView;

public interface CharmInformationPanel {
	ITextView addDescriptionView(String label);
	
	CharmSourcePanel addSourcePanel(String title);
}
