import styles from "./footer.module.css";
import { FaGithub } from "react-icons/fa";
import { BsLinkedin } from "react-icons/bs";

function Footer() {
  return (
    <footer className={styles.Footer}>
      <div className={styles.Main}>
        {/* Brand */}
        <div className={styles.Brand}>
          <div className={styles.Logo}>ArchitectHub</div>

          <div className={styles.Description}>
            A centralized platform for documenting, managing and collaborating
            on software architecture decisions.
          </div>

          <div className={styles.ProjectText}>
            Built as a Software Engineering Project
          </div>
        </div>

        {/* Project */}
        <div className={styles.Column}>
          <div className={styles.Title}>Project</div>

          <a href="#features">Features</a>
          <a href="#how-it-works">How It Works</a>
          <a href="#use-cases">Use Cases</a>
          <a href="#architecture">Architecture</a>
        </div>

        {/* Team */}
        <div className={styles.Column}>
          <div className={styles.Title}>Built By</div>

          <div className={styles.Member}>
            <span>Samyak</span>

            <div className={styles.Socials}>
              <a
                href="YOUR_SAMYAK_GITHUB_LINK"
                target="_blank"
                rel="noreferrer"
              >
                <FaGithub size={14}></FaGithub>
              </a>

              <a
                href="YOUR_SAMYAK_LINKEDIN_LINK"
                target="_blank"
                rel="noreferrer"
              >
                <BsLinkedin size={14} />
              </a>
            </div>
          </div>

          <div className={styles.Member}>
            <span>Dipesh</span>

            <div className={styles.Socials}>
              <a
                href="YOUR_DIPESH_GITHUB_LINK"
                target="_blank"
                rel="noreferrer"
              >
                <FaGithub size={14}></FaGithub>
              </a>

              <a
                href="YOUR_DIPESH_LINKEDIN_LINK"
                target="_blank"
                rel="noreferrer"
              >
                <BsLinkedin size={14} />
              </a>
            </div>
          </div>
        </div>
      </div>

      <div className={styles.Bottom}>
        <span>© 2026 ArchitectHub</span>

        <span>Built by Samyak &amp; Dipesh</span>
      </div>
    </footer>
  );
}

export default Footer;
