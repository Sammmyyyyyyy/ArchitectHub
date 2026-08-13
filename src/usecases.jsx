import styles from "./usecases.module.css";
import Card from "./card";
import Tag from "./tag";

import { Rocket, UsersRound, Building2 } from "lucide-react";

import dashboard from "./assets/dashboard.png";

function UseCases() {
  const useCases = [
    {
      bgcolor: "#f0efff",
      logo: <Rocket color="#5146e5" />,
      heading: "For Startups",
      detail:
        "Move fast with clear documentation and avoid repeating mistakes.",
    },
    {
      bgcolor: "#e7f8f7",
      logo: <UsersRound color="#0caaa3" />,
      heading: "For Growing Teams",
      detail: "Scale your knowledge base and keep everyone on the same page.",
    },
    {
      bgcolor: "#eaf5ff",
      logo: <Building2 color="#1688e5" />,
      heading: "For Enterprises",
      detail: "Ensure compliance, security and governance at scale.",
    },
  ];

  return (
    <div id="usecases" className={styles.MainWrapper}>
      {/* USE CASES */}
      <div className={styles.UseCases}>
        <Tag value="Use Cases" />

        <div className={styles.Heading}>Built for every engineering team</div>

        <div className={styles.SubHeading}>
          Whether you're a startup or an enterprise, ArchitectHub adapts to your
          needs.
        </div>

        <div className={styles.Cards}>
          {useCases.map((useCase, index) => (
            <Card
              key={index}
              bgcolor={useCase.bgcolor}
              logo={useCase.logo}
              heading={useCase.heading}
              detail={useCase.detail}
            />
          ))}
        </div>
      </div>

      {/* CTA */}
      <div className={styles.CTA}>
        <div className={styles.CTAContent}>
          <div className={styles.CTAHeading}>
            Ready to simplify your architecture documentation?
          </div>

          <div className={styles.CTADescription}>
            Join hundreds of engineering teams already using ArchitectHub to
            build better software.
          </div>

          <div className={styles.Buttons}>
            <button className={styles.PrimaryBtn}>Get Started for Free</button>

            <button className={styles.SecondaryBtn}>Book a Demo</button>
          </div>
        </div>

        <img
          className={styles.CTAImage}
          src={dashboard}
          alt="ArchitectHub dashboard"
        />
      </div>
    </div>
  );
}

export default UseCases;
