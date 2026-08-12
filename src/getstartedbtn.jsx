import styles from "./getstartedbtn.module.css";
function GetStartedBtn({ value }) {
  return <button className={styles.getStartedBtn}>{value}</button>;
}
export default GetStartedBtn;
