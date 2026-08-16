import { Bell, Settings, ChevronDown, Search } from "lucide-react";
import styles from "./header.module.css";

function Header() {
  return (
    <header className={styles.Header}>
      {/* Search */}
      <div className={styles.Search}>
        <Search className={styles.searchIcon} size={18} />
        <input type="text" placeholder="Search anything..." />
      </div>

      {/* Right Section */}
      <div className={styles.ProfileSection}>
        <ul>
          {/* Notifications */}
          <li className={styles.notifications}>
            <div className={styles.notificationIcon}>
              <Bell size={22} strokeWidth={1.8} />
            </div>
          </li>

          {/* Settings */}
          <li className={styles.settings}>
            <Settings size={22} strokeWidth={1.8} />
          </li>

          {/* User Profile */}
          <li className={styles.userProfile}>
            <div className={styles.profilePic}></div>

            <div className={styles.UserNameRole}>
              <span className={styles.Name}>Samyak</span>

              <span className={styles.Role}>Admin</span>
            </div>

            <div className={styles.dropDownArrow}>
              <ChevronDown size={18} strokeWidth={1.8} />
            </div>
          </li>
        </ul>
      </div>
    </header>
  );
}

export default Header;
