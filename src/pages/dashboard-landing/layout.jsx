import styles from "./layout.module.css";
import Sidebar from "../../components/sidebar";
import RightSection from "./RightSection";
function MainSection() {
  return (
    <div className={styles.Layout}>
      <Sidebar></Sidebar>
      <RightSection></RightSection>
    </div>
  );
}

export default MainSection;
