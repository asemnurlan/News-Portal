import java.sql.*;

public class Database {
    private static final String URL = "jdbc:sqlite:newsportal.db";

    static {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement st = conn.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT UNIQUE NOT NULL,
                    subscribed INTEGER NOT NULL DEFAULT 0,
                    strategy TEXT DEFAULT 'email'
                )
            """);
            System.out.println("[DB] users table ready");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String name, String strategy, boolean subscribed) {
        String sql = "INSERT OR REPLACE INTO users(name, strategy, subscribed) VALUES(?,?,?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, strategy);
            ps.setInt(3, subscribed ? 1 : 0);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSubscribed(String name) {
        String sql = "SELECT subscribed FROM users WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt("subscribed") == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getStrategy(String name) {
        String sql = "SELECT strategy FROM users WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getString("strategy") : "email";
        } catch (SQLException e) {
            e.printStackTrace();
            return "email";
        }
    }

    public static void updateSubscription(String name, boolean subscribed, String strategy) {
        String sql = "UPDATE users SET subscribed = ?, strategy = ? WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, subscribed ? 1 : 0);
            ps.setString(2, strategy);
            ps.setString(3, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
