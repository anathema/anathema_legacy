package net.sf.anathema.charmdatabase.management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.characterengine.support.Announcer;
import net.sf.anathema.charmdatabase.management.filters.CharmDatabaseFilter;
import net.sf.anathema.charmdatabase.management.model.CharmEditModel;
import net.sf.anathema.charmdatabase.management.model.ICharmEditModel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class CharmDatabaseManagement implements ICharmDatabaseManagement {

	private final CharmCache cache;
	private final CharmEditModel editModel;
	private final Resources resources;
	private final Map<String, TraitType[]> traitsByCharacterType = new HashMap<>();
	
	private final List<CharmDatabaseFilter> filters = new ArrayList<CharmDatabaseFilter>();
	
	private final Announcer<ChangeListener> charmListChangedControl = Announcer.to(ChangeListener.class);
	
	private List<Charm> currentCharmList;
	
	public CharmDatabaseManagement(Resources resources,
			HeroEnvironment characterGenerics,
			MagicDescriptionProvider magicDescriptionProvider) {
		cache = characterGenerics.getDataSet(CharmCache.class);
		editModel = new CharmEditModel(new MagicDisplayLabeler(resources), magicDescriptionProvider);
		this.resources = resources;
		
		for (CharacterType type : characterGenerics.getCharacterTypes().findAll()) {
			traitsByCharacterType.put(type.getId(), type.getFavoringTraitType().getTraitTypesForGenericCharms());
		}
		
		prepareCharmList();
	}

	@Override
	public ICharmEditModel getCharmEditModel() {
		return editModel;
	}
	
	private void prepareCharmList() {
		// TODO: We will need a lot of special cases to handle generics
		// They seem to be much less of a thing in Ex3 anyway, so just
		// filter them out for now and deal with it later.
		currentCharmList = new ArrayList<Charm>();
		for (Identifier set : cache.getCharmTypes()) {
			for (Charm charm : cache.getCharms(set)) {
				if (!charm.isInstanceOfGenericCharm()) {
					currentCharmList.add(charm);
				}
			}
		}

		Collections.sort(currentCharmList, new CharmComparator());
	}
	
	@Override
	public Charm[] getFilteredCharms() {
		List<Charm> charms = new ArrayList<Charm>(currentCharmList);
		for (Charm charm : currentCharmList) {
			for (CharmDatabaseFilter filter : filters) {
				if (!filter.approvesCharm(charm)) {
					charms.remove(charm);
					break;
				}
			}
		}
		return charms.toArray(new Charm[0]);
	}

	@Override
	public Identifier[] getCharmTypes() {
		List<Identifier> types = new ArrayList<>();
		for (Identifier type : cache.getCharmTypes()) {
			types.add(new SimpleIdentifier(type.getId()));
		}
		return types.toArray(new Identifier[0]);
	}
	
	@Override
	public Identifier[] getGroupsForCharmType(Identifier type) {
		Identifier[] groups = cache.getCharmProvider().getGroupsForCharmType(type);
		Arrays.sort(groups, new Comparator<Identifier>() {
			@Override
			public int compare(Identifier o1, Identifier o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return groups;
	}
	

	@Override
	public Identifier[] getTraitsForCharmType(Identifier type) {
		if (!traitsByCharacterType.containsKey(type.getId())) {
			return new Identifier[0];
		}
		Identifier[] traits = traitsByCharacterType.get(type.getId());
		Arrays.sort(traits, new Comparator<Identifier>() {
			@Override
			public int compare(Identifier o1, Identifier o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		return traits;
	}

	@Override
	public void addFilter(CharmDatabaseFilter filter) {
		filters.add(filter);
	}

	@Override
	public void removeFilter(CharmDatabaseFilter filter) {
		filters.remove(filter);
	}

	@Override
	public void notifyFiltersUpdated() {
		charmListChangedControl.announce().changeOccurred();
	}

	@Override
	public void addCharmListChangeListener(ChangeListener listener) {
		charmListChangedControl.addListener(listener);
	}

	private class CharmComparator implements Comparator<Charm> {

		@Override
		public int compare(Charm o1, Charm o2) {
			if (o1.getGroupId().compareTo(o2.getGroupId()) != 0) {
				return o1.getGroupId().compareTo(o2.getGroupId());
			}
			if (o1.isInstanceOfGenericCharm() && !o2.isInstanceOfGenericCharm()) {
				return -1;
			}
			if (o2.isInstanceOfGenericCharm() && !o1.isInstanceOfGenericCharm()) {
				return 1;
			}
			return resources.getString(o1.getId()).compareTo(resources.getString(o2.getId()));
		}

	}
}
