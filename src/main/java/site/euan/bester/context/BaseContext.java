package site.euan.bester.context;

/**
 * @author EUAN
 * 本地ID存储
 */
public class BaseContext {
    private static final ThreadLocal<Long> currentId = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        currentId.set(id);
    }

    public static Long getCurrentId() {
        return currentId.get();
    }

    public static void clear() {
        currentId.remove();
    }
}
