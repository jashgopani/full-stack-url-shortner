import { useState, useEffect } from "react";
import { shortenURL } from "../utils/service";

function URLShortnerForm({ updateURLListCallback }) {
  const [inputURL, setInputURL] = useState("");
  const [errors, setErrors] = useState([]);

  useEffect(() => {
    if (errors.length > 0) {
      const timer = setTimeout(() => {
        setErrors([]);
      }, 5000);
      return () => clearTimeout(timer);
    }
  }, [errors]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    console.log("Form submitted", inputURL);
    const response = await shortenURL(inputURL);
    if (response.error) {
      setErrors([...errors, response.error]);
    } else {
      setInputURL("");
      updateURLListCallback((prevURLList) => [...prevURLList, response]);
    }
  };
  return (
    <>
      {errors.length > 0 && (
        <div className='row my-2'>
          <div className='alert alert-danger alert-dismissible fade show' role='alert'>
            {errors.map((error, index) => (
              <div key={index}>{error}</div>
            ))}
          </div>
        </div>
      )}
      <div className='row my-2'>
        <h1>URL Shortner</h1>
        <form onSubmit={handleSubmit}>
          <div className='mb-3'>
            <label htmlFor='url' className='form-label'>
              Orignal URL
            </label>
            <input
              type='url'
              className='form-control'
              id='url'
              name='url'
              aria-describedby='originalURLHelp'
              onChange={(e) => setInputURL(e.target.value)}
            />
            <div id='originalURLHelp' className='form-text'>
              The URL you want to shorten
            </div>
          </div>
          <div className='d-grid'>
            <button type='submit' className='btn btn-primary btn-large'>
              Shorten
            </button>
          </div>
        </form>
      </div>
    </>
  );
}
export default URLShortnerForm;
