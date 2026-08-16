import styles from "./sidebar.module.css";
import {
  LayoutGrid,
  FolderGit2,
  MousePointerClick,
  RotateCcwKey,
  Users,
  ChartNoAxesCombined,
  Settings,
} from "lucide-react";
import logo from "@/assets/logo-3.png";
function Sidebar() {
  return (
    <aside className={styles.sidebar}>
      {/* Brand */}
      <div className={styles.brand}>
        <div className={styles.brandLogo}>
          <img src={logo} alt="LOGO" />
        </div>

        <span className={styles.brandName}>ArchitectHub</span>
      </div>

      {/* Navigation */}
      <nav className={styles.navigation}>
        <ul>
          <li className={`${styles.navItem} ${styles.active}`}>
            <span className={styles.navIcon}>
              <LayoutGrid size={18} />
            </span>
            <span>Dashboard</span>
          </li>

          <li className={styles.navItem}>
            <span className={styles.navIcon}>
              <FolderGit2 size={18} />
            </span>
            <span>Projects</span>
          </li>

          <li className={styles.navItem}>
            <span className={styles.navIcon}>
              <MousePointerClick size={18} />
            </span>
            <span>Decisions</span>
          </li>

          <li className={styles.navItem}>
            <span className={styles.navIcon}>
              <RotateCcwKey size={18} />
            </span>
            <span>Change Requests</span>
          </li>

          <li className={styles.navItem}>
            <span className={styles.navIcon}>
              <Users size={18} />
            </span>
            <span>Developers</span>
          </li>

          <li className={styles.navItem}>
            <span className={styles.navIcon}>
              <ChartNoAxesCombined size={18} />
            </span>
            <span>Analytics</span>
          </li>

          <li className={styles.navItem}>
            <span className={styles.navIcon}>
              <Settings size={18} />
            </span>
            <span>Settings</span>
          </li>
        </ul>
      </nav>
    </aside>
  );
}

export default Sidebar;
