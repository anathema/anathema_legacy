package net.sf.anathema.character.impl.view.advantage.demo;

import net.sf.anathema.character.impl.view.advantage.EssencePanelView;
import net.sf.anathema.character.impl.view.demo.BasicCharacterDemoCase;
import net.sf.anathema.character.library.intvalue.IIntValueDisplayFactory;
import net.sf.anathema.framework.resources.AnathemaResources;

public class EssencePanelViewDemo extends BasicCharacterDemoCase {

  public void demo() throws Exception {
    AnathemaResources resources = new AnathemaResources();
    IIntValueDisplayFactory configuration = createMortalGuiConfiguration(resources);
    EssencePanelView essencePanelView = new EssencePanelView(configuration);
    essencePanelView.addEssenceView("Essence", 2, 5); //$NON-NLS-1$
    essencePanelView.addPoolView("Personal Essence", "18"); //$NON-NLS-1$ //$NON-NLS-2$
    essencePanelView.addPoolView("Peripheral Essence", "25"); //$NON-NLS-1$ //$NON-NLS-2$
    show(essencePanelView.getPanel().getContent());
  }
}