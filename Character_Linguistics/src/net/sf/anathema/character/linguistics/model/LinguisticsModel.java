package net.sf.anathema.character.linguistics.model;

import java.util.Arrays;

import net.disy.commons.core.util.ArrayUtilities;
import net.disy.commons.core.util.Ensure;
import net.disy.commons.core.util.ObjectUtilities;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.ConfigurableCharacterChangeListener;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.removableentry.model.AbstractRemovableEntryModel;
import net.sf.anathema.character.linguistics.presenter.ILinguisticsModel;
import net.sf.anathema.lib.collection.Predicate;
import net.sf.anathema.lib.control.change.ChangeControl;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class LinguisticsModel extends AbstractRemovableEntryModel<IIdentificate> implements ILinguisticsModel {

  private final IIdentificate[] languages = new IIdentificate[] { new Identificate("HighRealm"), //$NON-NLS-1$
      new Identificate("LowRealm"), new Identificate("OldRealm"), new Identificate("Riverspeak"), new Identificate("Skytongue"), new Identificate("Flametongue"), new Identificate("Seatongue"), new Identificate("Foresttongue"), new Identificate("GuildCant") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$

  private IIdentificate selection;
  private int languagePointsAllowed;
  private int barbarianLanguagesPerPoint;
  private final ICharacterModelContext context;
  private final ChangeControl pointControl = new ChangeControl();

  public LinguisticsModel(final ICharacterModelContext context) {
    this.context = context;
    context.getBasicCharacterContext().getRuleSet().getEdition().accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        ConfigurableCharacterChangeListener listener = new ConfigurableCharacterChangeListener() {
          @Override
          public void configuredChangeOccured() {
            updateBarbarianLanguageAllowance();
          }
        };
        listener.addTraitTypes(AttributeType.Intelligence);
        context.getCharacterListening().addChangeListener(listener);
        updateBarbarianLanguageAllowance();
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        barbarianLanguagesPerPoint = 4;
      }
    });
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
    languagePointsAllowed = context.getTraitCollection().getTrait(AbilityType.Linguistics).getCurrentValue();
    pointControl.fireChangedEvent();
  }

  private void updateBarbarianLanguageAllowance() {
    barbarianLanguagesPerPoint = context.getTraitCollection().getTrait(AttributeType.Intelligence).getCurrentValue();
    pointControl.fireChangedEvent();
  }

  public IIdentificate[] getPredefinedLanguages() {
    return languages;
  }

  @Override
  protected IIdentificate createEntry() {
    IIdentificate selectionCopy = selection;
    this.selection = null;
    fireEntryChanged();
    return selectionCopy;
  }

  @Override
  public boolean isEntryAllowed() {
    return selection != null && !getEntries().contains(selection);
  }

  public boolean isPredefinedLanguage(Object object) {
    return ArrayUtilities.contains(languages, object);
  }

  public void selectBarbarianLanguage(String customName) {
    if (StringUtilities.isNullOrTrimEmpty(customName)) {
      this.selection = null;
      fireEntryChanged();
    }
    selectLanguage(new Identificate(customName));
  }

  public void selectLanguage(final IIdentificate language) {
    Ensure.ensureNotNull(language);
    IIdentificate foundLanguage = new Predicate<IIdentificate>() {
      public boolean evaluate(IIdentificate selectedLanguage) {
        return ObjectUtilities.equals(language, selectedLanguage);
      }
    }.find(getEntries());
    if (foundLanguage != null) {
      return;
    }
    this.selection = language;
    fireEntryChanged();
  }

  public IIdentificate getPredefinedLanguageById(final String id) {
    return new Predicate<IIdentificate>() {
      public boolean evaluate(IIdentificate definedLanuage) {
        return ObjectUtilities.equals(id, definedLanuage.getId());
      }
    }.find(Arrays.asList(languages));
  }

  public void addCharacterChangedListener(IChangeListener listener) {
    pointControl.addChangeListener(listener);
  }

  public int getBarbarianLanguageCount() {
    int count = 0;
    for (IIdentificate language : getEntries()) {
      if (!isPredefinedLanguage(language)) {
        count++;
      }
    }
    return count;
  }

  public int getLanguagePointsAllowed() {
    return languagePointsAllowed;
  }

  public int getLanguagePointsSpent() {
    int spent = getPredefinedLanguageCount();
    spent += Math.ceil((double) getBarbarianLanguageCount() / barbarianLanguagesPerPoint);
    return spent;
  }

  public int getPredefinedLanguageCount() {
    int count = 0;
    for (IIdentificate language : getEntries()) {
      if (isPredefinedLanguage(language)) {
        count++;
      }
    }
    return count;
  }
}