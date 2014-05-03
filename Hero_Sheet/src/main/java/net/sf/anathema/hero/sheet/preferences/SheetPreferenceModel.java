package net.sf.anathema.hero.sheet.preferences;

import net.sf.anathema.framework.preferences.elements.PreferenceModel;
import net.sf.anathema.framework.preferences.elements.RegisteredPreferenceModel;
import net.sf.anathema.framework.preferences.persistence.PreferenceKey;
import net.sf.anathema.framework.preferences.persistence.PreferencePto;
import net.sf.anathema.framework.preferences.persistence.PreferenceValue;
import net.sf.anathema.framework.reporting.pdf.PageSize;
import net.sf.anathema.interaction.Command;
import net.sf.anathema.lib.control.ChangeListener;
import org.jmock.example.announcer.Announcer;

@RegisteredPreferenceModel
public class SheetPreferenceModel implements PreferenceModel {

  public static final PreferenceKey KEY = new PreferenceKey("hero.sheet.pagesize");
  private final Announcer<ChangeListener> announcer = Announcer.to(ChangeListener.class);
  private PageSize pageSize;

  @Override
  public void serializeTo(PreferencePto pto) {
    PreferenceValue value = new PreferenceValue(pageSize.name());
    pto.map.put(KEY, value);
  }

  @Override
  public void initializeFrom(PreferencePto pto) {
    PreferenceValue value = pto.map.get(KEY);
    this.pageSize = calculatePageSize(value);
  }

  public static PageSize calculatePageSize(PreferenceValue value) {
    if (value == null) {
      return PageSize.A4;
    }
    return PageSize.valueOf(value.value);
  }

  public PageSize[] getAvailableChoices() {
    return PageSize.values();
  }

  public void requestChangeTo(PageSize newValue) {
    this.pageSize = newValue;
    announcer.announce().changeOccurred();
  }

  public PageSize getSelectedPageSize() {
    return pageSize;
  }

  public void onChange(ChangeListener changeListener) {
    announcer.addListener(changeListener);
  }

  @Override
  public void whenDirtied(Command command) {
    onChange(new ChangeListener() {
      @Override
      public void changeOccurred() {
        command.execute();
      }
    });
  }
}