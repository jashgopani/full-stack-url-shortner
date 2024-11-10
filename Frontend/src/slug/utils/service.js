import { BACKEND_URL } from "./constants";
import axios from "axios";

const axiosInstance = axios.create({
  baseURL: BACKEND_URL,
});

/**
 * Initialize the session storage
 */
const initializeStorage = () => {
  sessionStorage.clear();
};

/**
 * Read the slugs from the session storage
 * @returns {Array} slugs
 */
const readLocalSlugs = () => {
  const slugs = sessionStorage.getItem("slugs");
  return slugs ? JSON.parse(slugs) : [];
};

/**
 * Slugs to be inserted in the session storage
 * @param {Array} slugs
 */
const writeNewSlugs = (slugs) => {
  const existingSlugs = readLocalSlugs();
  const newSlugs = [...existingSlugs, ...slugs];
  sessionStorage.setItem("slugs", JSON.stringify(newSlugs));
};

/**
 * Fetch the slugs from the backend and cache them in the session storage
 * @returns {Array} slugs
 */
const fetchSlugs = async () => {
  const response = await axiosInstance.get("/slugs");
  if (response.status === 200) {
    writeNewSlugs(response.data);
    return response.data;
  } else if (response.data.error) {
    throw new Error(response.data.error);
  } else {
    throw new Error("Something went wrong. Please try again ");
  }
};

/**
 * Gets a new short url for the given url and also writes it to the session storage
 * @param {String} url
 * @returns A slug object {slug:string, url:string}
 */
const shortenURL = async (url) => {
  try {
    const urlParams = new URLSearchParams();
    urlParams.append("url", url);
    const response = await axiosInstance.post("/", urlParams);
    if (response.status === 201) {
      const newSlug = response.data;
      writeNewSlugs([newSlug]);
      return newSlug;
    } else if (response.data.error) {
      throw new Error(response.data.error);
    } else {
      throw new Error("Something went wrong. Please try again");
    }
  } catch (error) {
    return { error: error.message };
  }
};

export { initializeStorage, readLocalSlugs, writeNewSlugs, fetchSlugs, shortenURL };
