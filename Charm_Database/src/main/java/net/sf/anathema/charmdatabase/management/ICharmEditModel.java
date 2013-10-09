package net.sf.anathema.charmdatabase.management;

import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.basic.cost.ICostList;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.character.main.magic.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.traits.ValuedTraitType;
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
	
	// TODO: More complex prerequisites, such as OR groups or keyword counts
	Charm[] getCharmPrerequisites();
	
	void setCharmPrerequisites(Charm[] charms);
	
	void addCharmPrerequisitesChangedListening(ChangeListener listener);
	
	
	ValuedTraitType[] getCharmTraitMinimums();
	
	void setCharmTraitMinimums(ValuedTraitType[] traits);
	
	void addCharmTraitMinimumsChangedListening(ChangeListener listener);
	
	
	
	ICostList getCharmTemporaryCosts();
	
	void setCharmTemporaryCosts(ICostList costs);
	
	void addCharmTemporaryCostsChangedListening(ChangeListener listener);
	
	
	
	MagicAttribute[] getCharmKeywords();
	
	void setCharmKeywords(MagicAttribute[] keywords);
	
	void addCharmKeywordsChangedListening(ChangeListener listener);
	
	
	
	
	ICharmTypeModel getCharmActionType();
	
	void setCharmActionType(ICharmTypeModel type);
	
	void addCharmActionTypeChangedListening(ChangeListener listener);
	
	
	
	
	Duration getCharmDuration();
	
	void setCharmDuration(Duration duration);
	
	void addCharmDurationTypeChangedListening(ChangeListener listener);
	
	
	
	void addCanonCharmSelectionListening(ChangeListener listener);
	
	void addCustomCharmSelectionListening(ChangeListener listener);


	

}
