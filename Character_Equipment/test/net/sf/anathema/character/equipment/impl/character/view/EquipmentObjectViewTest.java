package net.sf.anathema.character.equipment.impl.character.view;

import net.sf.anathema.hero.equipment.display.view.SwingEquipmentItemView;
import net.sf.anathema.lib.model.IModifiableBooleanModel;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class EquipmentObjectViewTest {

  @Test
  public void supportsAmpersandInSpecialtyNames() throws Exception {
    SwingEquipmentItemView view = new SwingEquipmentItemView();
    IModifiableBooleanModel model = view.addStats("Setup");
    IModifiableBooleanModel optionModel = view.addOptionFlag(model, "Test & Success");
    assertThat(optionModel, is(not(nullValue())));
  }
}
