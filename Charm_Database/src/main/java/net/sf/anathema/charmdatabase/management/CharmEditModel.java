package net.sf.anathema.charmdatabase.management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.main.magic.basic.attribute.MagicAttribute;
import net.sf.anathema.character.main.magic.basic.cost.ICostList;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.duration.Duration;
import net.sf.anathema.character.main.magic.charm.type.ICharmTypeModel;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.traits.ValuedTraitType;
import net.sf.anathema.characterengine.support.Announcer;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import net.sf.anathema.lib.workflow.textualdescription.ITextualDescription;
import net.sf.anathema.lib.workflow.textualdescription.SimpleTextualDescription;

import com.google.common.base.Joiner;

public class CharmEditModel implements ICharmEditModel {

	private final MagicDisplayLabeler labeler;
	private final MagicDescriptionProvider descriptionProvider;
	
	ITextualDescription name;
	ITextualDescription description;
	Identifier charmType;
	Identifier charmGroup;
	Charm[] prerequisites;
	ValuedTraitType[] traitMinimums;
	ICostList costs;
	MagicAttribute[] keywords;
	ICharmTypeModel actionType;
	Duration duration;
	
	private final Announcer<ChangeListener> typeChangedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> traitChangedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> prerequisitesChangedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> traitMinimumsChangedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> costsChangedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> keywordsChangedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> actionTypeChangedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> durationChangedControl = Announcer.to(ChangeListener.class);
	
	private final Announcer<ChangeListener> canonSelectedControl = Announcer.to(ChangeListener.class);
	private final Announcer<ChangeListener> customSelectedControl = Announcer.to(ChangeListener.class);
	
	public CharmEditModel(MagicDisplayLabeler labeler, MagicDescriptionProvider provider) {
		this.labeler = labeler;
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
		name.setText(labeler.getLabelForMagic(charm));
		description.setText(getDescriptionText(charm));
		
		setCharmType(charm.getCharacterType());
		setCharmGroup(new SimpleIdentifier(charm.getGroupId()));
		
		setCharmPrerequisites(charm.getParentCharms().toArray(new Charm[0]));
		
		List<ValuedTraitType> traits = new ArrayList<>();
		traits.add(charm.getEssence());
		traits.addAll(Arrays.asList(charm.getPrerequisites()));
		setCharmTraitMinimums(traits.toArray(new ValuedTraitType[0]));
		setCharmTemporaryCosts(charm.getTemporaryCost());
		
		setCharmKeywords(charm.getAttributes());
		setCharmActionType(charm.getCharmTypeModel());
		setCharmDuration(charm.getDuration());
		
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

	@Override
	public Charm[] getCharmPrerequisites() {
		return prerequisites;
	}

	@Override
	public void setCharmPrerequisites(Charm[] charms) {
		if (Arrays.deepEquals(prerequisites, charms)) {
			return;
		}
		
		prerequisites = charms;
		
		prerequisitesChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCharmPrerequisitesChangedListening(ChangeListener listener) {
		prerequisitesChangedControl.addListener(listener);
	}

	@Override
	public ValuedTraitType[] getCharmTraitMinimums() {
		return traitMinimums;
	}

	@Override
	public void setCharmTraitMinimums(ValuedTraitType[] traits) {
		if (Arrays.deepEquals(traitMinimums, traits)) {
			return;
		}
		
		traitMinimums = traits;
		
		traitMinimumsChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCharmTraitMinimumsChangedListening(ChangeListener listener) {
		traitMinimumsChangedControl.addListener(listener);
	}

	@Override
	public ICostList getCharmTemporaryCosts() {
		return costs;
	}

	@Override
	public void setCharmTemporaryCosts(ICostList costs) {
		if (costs.equals(this.costs)) {
			return;
		}
		
		this.costs = costs;
		
		costsChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCharmTemporaryCostsChangedListening(ChangeListener listener) {
		costsChangedControl.addListener(listener);
	}

	@Override
	public MagicAttribute[] getCharmKeywords() {
		return keywords;
	}

	@Override
	public void setCharmKeywords(MagicAttribute[] keywords) {
		if (Arrays.deepEquals(keywords, this.keywords)) {
			return;
		}
				
		this.keywords = keywords;
		
		keywordsChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCharmKeywordsChangedListening(ChangeListener listener) {
		keywordsChangedControl.addListener(listener);
	}

	@Override
	public ICharmTypeModel getCharmActionType() {
		return actionType;
	}

	@Override
	public void setCharmActionType(ICharmTypeModel type) {
		if (type.equals(actionType)) {
			return;
		}
		
		this.actionType = type;
		
		actionTypeChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCharmActionTypeChangedListening(ChangeListener listener) {
		actionTypeChangedControl.addListener(listener);
	}

	@Override
	public Duration getCharmDuration() {
		return duration;
	}

	@Override
	public void setCharmDuration(Duration duration) {
		if (duration.equals(this.duration)) {
			return;
		}
		
		this.duration = duration;
		
		durationChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCharmDurationTypeChangedListening(ChangeListener listener) {
		durationChangedControl.addListener(listener);
	}
	
}
