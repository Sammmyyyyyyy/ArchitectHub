import logo from "@/assets/logo-3.png";
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
            <span>
              <a href="#features">Features</a>
            </span>
          </li>
          <li>
            <span>
              <a href="#howitworks">How It Works</a>
            </span>
          </li>
          <li>
            <span>
              <a href="#usecases">Use Cases</a>
            </span>
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
          <button id={styles.loginbtn} className={styles.loginBtn}>
            Log in
          </button>
        </span>
        <span>
          <GetStartedBtn value={"Get Started"}></GetStartedBtn>
        </span>
      </div>
    </div>
  );
}
export default Navbar;
