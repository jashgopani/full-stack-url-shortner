import "./App.css";
import URLShortnerForm from "./slug/components/URLShortnerForm";
import { useState, useEffect } from "react";
import { fetchSlugs, initializeStorage } from "./slug/utils/service";
import SlugList from "./slug/components/SlugList";

function App() {
  const [shortURLList, setShortURLList] = useState([]);
  useEffect(() => {
    initializeStorage();
    const fetchShortURLs = async () => {
      try {
        const slugs = await fetchSlugs();
        console.log("Fetching short urls from the backend", slugs);
        setShortURLList(slugs);
      } catch (error) {
        console.error("Error fetching short urls", error);
      }
    };
    fetchShortURLs();
  }, []);

  return (
    <>
      <div className='container'>
        <URLShortnerForm updateURLListCallback={setShortURLList} />
        <SlugList slugs={shortURLList} />
      </div>
    </>
  );
}

export default App;
