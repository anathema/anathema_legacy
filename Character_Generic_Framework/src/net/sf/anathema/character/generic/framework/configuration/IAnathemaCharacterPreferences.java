package net.sf.anathema.character.generic.framework.configuration;

import net.sf.anathema.character.generic.rules.IExaltedRuleSet;

public interface IAnathemaCharacterPreferences {

  public IExaltedRuleSet getPreferredRuleset();

  public boolean printZeroBackgrounds();

  public boolean printZeroCrafts();
}