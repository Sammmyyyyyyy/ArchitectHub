import styles from "./marquee.module.css";
import {
  Package,
  NotebookPen,
  Scaling,
  FileText,
  MessageSquareQuote,
  ListCheck,
  UsersRound,
  ClipboardClock,
} from "lucide-react";
const values = [
  <>
    <NotebookPen size={15} /> <span>Plan</span>
  </>,
  <>
    <Scaling size={15} /> <span>Design</span>
  </>,
  <>
    <FileText size={15} /> <span>Document</span>
  </>,
  <>
    <MessageSquareQuote size={15} /> <span>Review</span>
  </>,
  <>
    <UsersRound size={15} /> <span>Collaborate</span>
  </>,
  <>
    <ListCheck size={15} /> <span>Decide</span>
  </>,
  <>
    <ClipboardClock size={15} /> <span>Track</span>
  </>,
  <>
    <Package size={15} /> <span>Deliver</span>
  </>,
];
function Marquee() {
  return (
    <div className={styles.Container}>
      <div className={styles.Heading}>
        <span>Everything your engineering team needs</span>
      </div>
      <div className={styles.Marquee}>
        <div className={styles.Group}>
          <div className={styles.Content}>
            {[...values].map((value, index) => (
              <>
                <span className={styles.Seperator}>✦</span>
                <span className={styles.Reasons} key={index}>
                  {value}
                </span>
              </>
            ))}
          </div>
          <div className={styles.Content}>
            {[...values].map((value, index) => (
              <>
                <span className={styles.Seperator}>✦</span>
                <span className={styles.Reasons} key={index}>
                  {value}
                </span>
              </>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
export default Marquee;
