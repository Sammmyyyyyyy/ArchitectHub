import styles from "./features.module.css";
import Card from "./card";
import Tag from "./tag";
import { ListTodo } from "lucide-react";
import { RotateCcwClock } from "lucide-react";
import { ReplaceAll } from "lucide-react";
import { BookOpenCheck } from "lucide-react";
import { FileSearchCorner } from "lucide-react";
import { ArrowRight } from "lucide-react";
function Features() {
  const features = [
    {
      bgcolor: "#f0efff",
      logo: <ListTodo color="#5146e5" />,
      heading: "Architecture Decisions",
      detail:
        "Record important decisions with context, rationale, alternatives and impact.",
    },
    {
      bgcolor: "#e7f8f7",
      logo: <RotateCcwClock color="#0caaa3" />,
      heading: "Version History",
      detail:
        "Track changes over time and maintain a complete history of your architecture.",
    },
    {
      bgcolor: "#fff3df",
      logo: <ReplaceAll color="#f59e0b" />,
      heading: "Change Requests",
      detail: "Propose, review and approve changes with a structured workflow.",
    },
    {
      bgcolor: "#eaf5ff",
      logo: <BookOpenCheck color="#1688e5" />,
      heading: "Onboarding Made Easy",
      detail:
        "Help new developers get up to speed with curated learning paths.",
    },
    {
      bgcolor: "#fff0f3",
      logo: <FileSearchCorner color="#ef476f" />,
      heading: "Powerful Search",
      detail:
        "Find anything across projects, decisions, documents and team members.",
    },
  ];
  return (
    <div id="features" className={styles.Wrapper}>
      <Tag value={"Features"}></Tag>
      <div className={styles.Heading}>
        Everything you need to manage architecture knowledge with confidence
      </div>
      <div className={styles.Cards}>
        {features.map((feature, index) => {
          return (
            <Card
              bgcolor={feature.bgcolor}
              logo={feature.logo}
              heading={feature.heading}
              detail={feature.detail}
            />
          );
        })}
      </div>
      <button className={styles.Btn}>
        <span>Explore All Features</span>
        <ArrowRight size={15} style={{ paddingTop: 2 }} strokeWidth={3} />
      </button>
    </div>
  );
}

export default Features;
