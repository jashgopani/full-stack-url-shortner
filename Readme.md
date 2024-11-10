# URL Shortener Take-Home Exercise (Fullstack)

## Instructions

Your task is to create a URL shortener web application (similar to [bitly](https://bitly.com/) or [TinyURL](https://tinyurl.com/)). This exercise is intentionally open-ended, and you are welcome to implement your solution using the language and tech stack of your choice. If you are familiar with React & Next.js, please use those for your submission. The core functionality of the application should be expressed through your own original code.

You should aim to spend no more than 2 hours on this project. If you don't complete everything in 2 hours, please submit what you have - we value your time and want to see your prioritization skills.

### Application Description

At the root path (e.g., http://localhost:8080/), a user should be presented with a form that allows them to input a URL. When a user submits that form, it should convert the input URL to a shortened version and present that to the user.

The shortened URL should be in the format: http://localhost:8080/{slug}, where `{slug}` is a unique identifier for the original URL.

When a user navigates to the shortened URL, they should be redirected to the original URL that was used to generate this shortened URL.

### Minimum Requirements

* Format and method of generating slugs for shortened URLs are up to you
* Shortened URLs do not need to persist across server shutdown/startup (i.e., setting up a DB isn't necessary - server memory should suffice)
* Only allow valid http(s) URLs

If you have additional time, consider spending it on testing or UI improvements as opposed to supplemental features.

## Evaluation Criteria

We will be evaluating your submission based on the following:

1. Functionality: Does the application work as described?
2. Code quality: Is the code clean, well-organized, and following best practices?
3. Error handling: How does the application handle invalid inputs or errors?
4. Technical choices: Are the chosen technologies appropriate for the task?
5. Documentation: Is the code well-commented and the README clear?

## Deliverables

Please fill out the sections below in the _README.md_ of your project and submit according to the instructions you received with this project. Your code can be sent as a zip file or a link to a repository containing your project.

---

## Implementation Details
### Choosing Technologies

#### Frontend
1. The choice for frontend was going to be React.js because it allows us to build interfaces in form of reusable components, providing more flexibility and state management capabilities.
2. I chose Bootstrap 5 for styling because it is easy to use and because I've used it the most in the past.
3. Instead of using the fetch API, I chose Axios for making API requests because it provides abstraction over the fetch API which makes hanlding requests easier and the code more scalable and maintainable.

#### Backend
1. For the frontend I had to decide between Node.js and Spring Boot. While node.js is a good choice for building RESTful APIs quickly, I decided to go with Spring Boot for the following reasons
   1. Scalability and Performance: While Node.js can handle a large number of requests through its non-blocking architecture, Spring Boot is is more scalable, performant and provides security out of the box which also makes it production ready.
   2. Developer Productivity: Spring Boot annotations are powerful and avoid boilerplate code, and Java being an Object oriented language helps to model entites and behaviours easily. While Typescript was an option that I considered but I find Java to be more robust and easier to work with.
   3. Spring boot has a slightly larger initial memory footprint compared to Node.js but I felt that the tradeoff was worth it for long term perspective.
2. The libaray `JNanoId` was used to generate unique slugs for the shortened URLs. It is a Java implementation of NanoId library on NPM which is a tiny, secure unique string ID generator for JavaScript.
   1. I states on its page that it is better than standard UUID and it also has a collision probability calculator which I checked for 10 characters and 1M requests / hour and it was 65 days which is decent for this use case. Later I found that for 11 characters and 1M requests / hour the collision probability is 1 year which is even better.
   2. It also provides the flexibility to provide alphabet i.e. the set of characters using which the slug will be generated. I used the default alphabet.

#### Design and Development Approach
1. The application follows a client server architecture.
2. The source code in both sub-projects is organised by feature i.e. `slug` is the feature and all files related to it are present in the `slug` sub-folder in src.
3. The Backend is a REST API which provides the specified endpoints and one additional endpoint
   1. `GET /slugs` - To retrieve the list of all slugs and their corresponding URLs.
4. CORS is enabled for all origins, for all methods and headers as of now.
5. The Frontend is a React.js application which has 2 components - `URLShortnerForm` and `SlugList`
   1. `URLShortnerForm` is a form which takes the URL input and sends it to the backend to get the shortened URL.
   2. `SlugList` is a component which displays the list of all slugs and their corresponding URLs.
6. The service abstraction in both the projects handles the responsibility of hanlding the data and interacting with the persistance layer (in this case the in-memory map in the backend and the session storage in the frontend).
7. I've used sessionStorage instead of localStorage to avoid persisting the data across sessions as our data is anyways in-memory as of now.
8. I've followed the following branching strategy in git
   1. `main` - This branch contains production code. No direct commits allowed (by convention).
   2. `release/{version}` - Created from main and only used for creating and merging child feature branches which contain code corresponding to the release version. Only this branch can be merged into `main`.
   3. `feature/{feature-name}` - Created from release branch and used for developing a feature. Once the feature is complete, it is merged back to the release branch. Cannot merge directly in `main`.


#### Other Notes
1. I did do the task in 2 hours but I spent additional 30 minutes (timed) to complete the Slug list component and iron out a few UI bugs.
2. I planned to write unit tests for the backend but could not do so due to time constraints.
3. The documentation is written outside of the 2h time window
4. Co-pilot's inline suggestions were used to autocomplete simple logic and boiler plate code but the core logic was written by me.



#### References
1. [Node.js vs Spring Boot Hello World performance](https://medium.com/deno-the-complete-reference/node-js-vs-springboot-hello-world-performance-comparison-59b4d461526c)
2. [Node.js Fastify vs Spring Boot Webflux performance](https://medium.com/deno-the-complete-reference/node-js-fastify-vs-springboot-webflux-performance-comparison-for-jwt-verify-and-mysql-query-2365f0efb954)

## How to Run

### Prerequisites

- Java 17 or higher
- Node.js 18.x or higher
- npm 10.2.x or higher

### Backend (Spring Boot)

1. Navigate to the `Backend` directory:

    ```sh
    cd Backend
    ```

2. Build the project using Gradle:

    ```sh
    ./gradlew build
    ```

3. Run the Spring Boot application:

    ```sh
    ./gradlew bootRun
    ```

   The backend server should now be running at [http://localhost:8080](http://localhost:8080).

### Frontend (React.js)

1. Navigate to the  directory:

    ```sh
    cd ../Frontend
    ```

2. Install the dependencies:

    ```sh
    npm install
    ```

3. Start the development server:

    ```sh
    npm run dev
    ```

   The frontend application should now be running at [http://localhost:5173](http://localhost:5173).

### Accessing the Application ([View Demo](https://www.loom.com/share/7bc0a2dd62414bc4a56a92422e876144?sid=517c47ca-9d84-4e9f-badb-323b59dd703d))

- Open your browser and navigate to  to access the URL shortener web application.
- You can use the form to input a URL and get a shortened version.
- The shortened URL will be in the format , where `{slug}` is a unique identifier for the original URL.
- When you navigate to the shortened URL, you will be redirected to the original URL.


### Additional Notes

- The backend server runs on port `8080` by default.
- The frontend development server runs on port `5173` by default.
- Ensure that both the backend and frontend servers are running simultaneously to use the application.

## Testing

I have performed manual testing for both the frontend and backend.

For the backend, I've used Postman to test the endpoints. You can import this [Postman collection file](./url-shortner.postman_collection.json) to test the endpoints.

## Tools Used
### IDE
1. VS Code (with copilot)

### Technologies
1. Backend
   1. Spring Boot (with Gradle)
      1. Spring Web
      2. [JNanoId](https://github.com/Soundicly/jnanoid-enhanced) (for generating unique slugs)
   2. Java 17
2. Frontend
   1. React.js
   2. Bootstrap 5 (for styling)
   3. Axios (for API requests)


---

Good luck, and we look forward to reviewing your submission!