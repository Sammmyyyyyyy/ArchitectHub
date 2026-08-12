import logo from "./assets/logo-3.png";
import styles from "./navbar.module.css";
import GetStartedBtn from "./getstartedbtn.jsx";
function Navbar() {
  return (
    <div className={styles.navbar}>
      <div className={styles.title}>
        <img src={logo} alt="LOGO" className={styles.logo} />
        <span className="brand-name">ArchitectHub</span>
      </div>
      <div className={styles.navLinks}>
        <ul>
          <li>
            <span>Features</span>
          </li>
          <li>
            <span>How It Works</span>
          </li>
          <li>
            <span>Use Cases</span>
          </li>
          <li>
            <span>Pricing</span>
          </li>
          <li>
            <span>Docs</span>
          </li>
          <li>
            <span>About</span>
          </li>
        </ul>
      </div>
      <div className={styles.login}>
        <span>
          <button className={styles.loginBtn}>Log in</button>
        </span>
        <span>
          <GetStartedBtn value={"Get Started"}></GetStartedBtn>
        </span>
      </div>
    </div>
  );
}
export default Navbar;
