import styles from "./howitworks.module.css";
import Tag from "./tag";
import Card from "./card";

import {
  FilePlus2,
  MessageSquareText,
  UsersRound,
  CircleCheck,
  History,
} from "lucide-react";

function HowItWorks() {
  const steps = [
    {
      bgcolor: "#f0efff",
      logo: <FilePlus2 color="#5146e5" />,
      heading: "Create",
      detail:
        "Create an architecture decision and capture its context, rationale, alternatives and impact.",
    },
    {
      bgcolor: "#fff3df",
      logo: <MessageSquareText color="#f59e0b" />,
      heading: "Review",
      detail:
        "Share the decision with your team and collect feedback before anything is finalized.",
    },
    {
      bgcolor: "#e7f8f7",
      logo: <UsersRound color="#0caaa3" />,
      heading: "Collaborate",
      detail:
        "Discuss alternatives, resolve concerns and bring everyone involved into the decision.",
    },
    {
      bgcolor: "#eaf5ff",
      logo: <CircleCheck color="#1688e5" />,
      heading: "Approve",
      detail:
        "Finalize the decision once the team agrees and establish a clear source of truth.",
    },
    {
      bgcolor: "#fff0f3",
      logo: <History color="#ef476f" />,
      heading: "Track",
      detail:
        "Track decisions and changes while keeping your architecture history organized.",
    },
  ];

  return (
    <div id="howitworks" className={styles.Wrapper}>
      <Tag value="How It Works" />

      <div className={styles.Heading}>
        A simple workflow for better architecture decisions
      </div>
      <div className={styles.Workflow}>
        {steps.map((step, index) => (
          <div className={styles.StepWrapper} key={index}>
            <Card
              bgcolor={step.bgcolor}
              logo={step.logo}
              heading={step.heading}
              detail={step.detail}
            />

            {index !== steps.length - 1 && (
              <div className={styles.Arrow}>→</div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}

export default HowItWorks;
