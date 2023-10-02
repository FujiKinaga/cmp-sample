package cmp.sample.shared.screen.core.ui

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual class WebBrowser() {
  actual fun open(url: String) {
    val nsUrl = NSURL.URLWithString(url) ?: return
    if (UIApplication.sharedApplication.canOpenURL(nsUrl)) {
      UIApplication.sharedApplication.openURL(nsUrl)
    }
  }
}
