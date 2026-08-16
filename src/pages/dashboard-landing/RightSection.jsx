import Header from "../../components/header";
import styles from "./RightSection.module.css";
import HeroSection from "./herosection";
function RightSection() {
  return (
    <div className={styles.RightSection}>
      <Header></Header>
      <HeroSection></HeroSection>
    </div>
  );
}

export default RightSection;
