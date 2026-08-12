import styles from "./herosection.module.css";
import Tag from "./tag";
import GetStartedBtn from "./getstartedbtn";
import { Play } from "lucide-react";
function HeroSection() {
  return (
    <div className={styles.hero}>
      <div className={styles.leftSection}>
        <Tag value="For Engineering Teams"></Tag>
        <div className={styles.tagLine}>
          <div className={styles.lineOne}>Document. Decide.</div>
          <div className={styles.lineTwo}>Deliver Better Software.</div>
          <div className={styles.description}>
            ArchitectHub helps engineering teams document achitecture decisions,
            track changes, and share knowledge - all in one place.
          </div>

          <div className={styles.buttons}>
            <GetStartedBtn value={"Get Started for Free"}></GetStartedBtn>
            <Play />
          </div>
        </div>
      </div>
      <div className={styles.rightSection}></div>
    </div>
  );
}

export default HeroSection;
