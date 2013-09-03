package net.sf.anathema.framework.preferences.persistence;

import net.sf.anathema.framework.preferences.persistence.PreferenceKey;
import net.sf.anathema.framework.preferences.persistence.PreferenceValue;

import java.util.HashMap;
import java.util.Map;

public class PreferencePto {
  public Map<PreferenceKey, PreferenceValue> map = new HashMap<>();
}