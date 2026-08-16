import styles from "./herosection.module.css";
import {
  Folder,
  Users,
  BadgeCheck,
  GitPullRequestArrow,
  Crown,
  Grid2x2Check,
  LayoutGrid,
  Boxes,
  DatabaseCheck,
  DatabaseZap,
} from "lucide-react";
import heroImage from "@/assets/image.png";
function HeroSection() {
  return (
    <main className={styles.heroSection}>
      {/* =========================================
          WELCOME HERO
          ========================================= */}

      <section className={styles.welcomeCard}>
        <div className={styles.welcomeContent}>
          <h1>Welcome to ArchitectHub, Samyak! 👋</h1>

          <p>Let's set up your workspace and get your team started.</p>

          <div className={styles.welcomeButtons}>
            <button className={styles.primaryButton}>
              <span className={styles.buttonIcon}>
                <Folder size={20} />
              </span>
              Create Your First Project
            </button>

            <button className={styles.secondaryButton}>
              <span className={styles.buttonIcon}>
                <Users size={20} />
              </span>
              Add Team Members
            </button>
          </div>
        </div>

        {/* Image / Illustration Placeholder */}
        <div
          className={styles.welcomeIllustration}
          style={{
            backgroundImage: `url(${heroImage})`,
            backgroundSize: "contain",
            marginRight: "64px",
          }}
        ></div>
      </section>

      {/* =========================================
          STATS
          ========================================= */}

      <section className={styles.statsCard}>
        {/* Projects */}

        <div className={styles.statItem}>
          <div className={`${styles.statIcon} ${styles.greenIcon}`}>
            <Folder />
          </div>

          <div className={styles.statInfo}>
            <h2>0</h2>
            <h3>Projects</h3>
            <p>No projects created yet</p>
          </div>
        </div>

        {/* Team Members */}

        <div className={styles.statItem}>
          <div className={`${styles.statIcon} ${styles.blueIcon}`}>
            <Users />
          </div>

          <div className={styles.statInfo}>
            <h2>1</h2>
            <h3>Team Members</h3>
            <p>Only you for now</p>
          </div>
        </div>

        {/* Decisions */}

        <div className={styles.statItem}>
          <div className={`${styles.statIcon} ${styles.orangeIcon}`}>
            <BadgeCheck />
          </div>

          <div className={styles.statInfo}>
            <h2>0</h2>
            <h3>Decisions</h3>
            <p>No decisions made yet</p>
          </div>
        </div>

        {/* Change Requests */}

        <div className={styles.statItem}>
          <div className={`${styles.statIcon} ${styles.purpleIcon}`}>
            <GitPullRequestArrow />
          </div>

          <div className={styles.statInfo}>
            <h2>0</h2>
            <h3>Change Requests</h3>
            <p>No change requests yet</p>
          </div>
        </div>
      </section>

      {/* =========================================
          LOWER SECTION
          ========================================= */}

      <section className={styles.lowerSection}>
        {/* =====================================
            WHAT TO DO NEXT
            ===================================== */}

        <div className={styles.nextStepsCard}>
          <div className={styles.sectionHeading}>
            <h2>What to do next?</h2>

            <p>
              Complete these quick steps to get the most out of ArchitectHub.
            </p>
          </div>

          <div className={styles.stepsList}>
            {/* Step 1 */}

            <div className={styles.stepItem}>
              <div className={styles.stepNumber}>1</div>

              <div className={`${styles.stepIcon} ${styles.greenIcon}`}>
                <Folder />
              </div>

              <div className={styles.stepContent}>
                <h3>Create your first project</h3>

                <p>
                  Start by creating a project. You can add details, modules, and
                  more.
                </p>
              </div>

              <span className={styles.stepStatus}>Not Started</span>
            </div>

            {/* Step 2 */}

            <div className={styles.stepItem}>
              <div className={styles.stepNumber}>2</div>

              <div className={`${styles.stepIcon} ${styles.greenIcon}`}>
                <Users />
              </div>

              <div className={styles.stepContent}>
                <h3>Add your team members</h3>

                <p>
                  Invite your team and assign roles to collaborate on projects.
                </p>
              </div>

              <span className={styles.stepStatus}>Not Started</span>
            </div>

            {/* Step 3 */}

            <div className={styles.stepItem}>
              <div className={styles.stepNumber}>3</div>

              <div className={`${styles.stepIcon} ${styles.greenIcon}`}>
                <Crown />
              </div>

              <div className={styles.stepContent}>
                <h3>Assign a project lead</h3>

                <p>Choose a lead who will manage decisions and architecture.</p>
              </div>

              <span className={styles.stepStatus}>Not Started</span>
            </div>

            {/* Step 4 */}

            <div className={styles.stepItem}>
              <div className={styles.stepNumber}>4</div>

              <div className={`${styles.stepIcon} ${styles.greenIcon}`}>
                <Grid2x2Check />
              </div>

              <div className={styles.stepContent}>
                <h3>Make your first decision</h3>

                <p>
                  Create an architectural decision and start building great
                  things.
                </p>
              </div>

              <span className={styles.stepStatus}>Not Started</span>
            </div>
          </div>

          {/* Tip */}

          <div className={styles.tipBox}>
            <span className={styles.tipIcon}>{/* Icon placeholder */}</span>

            <p>
              <strong>Tip:</strong> You can always come back to these steps
              anytime.
            </p>
          </div>
        </div>

        {/* =====================================
            WHY TEAMS LOVE ARCHITECTHUB
            ===================================== */}

        <div className={styles.featuresCard}>
          <div className={styles.sectionHeading}>
            <h2>Why teams love ArchitectHub?</h2>
          </div>

          <div className={styles.featuresList}>
            {/* Feature 1 */}

            <div className={styles.featureItem}>
              <div className={`${styles.featureIcon} ${styles.greenIcon}`}>
                <LayoutGrid />
              </div>

              <div>
                <h3>Centralized Decisions</h3>

                <p>Document and track architectural decisions in one place.</p>
              </div>
            </div>

            <div className={styles.featureItem}>
              <div className={`${styles.featureIcon} ${styles.greenIcon}`}>
                <Boxes />
              </div>

              <div>
                <h3>Better Collaboration</h3>

                <p>Bring your team together and build better systems.</p>
              </div>
            </div>

            {/* Feature 3 */}

            <div className={styles.featureItem}>
              <div className={`${styles.featureIcon} ${styles.greenIcon}`}>
                <DatabaseCheck />
              </div>

              <div>
                <h3>Traceable Changes</h3>

                <p>Track change requests and their impact effortlessly.</p>
              </div>
            </div>

            {/* Feature 4 */}

            <div className={styles.featureItem}>
              <div className={`${styles.featureIcon} ${styles.greenIcon}`}>
                <DatabaseZap />
              </div>

              <div>
                <h3>Data-Driven Insights</h3>

                <p>Make informed decisions with powerful analytics.</p>
              </div>
            </div>
          </div>

          <div className={styles.featureFooter}>
            Let's build something amazing together! 🚀
          </div>
        </div>
      </section>
    </main>
  );
}

export default HeroSection;
