package net.sf.anathema.lib.gui.dialog.core;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class DialogDefaults {

  private static final DialogDefaults instance = new DialogDefaults();

  public static DialogDefaults getInstance() {
    return instance;
  }

  private final List<Image> frameIconImages = new ArrayList<Image>();

  private DialogDefaults() {
    //nothing to do 
  }

  public List<Image> getFrameIconImages() {
    return frameIconImages;
  }
}