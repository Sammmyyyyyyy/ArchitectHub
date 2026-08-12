import styles from "./tag.module.css";
function Tag({ value }) {
  return <div className={styles.tag}>{value}</div>;
}
export default Tag;
