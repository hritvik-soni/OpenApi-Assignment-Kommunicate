# Open AI Assignment Kommunicate

This project contains a RESTful API service for handling chat requests using OpenAI. It provides an endpoint to send a partial text and get a response generated by Open AI.

## API Endpoints

### Complete Chat

- **URL:** `/complete_chat`
- **Method:** POST
- **Request Body:**
  ```json
  {
    "partial_text": "The partial text of the conversation."
  }
  ```
- **Response:**
  ```json
  {
    "completed_text": "The response generated by the GPT-3.5 model."
  }
  ```

## Building and Running the Program

To build and run the program locally, follow these steps:

1. Clone the repository to your local machine.
2. Make sure you have Java and Maven installed.
3. Update the `application.properties` file with your OpenAI API key and API URL.
4. Navigate to the project directory in your terminal.
5. Run the following Maven command to build the project: ` mvn clean install`
6. Once the build is successful, run the following command to start the application:
   `java -jar target/OpenApiAssignmentKommunicate-<version>.jar`
   Replace `<version>` with the actual version number.
7. The application should now be running locally on port 8080 by default.

## Dependencies

- Java
- Spring Boot
- Lombok
- Apache Commons Lang
- Spring Web
- RestTemplate

## Configuration

- `apiKey`: Your OpenAI API key in encrypt base 64.
- `apiUrl`: The URL of the OpenAI API endpoint.

##

Happy Coding !
