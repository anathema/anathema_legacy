package net.sf.anathema.charmdatabase.management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.characterengine.support.Announcer;
import net.sf.anathema.charmdatabase.management.filters.CharmDatabaseFilter;
import net.sf.anathema.charmdatabase.presenter.CharmEditModel;
import net.sf.anathema.charmdatabase.presenter.ICharmEditModel;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public class CharmDatabaseManagement implements ICharmDatabaseManagement {

	private final CharmCache cache;
	private final CharmEditModel editModel;
	private final Identifier[] characterTypes;
	
	private final List<CharmDatabaseFilter> filters = new ArrayList<CharmDatabaseFilter>();
	
	private final Announcer<ChangeListener> charmListChangedControl = Announcer.to(ChangeListener.class);
	
	public CharmDatabaseManagement(Resources resources,
			HeroEnvironment characterGenerics,
			MagicDescriptionProvider magicDescriptionProvider) {
		cache = characterGenerics.getDataSet(CharmCache.class);
		editModel = new CharmEditModel(resources, magicDescriptionProvider);
		
		// TODO: Horrible, just horrible.
		// trying to collect all charm types
		// clean this up; there must be a much smarter way of doing this
		List<Identifier> types = new ArrayList<Identifier>();
		for (CharacterType type : characterGenerics.getCharacterTypes().findAll()) {
			if (type.isEssenceUser()) {
				types.add(type);
			}
		}
		types.add(new SimpleIdentifier("Martial Arts"));
		characterTypes = types.toArray(new Identifier[0]);
	}

	@Override
	public ICharmEditModel getCharmEditModel() {
		return editModel;
	}

	@Override
	public Charm[] getCharms() {
		// TODO: Handle generics
		List<Charm> charms = new ArrayList<Charm>();
		for (Identifier set : cache.getCharmTypes()) {
			charms.addAll(Arrays.asList(cache.getCharms(set)));
		}
		
		for (Charm charm : new ArrayList<Charm>(charms)) {
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
		return characterTypes;
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

}
