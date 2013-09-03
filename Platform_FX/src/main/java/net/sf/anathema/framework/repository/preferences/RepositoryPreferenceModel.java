package net.sf.anathema.framework.repository.preferences;

import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferenceModel;
import net.sf.anathema.framework.preferences.persistence.PreferenceKey;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;
import net.sf.anathema.framework.preferences.persistence.PreferenceValue;

import java.nio.file.Path;
import java.nio.file.Paths;

@RegisteredPreferenceModel
public class RepositoryPreferenceModel implements PreferenceModel {
  public static final PreferenceKey key = new PreferenceKey("framework.repository.location");
  private Path repositoryPath;

  @Override
  public void serializeTo(PreferencePto pto) {
    PreferenceValue value = new PreferenceValue(repositoryPath.toString());
    pto.map.put(key, value);
  }

  @Override
  public void initializeFrom(PreferencePto pto) {
    PreferenceValue preferenceValue = pto.map.get(key);
    if (preferenceValue == null) {
      repositoryPath = Paths.get("./repository");
      return;
    }
    repositoryPath = Paths.get(preferenceValue.value);
  }
}