package net.sf.anathema.charmdatabase.management;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.characterengine.support.Announcer;
import net.sf.anathema.charmdatabase.management.filters.CharmDatabaseFilter;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.MagicDisplayLabeler;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.util.Identifier;

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
		editModel = new CharmEditModel(new MagicDisplayLabeler(resources), magicDescriptionProvider);
		
		// TODO: We eventually want Charm Types, rather than Character Types here.
		characterTypes = characterGenerics.getCharacterTypes().findAll();
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
