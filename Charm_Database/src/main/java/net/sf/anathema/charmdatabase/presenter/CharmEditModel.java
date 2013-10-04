package net.sf.anathema.charmdatabase.presenter;

import com.google.common.base.Joiner;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.characterengine.support.Announcer;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;

public class CharmEditModel implements ICharmEditModel {

	private final Resources resources;
	private final MagicDescriptionProvider descriptionProvider;
	
	ITextualDescription name;
	ITextualDescription description;
	Identifier charmType;
	Identifier charmGroup;
	
	
	private final Announcer<ChangeListener> typeChangedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> traitChangedControl = Announcer.to(ChangeListener.class);
	
	private final Announcer<ChangeListener> canonSelectedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> customSelectedControl = Announcer.to(ChangeListener.class);
	
	public CharmEditModel(Resources resources, MagicDescriptionProvider provider) {
		this.resources = resources;
		name = new SimpleTextualDescription();
		description = new SimpleTextualDescription();
		descriptionProvider = provider;
	}
	
	@Override
	public void setNewTemplate() {
		name.setText("");
		description.setText("");
	}
	
	@Override
	public void setEditCharm(Charm charm) {
		name.setText(resources.getString(charm.getId()));
		description.setText(getDescriptionText(charm));
		
		setCharmType(charm.getCharacterType());
		setCharmGroup(new SimpleIdentifier(charm.getGroupId()));
		
		// TODO: Check charm classification
		canonSelectedControl.announce().changeOccurred();
	}

	@Override
	public ITextualDescription getDescription() {
		return description;
	}

	@Override
	public ITextualDescription getName() {
		return name;
	}

	@Override
	public Identifier getCharmType() {
		return charmType;
	}
	
	@Override
	public void setCharmType(Identifier newValue) {
		if (newValue.equals(charmType)) {
			return;
		}
		
		charmType = newValue;
		
		typeChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCanonCharmSelectionListening(ChangeListener listener) {
		canonSelectedControl.addListener(listener);
	}

	@Override
	public void addCustomCharmSelectionListening(ChangeListener listener) {
		customSelectedControl.addListener(listener);
	}

	@Override
	public void addCharmTypeChangedListening(ChangeListener listener) {
		typeChangedControl.addListener(listener);
	}

	@Override
	public Identifier getCharmGroup() {
		return charmGroup;
	}

	@Override
	public void setCharmGroup(Identifier newValue) {
		if (newValue != null && newValue.equals(charmGroup)) {
			return;
		}
		
		charmGroup = newValue;
		
		traitChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCharmGroupChangedListening(ChangeListener listener) {
		traitChangedControl.addListener(listener);
	}
	
	private String getDescriptionText(Charm charm) {
		String[] paragraphs = descriptionProvider.getCharmDescription(charm).getParagraphs();
		return Joiner.on("\n").join(paragraphs);
	}
	
}
