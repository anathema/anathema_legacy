package net.sf.anathema.character.equipment.impl.character.view;

import net.sf.anathema.hero.equipment.display.presenter.StatsView;
import net.sf.anathema.hero.equipment.display.view.SwingEquipmentItemView;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class EquipmentObjectViewTest {

  @Test
  public void supportsAmpersandInSpecialtyNames() throws Exception {
    SwingEquipmentItemView view = new SwingEquipmentItemView();
    StatsView statsView = view.addStats("Setup");
    StatsView optionModel = statsView.addOptionFlag("Test & Success");
    assertThat(optionModel, is(not(nullValue())));
  }
}
