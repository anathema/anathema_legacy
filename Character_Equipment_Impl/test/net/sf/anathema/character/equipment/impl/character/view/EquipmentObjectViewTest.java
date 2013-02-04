package net.sf.anathema.character.equipment.impl.character.view;

import net.sf.anathema.lib.model.BooleanModel;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class EquipmentObjectViewTest {

    @Test
    public void supportsAmpersandInSpecialtyNames() throws Exception {
        EquipmentObjectView view = new EquipmentObjectView();
        BooleanModel model = view.addStats("Setup");
        BooleanModel optionModel = view.addOptionFlag(model, "Test & Success");
        assertThat(optionModel, is(not(nullValue())));
    }
}
