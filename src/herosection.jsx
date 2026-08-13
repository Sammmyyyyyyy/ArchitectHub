import styles from "./herosection.module.css";
import Tag from "./tag";
import GetStartedBtn from "./getstartedbtn";
import { Play } from "lucide-react";
import { MessageCircleCheck } from "lucide-react";
import { User } from "lucide-react";
import { ShieldCheck } from "lucide-react";
import dashboard_preview from "./assets/dashboard.png";
function HeroSection() {
  return (
    <div className={styles.hero}>
      <div className={styles.leftSection}>
        <Tag value="For Engineering Teams"></Tag>
        <div className={styles.tagLine}>
          <div className={styles.lineOne}>Document. Decide.</div>
          <div className={styles.lineTwo}>Deliver Better Software.</div>
        </div>
        <div className={styles.description}>
          ArchitectHub helps engineering teams document achitecture decisions,
          track changes, and share knowledge - all in one place.
        </div>

        <div className={styles.buttons}>
          <GetStartedBtn value={"Get Started for Free"}></GetStartedBtn>
          <button className={styles.demo}>
            <Play size={"12px"} color={"#028181"} />
            <span> Watch Demo</span>
          </button>
        </div>

        <div className={styles.properties}>
          <div className={styles.Item}>
            <MessageCircleCheck size={"12px"} strokeWidth={2} />
            <span>Open Source Friendly</span>
          </div>
          <div className={styles.Item}>
            <User size={"12px"} strokeWidth={2} />
            <span>Role-based Access</span>
          </div>
          <div className={styles.Item}>
            <ShieldCheck size={"12px"} strokeWidth={2} />
            <span>Secure & Scalable</span>
          </div>
        </div>
      </div>
      <div className={styles.rightSection}>
        <img src={dashboard_preview} alt="DASHBOARD_PREVIEW" />
      </div>
    </div>
  );
}

export default HeroSection;
