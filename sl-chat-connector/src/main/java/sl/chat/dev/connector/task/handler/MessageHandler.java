package sl.chat.dev.connector.task.handler;

public interface MessageHandler<T> {

  public void handler(T data);

}
