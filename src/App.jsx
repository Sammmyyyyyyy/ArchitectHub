import Navbar from "./navbar";
import HeroSection from "./herosection";
import Marquee from "./marquee";
import Features from "./features";
import HowItWorks from "./howitworks";
import UseCases from "./usecases";
import Footer from "./footer";
function App() {
  return (
    <>
      <Navbar />
      <HeroSection />
      <Marquee></Marquee>
      <Features></Features>
      <HowItWorks></HowItWorks>
      <UseCases></UseCases>
      <Footer></Footer>
    </>
  );
}

export default App;
