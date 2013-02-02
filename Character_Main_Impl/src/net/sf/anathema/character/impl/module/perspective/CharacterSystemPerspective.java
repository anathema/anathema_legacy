package net.sf.anathema.character.impl.module.perspective;

import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.framework.view.perspective.Perspective;
import net.sf.anathema.framework.view.perspective.PerspectiveAutoCollector;
import net.sf.anathema.initialization.reflections.ReflectionObjectFactory;
import net.sf.anathema.initialization.reflections.Weight;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;

@PerspectiveAutoCollector
@Weight(weight = 1)
public class CharacterSystemPerspective implements Perspective {
  @Override
  public String getTitle() {
    return "Character";
  }

  @Override
  public JComponent createContent(IAnathemaModel model, IResources resources, ReflectionObjectFactory instantiater) {
    JPanel panel = new JPanel();
    panel.add(new JTextField("Test the West"));
    return panel;
  }
}
