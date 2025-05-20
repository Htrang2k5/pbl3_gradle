package pbl3_gradle.util;

import java.util.HashMap;
import java.util.Map;

public class AppContext {
    private static final Map<String, Object> context = new HashMap<>();

    // Lưu dữ liệu
    public static void set(String key, Object value) {
        context.put(key, value);
    }

    // Lấy dữ liệu
    public static Object get(String key) {
        return context.get(key);
    }

    // Xóa một key cụ thể
    public static void remove(String key) {
        context.remove(key);
    }

    // Xóa toàn bộ dữ liệu trong context (nếu cần reset phiên)
    public static void clear() {
        context.clear();
    }
}
