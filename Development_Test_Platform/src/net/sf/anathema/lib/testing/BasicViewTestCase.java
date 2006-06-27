package net.sf.anathema.lib.testing;

import java.awt.Component;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class BasicViewTestCase extends BasicTestCase {
  
  private List<Frame>  allFrames = new ArrayList<Frame>();
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void showComponent(Component component) {
    JFrame frame = new JFrame();
    allFrames.add(frame);
    frame.getContentPane().add(component);
    frame.pack();
    frame.setVisible(true);
  }
  
  @Override
  protected void tearDown() throws Exception {
    super.tearDown();
    for (int index = 0; index < allFrames.size(); index++) {
      Frame frame = allFrames.get(index);
      allFrames.remove(frame);
      frame.dispose();
    }
  }
  
}