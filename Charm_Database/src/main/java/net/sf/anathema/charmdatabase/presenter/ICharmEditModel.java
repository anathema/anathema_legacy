package net.sf.anathema.charmdatabase.presenter;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;

public interface ICharmEditModel {

	void setNewTemplate();
	
	void setEditCharm(Charm charm);

	ITextualDescription getDescription();

	ITextualDescription getName();
	
	
	
	Identifier getCharmType();
	
	void setCharmType(Identifier newValue);
	
	void addCharmTypeChangedListening(ChangeListener listener);

	Identifier getCharmGroup();
	
	void setCharmGroup(Identifier newValue);
	
	void addCharmGroupChangedListening(ChangeListener listener);
	
	
	
	void addCanonCharmSelectionListening(ChangeListener listener);
	
	void addCustomCharmSelectionListening(ChangeListener listener);


	

}
