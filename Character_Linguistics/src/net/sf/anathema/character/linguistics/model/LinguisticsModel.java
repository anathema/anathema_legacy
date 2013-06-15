package net.sf.anathema.character.linguistics.model;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ConfigurableCharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.lang3.ArrayUtils;
import org.jmock.example.announcer.Announcer;

import java.util.Arrays;

public class LinguisticsModel extends AbstractRemovableEntryModel<Identifier> implements ILinguisticsModel {

  private static final int barbarianLanguagesPerPoint= 4;
  private final Identifier[] languages = new Identifier[] { new SimpleIdentifier("HighRealm"),
      new SimpleIdentifier("LowRealm"), new SimpleIdentifier("OldRealm"), new SimpleIdentifier("Riverspeak"), new SimpleIdentifier("Skytongue"), new SimpleIdentifier("Flametongue"), new SimpleIdentifier("Seatongue"), new SimpleIdentifier("Foresttongue"), new SimpleIdentifier("GuildCant"), new SimpleIdentifier("ClawSpeak"), new SimpleIdentifier("HighHolySpeech"), new SimpleIdentifier("Pelagial") };          //$NON-NLS-10$ //$NON-NLS-11$

  private Identifier selection;
  private int languagePointsAllowed;
  private final ICharacterModelContext context;
  private final Announcer<IChangeListener> pointControl = Announcer.to(IChangeListener.class);

  public LinguisticsModel(ICharacterModelContext context) {
    this.context = context;
    ConfigurableCharacterChangeListener listener = new ConfigurableCharacterChangeListener() {
      @Override
      public void configuredChangeOccured() {
        updateLanguagePointAllowance();
      }
    };
    listener.addTraitTypes(AbilityType.Linguistics);
    context.getCharacterListening().addChangeListener(listener);
    updateLanguagePointAllowance();
  }

  private void updateLanguagePointAllowance() {
	int currentPoints = languagePointsAllowed;
    languagePointsAllowed = context.getTraitCollection().getTrait(AbilityType.Linguistics).getCurrentValue() + 1;
    if (currentPoints != languagePointsAllowed)
    	pointControl.announce().changeOccurred();
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
    return Iterables.find(Arrays.asList(languages), new Predicate<Identifier>(){
      @Override
      public boolean apply(Identifier definedLanuage) {
        return Objects.equal(id, definedLanuage.getId());
      }
    },null);
  }

  @Override
  public void addCharacterChangedListener(IChangeListener listener) {
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