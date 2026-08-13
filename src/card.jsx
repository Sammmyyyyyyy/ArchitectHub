import styles from "./card.module.css";
function Card({ bgcolor, logo, heading, detail }) {
  return (
    <div className={styles.Card}>
      <div className={styles.Logo} style={{ backgroundColor: bgcolor }}>
        {logo}
      </div>
      <div className={styles.Heading}>{heading}</div>
      <div className={styles.Detail}>{detail}</div>
    </div>
  );
}
export default Card;
