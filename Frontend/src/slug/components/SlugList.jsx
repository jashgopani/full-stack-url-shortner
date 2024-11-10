import { BACKEND_URL } from "../utils/constants";

function SlugList({ slugs }) {
  return (
    <div className='row'>
      <h2>Your Shortened URL list</h2>
      {!slugs && <div className='alert alert-info'>No URLs shortened yet</div>}

      {slugs &&
        slugs.map((slug) => (
          <div key={slug.id} className='card my-2 p-0'>
            <div className='card-header d-flex flex-row justify-content-between'>
              <a
                href={`${BACKEND_URL}/${slug.id}`}
                target='_blank'
                rel='noopener noreferrer'>{`${BACKEND_URL}/${slug.id}`}</a>
              <a
                className='btn btn-success btn-sm'
                href={`${BACKEND_URL}/${slug.id}`}
                target='_blank'
                rel='noopener noreferrer'>
                Open URL <i className='fas fa-external-link-alt'></i>
              </a>
            </div>
            <div className='card-body '>
              <a href={`${slug.url}`} target='_blank' rel='noopener noreferrer'>{`${slug.url}`}</a>
            </div>
          </div>
        ))}
    </div>
  );
}

export default SlugList;
