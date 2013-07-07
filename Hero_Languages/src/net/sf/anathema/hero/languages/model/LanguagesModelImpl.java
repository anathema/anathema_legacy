package net.sf.anathema.hero.languages.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.sf.anathema.character.main.traits.types.AbilityType;
import net.sf.anathema.character.main.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.hero.abilities.AbilityModelFetcher;
import net.sf.anathema.hero.traits.TraitChangeFlavor;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.change.RemovableEntryChangeAdapter;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.model.InitializationContext;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.lang3.ArrayUtils;
import org.jmock.example.announcer.Announcer;

import java.util.Arrays;

public class LanguagesModelImpl extends AbstractRemovableEntryModel<Identifier> implements LanguagesModel {

  private static final int barbarianLanguagesPerPoint = 4;
  private final Identifier[] languages =
          new Identifier[]{new SimpleIdentifier("HighRealm"), new SimpleIdentifier("LowRealm"), new SimpleIdentifier("OldRealm"),
                  new SimpleIdentifier("Riverspeak"), new SimpleIdentifier("Skytongue"), new SimpleIdentifier("Flametongue"),
                  new SimpleIdentifier("Seatongue"), new SimpleIdentifier("Foresttongue"), new SimpleIdentifier("GuildCant"),
                  new SimpleIdentifier("ClawSpeak"), new SimpleIdentifier("HighHolySpeech"), new SimpleIdentifier("Pelagial")};

  private Identifier selection;
  private int languagePointsAllowed;
  private Hero hero;
  private final Announcer<ChangeListener> pointControl = Announcer.to(ChangeListener.class);

  @Override
  public Identifier getId() {
    return ID;
  }

  @Override
  public void initialize(InitializationContext context, Hero hero) {
    this.hero = hero;
    updateLanguagePointAllowance();
  }

  @Override
  public void initializeListening(final ChangeAnnouncer announcer) {
    addModelChangeListener(new RemovableEntryChangeAdapter<Identifier>(announcer));
    announcer.addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (TraitChangeFlavor.changes(flavor, AbilityType.Linguistics)) {
          updateLanguagePointAllowance();
        }
      }
    });
  }

  private void updateLanguagePointAllowance() {
    int currentPoints = languagePointsAllowed;
    Trait linguistics = AbilityModelFetcher.fetch(hero).getTrait(AbilityType.Linguistics);
    languagePointsAllowed = linguistics.getCurrentValue() + 1;
    if (currentPoints != languagePointsAllowed) {
      pointControl.announce().changeOccurred();
    }
  }

  @Override
  public Identifier[] getPredefinedLanguages() {
    return languages;
  }

  @Override
  protected Identifier createEntry() {
    Identifier selectionCopy = selection;
    this.selection = null;
    fireEntryChanged();
    return selectionCopy;
  }

  @Override
  public boolean isEntryAllowed() {
    return selection != null && !getEntries().contains(selection);
  }

  @Override
  public boolean isPredefinedLanguage(Object object) {
    return ArrayUtils.contains(languages, object);
  }

  @Override
  public void selectBarbarianLanguage(String customName) {
    if (StringUtilities.isNullOrTrimmedEmpty(customName)) {
      this.selection = null;
      fireEntryChanged();
    }
    selectLanguage(new SimpleIdentifier(customName));
  }

  @Override
  public void selectLanguage(final Identifier language) {
    Preconditions.checkNotNull(language);
    Identifier foundLanguage = Iterables.find(getEntries(), new Predicate<Identifier>() {
      @Override
      public boolean apply(Identifier selectedLanguage) {
        return Objects.equal(language, selectedLanguage);
      }
    }, null);
    if (foundLanguage != null) {
      return;
    }
    this.selection = language;
    fireEntryChanged();
  }

  @Override
  public Identifier getPredefinedLanguageById(final String id) {
    return Iterables.find(Arrays.asList(languages), new Predicate<Identifier>() {
      @Override
      public boolean apply(Identifier definedLanuage) {
        return Objects.equal(id, definedLanuage.getId());
      }
    }, null);
  }

  @Override
  public void addCharacterChangedListener(ChangeListener listener) {
    pointControl.addListener(listener);
  }

  @Override
  public int getBarbarianLanguageCount() {
    int count = 0;
    for (Identifier language : getEntries()) {
      if (!isPredefinedLanguage(language)) {
        count++;
      }
    }
    return count;
  }

  @Override
  public int getLanguagePointsAllowed() {
    updateLanguagePointAllowance();
    return languagePointsAllowed;
  }

  @Override
  public int getLanguagePointsSpent() {
    int spent = getPredefinedLanguageCount();
    spent += Math.ceil((double) getBarbarianLanguageCount() / barbarianLanguagesPerPoint);
    return spent;
  }

  @Override
  public int getPredefinedLanguageCount() {
    int count = 0;
    for (Identifier language : getEntries()) {
      if (isPredefinedLanguage(language)) {
        count++;
      }
    }
    return count;
  }
}