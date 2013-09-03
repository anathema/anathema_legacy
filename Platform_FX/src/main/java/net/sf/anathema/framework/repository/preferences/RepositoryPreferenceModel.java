package net.sf.anathema.framework.repository.preferences;

import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferenceModel;
import net.sf.anathema.framework.preferences.persistence.PreferenceKey;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;
import net.sf.anathema.framework.preferences.persistence.PreferenceValue;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

import java.nio.file.Path;
import java.nio.file.Paths;

import static net.sf.anathema.framework.presenter.action.preferences.IAnathemaPreferencesConstants.DEFAULT_REPOSITORY_LOCATION;

@RegisteredPreferenceModel
public class RepositoryPreferenceModel implements PreferenceModel {
  public static final PreferenceKey key = new PreferenceKey("framework.repository.location");
  private final Path defaultPath = Paths.get(DEFAULT_REPOSITORY_LOCATION);
  private final Announcer<ChangeListener> announcer = Announcer.to(ChangeListener.class);
  private Path repositoryPath;

  @Override
  public void serializeTo(PreferencePto pto) {
    PreferenceValue value = new PreferenceValue(repositoryPath.toString());
    pto.map.put(key, value);
  }

  @Override
  public void initializeFrom(PreferencePto pto) {
    PreferenceValue preferenceValue = pto.map.get(key);
    Path newPath;
    if (preferenceValue == null) {
      newPath = defaultPath;
    } else {
      newPath = Paths.get(preferenceValue.value);
    }
    requestChangeOfRepositoryPath(newPath);
  }

  public void whenLocationChanges(ChangeListener changeListener) {
    announcer.addListener(changeListener);
  }

  public Path getRepositoryPath() {
    return repositoryPath;
  }

  public void requestChangeOfRepositoryPath(Path path) {
    this.repositoryPath = path;
    announcer.announce().changeOccurred();
  }
}