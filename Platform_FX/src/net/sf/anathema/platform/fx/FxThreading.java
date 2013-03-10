package net.sf.anathema.platform.fx;

import javafx.application.Platform;

public class FxThreading {

  public static void assertOnFxThread() {
    if (!Platform.isFxApplicationThread()) {
      throw new IllegalStateException("Operation must be performed on FX Thread");
    }
  }

  public static void assertNotOnFxThread() {
    if (Platform.isFxApplicationThread()) {
      throw new IllegalStateException("Operation must NOT be performed on FX Thread");
    }
  }

  public static void runOnCorrectThread(Runnable runnable) {
    if (Platform.isFxApplicationThread()) {
      runnable.run();
    } else {
      Platform.runLater(runnable);
    }
  }
}
