package sl.chat.dev.common.publics.sensitive.core.support.deny;

import sl.chat.dev.common.publics.sensitive.common.annotation.ThreadSafe;
import sl.chat.dev.common.publics.sensitive.common.utils.FileUtils;
import sl.chat.dev.common.publics.sensitive.core.api.IWordDeny;

import java.io.InputStream;
import java.util.List;

@ThreadSafe
public class FileWordDeny implements IWordDeny {

  private InputStream ios;

  public FileWordDeny(InputStream ios) {
    this.ios = ios;
  }

  @Override
  public List<String> deny() {
    return FileUtils.readAllLines(ios);
  }

}
