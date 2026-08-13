import styles from "./tag.module.css";
import { Dot } from "lucide-react";
function Tag({ value }) {
  return (
    <div className={styles.tag}>
      <Dot />
      <span>{value}</span>
    </div>
  );
}
export default Tag;
